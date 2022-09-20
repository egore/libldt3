/*
 * Copyright 2016-2022  Christoph Brill <opensource@christophbrill.de>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Reflection;
using System.Text;
using libldt3.attributes;
using libldt3.model.enums;
using libldt3.model.saetze;
using NodaTime;

namespace libldt3
{

    /**
     * Simple, reflection and annotation based writer for LDT 3.0.
     * 
     * @author Christoph Brill &lt;opensource@christophbrill.de&gt;
     */
    public class LdtWriter
    {

        private readonly LdtConstants.Mode mode;

        public LdtWriter(LdtConstants.Mode mode)
        {
            this.mode = mode;
        }

        /**
         * Write a given set of Satz elements to a given path
         * 
         * @param data
         *            the Satz elements to write
         * @param path
         *            the path to write to
         */
        public void Write(IList<Satz> data, string path)
        {
            using (var f = File.OpenWrite(path))
            {
                Write(data, f);
            }
        }

        /**
         * Write a given set of Satz elements to a given path
         * 
         * @param data
         *            the Satz elements to write
         * @param path
         *            the path to write to
         */
        public void Write(IList<Satz> data, FileStream path)
        {
            using (var w = new StreamWriter(path, Encoding.GetEncoding("ISO-8859-1")))
            {
                Write(data, w);
            }
        }

        /**
         * Write a given set of Satz elements to a given writer
         * 
         * @param data
         *            the Satz elements to write
         * @param writer
         *            the writer to write to
         */
        public void Write(IList<Satz> data, StreamWriter writer)
        {
            foreach (Satz o in data)
            {
                HandleOutput(o, writer);
            }
        }

        void HandleOutput(object o, StreamWriter writer)
        {
            Datenpaket datenpaket = o.GetType().GetCustomAttribute<Datenpaket>();
            if (datenpaket != null)
            {
                writer.Write(string.Format("0138000{0}\r\n", datenpaket.Value.GetCode()));
            }
            WriteObjekt(o, writer);
            if (datenpaket != null)
            {
                writer.Write(string.Format("0138001{0}\r\n", datenpaket.Value.GetCode()));
            }
        }

        void WriteObjekt(object o, StreamWriter writer)
        {
            Objekt objekt = o.GetType().GetCustomAttribute<Objekt>();
            if (objekt != null && objekt.Value.Length > 0)
            {
                writer.Write(string.Format("0178002Obj_{0}\r\n", objekt.Value));
            }
            foreach (FieldInfo field in o.GetType().GetFields(BindingFlags.Instance | BindingFlags.DeclaredOnly | BindingFlags.Public))
            {
                Feld feld = field.GetCustomAttribute<Feld>();
                if (feld != null)
                {
                    object o2 = field.GetValue(o);
                    if (o2 == null)
                    {
                        continue;
                    }
                    if (GetGenericList(o2.GetType()) != null)
                    {
                        foreach (object o1 in (System.Collections.IList)o2)
                        {
                            WriteTextualRepresentation(field, writer, feld, o1);
                            HandleOutput(o1, writer);
                        }
                    }
                    else
                    {
                        WriteTextualRepresentation(field, writer, feld, o2);
                        HandleOutput(o2, writer);
                    }
                }
            }
            if (objekt != null && objekt.Value.Length > 0)
            {
                writer.Write(string.Format("0178003Obj_{0}\r\n", objekt.Value));
            }
        }

        void WriteLdtLine(StreamWriter writer, Feld feld, string text)
        {
            writer.Write(string.Format("{0}{1}{2}\r\n", (text.Length + 9).ToString("000"), feld.Value, text));
        }

        /**
         * Transform an object into its LDT 3.0 represenation
         */
        void WriteTextualRepresentation(FieldInfo field, StreamWriter writer, Feld feld, object o)
        {
            if (feld.Feldart == Feldart.muss && o == null)
            {
                if (mode == LdtConstants.Mode.STRICT)
                {
                    throw new ArgumentException(
                            "Cannot get textual representation of null when writing feld " + feld);
                }
                else
                {
                    Trace.TraceWarning("Cannot get textual representation of null when writing feld {0}, assuming empty string", feld);
                    WriteLdtLine(writer, feld, "");
                    return;
                }
            }
            if (o is string)
            {
                string value = (string)o;
                foreach (Regelsatz regelsatz in field.GetCustomAttributes<Regelsatz>())
                {
                    if (regelsatz.MaxLaenge >= 0)
                    {
                        if (value.Length > regelsatz.MaxLaenge)
                        {
                            if (mode == LdtConstants.Mode.STRICT)
                            {
                                throw new ArgumentException("Value " + value + " must have maximum length of " + regelsatz.MaxLaenge + ", but was " + value.Length);
                            }
                            else
                            {
                                Trace.TraceWarning("{0}.{1}: Value {1} must have maximum length of {3}, but was {4}, trimming", field.DeclaringType.Name, field.Name, value, regelsatz.MaxLaenge, value.Length);
                                value = value.Substring(0, Math.Min(value.Length, regelsatz.MaxLaenge));
                            }
                        }
                    }
                    else if (regelsatz.Laenge >= 0)
                    {
                        if (value.Length > regelsatz.Laenge)
                        {
                            if (mode == LdtConstants.Mode.STRICT)
                            {
                                throw new ArgumentException(field.DeclaringType.Name + "." + field.Name + ": Value " + value + " must have exact length of " + regelsatz.Laenge + ", but was " + value.Length);
                            }
                            else
                            {
                                Trace.TraceWarning("{0}.{1}: Value {2} must have exact length of {3}, but was {4}, trimming", field.DeclaringType.Name, field.Name, value, regelsatz.Laenge, value.Length);
                                value = value.Substring(0, regelsatz.Laenge);
                            }
                        }
                        else if (value.Length < regelsatz.Laenge)
                        {
                            if (mode == LdtConstants.Mode.STRICT)
                            {
                                throw new ArgumentException(field.DeclaringType.Name + "." + field.Name + ": Value " + value + " must have exact length of " + regelsatz.Laenge + ", but was " + value.Length);
                            }
                            else
                            {
                                Trace.TraceWarning("{0}.{1}: Value {2} must have exact length of {3}, but was {4}, ignoring", field.DeclaringType.Name, field.Name, value, regelsatz.Laenge, value.Length);
                            }
                        }
                    }
                }
                WriteLdtLine(writer, feld, value);
                return;
            }
            if (o is float)
            {
                WriteLdtLine(writer, feld, o.ToString());
                return;
            }
            if (o is int)
            {
                WriteLdtLine(writer, feld, o.ToString());
                return;
            }
            if (o is bool)
            {
                WriteLdtLine(writer, feld, ((bool)o) ? "1" : "0");
                return;
            }
            if (o is LocalDate)
            {
                WriteLdtLine(writer, feld, LdtConstants.FORMAT_DATE.Format((LocalDate)o));
                return;
            }
            if (o is LocalTime)
            {
                WriteLdtLine(writer, feld, LdtConstants.FORMAT_TIME.Format((LocalTime)o));
                return;
            }
            if (o is Enum)
            {
                MethodInfo method = Type.GetType(o.GetType().FullName + "Extensions").GetMethod("GetCode");
                if (method != null)
                {
                    WriteLdtLine(writer, feld, (string)method.Invoke(o, new object[] { o }));
                    return;
                }
            }
            if (IsNullableEnum(o.GetType()))
            {
                Type enumType = Nullable.GetUnderlyingType(o.GetType());
                MethodInfo method = Type.GetType(enumType.FullName + "Extensions").GetMethod("GetCode");
                if (method != null)
                {
                    WriteLdtLine(writer, feld, (string)method.Invoke(o, new object[] { o }));
                    return;
                }
            }
            Objekt annotation = o.GetType().GetCustomAttribute<Objekt>();
            if (annotation != null && annotation.Value.Length == 0)
            {
                try
                {
                    FieldInfo declaredField = o.GetType().GetField("Value");
                    object innerObject = declaredField.GetValue(o);
                    WriteTextualRepresentation(declaredField, writer, feld, innerObject);
                    Objekt innerAnnotation = innerObject.GetType().GetCustomAttribute<Objekt>();
                    if (innerAnnotation != null && innerAnnotation.Value.Length > 0)
                    {
                        WriteObjekt(innerObject, writer);
                    }
                    return;
                }
                catch (Exception e)
                {
                    if (mode == LdtConstants.Mode.STRICT)
                    {
                        throw new InvalidOperationException(e.Message, e);
                    }
                    else
                    {
                        Trace.TraceError(e.Message);
                    }
                }
            }
            string name = feld.Name;
            if (name.Length == 0)
            {
                if (annotation != null && annotation.Name.Length > 0)
                {
                    name = annotation.Name;
                }
                else
                {
                    name = o.GetType().Name;
                }
            }
            WriteLdtLine(writer, feld, name);
        }

        static bool IsNullableEnum(Type t)
        {
            Type u = Nullable.GetUnderlyingType(t);
            return (u != null) && u.IsEnum;
        }

        static Type? GetGenericList(Type type)
        {
            if (type.IsGenericType && type.GetGenericTypeDefinition() == typeof(IList<>))
            {
                return type;
            }

            foreach (Type interfaceType in type.GetInterfaces())
            {
                if (interfaceType.IsGenericType && interfaceType.GetGenericTypeDefinition() == typeof(IList<>))
                {
                    return interfaceType;
                }
            }
            return null;
        }
    }
}
