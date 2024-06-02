/*
 * Copyright 2016-2024  Christoph Brill <opensource@christophbrill.de>
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
using System.Diagnostics;
using System.Reflection;
using libldt3.attributes;
using libldt3.model;
using libldt3.model.objekte;

namespace libldt3
{
    namespace model
    {
        namespace regel
        {
            namespace kontext
            {
                class KontextregelHelper
                {

                    public static bool ContainsAnyString(string value)
                    {
                        return !string.IsNullOrEmpty(value);
                    }
                    /// Check if a given field has any string content (either simply text or multiline Fliesstext)
                    public static bool ContainsAnyString(Fliesstext value)
                    {
                        Fliesstext fliesstext = (Fliesstext)value;
                        if (fliesstext.Text != null)
                        {
                            foreach (string s in fliesstext.Text)
                            {
                                if (s != null && !string.IsNullOrEmpty(s))
                                {
                                    return true;
                                }

                            }
                        }

                        if (fliesstext.Base64kodierteAnlage != null)
                        {
                            foreach (string s in fliesstext.Base64kodierteAnlage)
                            {
                                if (s != null && !string.IsNullOrEmpty(s))
                                {
                                    return true;
                                }

                            }
                        }

                        return false;
                    }
                    /// Check if a given field has any string content (either simply text or multiline Fliesstext)
                    public static bool ContainsAnyValue(FieldInfo field, object owner)
                    {
                        object value = KontextregelHelper.GetFieldValue(field, owner);
                        if (value == null)
                        {
                            return false;
                        }

                        if (value is string)
                        {
                            return KontextregelHelper.ContainsAnyString((string)value);
                        }

                        if (value is Fliesstext)
                        {
                            return KontextregelHelper.ContainsAnyString((Fliesstext)value);
                        }

                        return true;
                    }
                    /// Not recursive, only a single field.
                    public static FieldInfo FindField(object owner, string fieldtype)
                    {
                        foreach (FieldInfo f in owner.GetType().GetFields())
                        {
                            Feld? annotation = f.GetCustomAttribute<Feld>();
                            if (annotation != null && annotation.Value.Equals(fieldtype))
                            {
                                ;
                                return f;
                            }

                        }
                        return null;
                    }
                    /// Not recursive, but for multiple field.
                    public static IDictionary<string, FieldInfo> FindFields(Kontext owner, ISet<string> fieldtypes)
                    {
                        IDictionary<string, FieldInfo> result = new Dictionary<string, FieldInfo>(fieldtypes.Count);
                        foreach (FieldInfo f in owner.GetClass().GetFields())
                        {
                            Feld? annotation = f.GetCustomAttribute<Feld>();
                            if (annotation != null && fieldtypes.Contains(annotation.Value))
                            {
                                ;
                                result[annotation.Value] = f;
                            }

                        }
                        return result;
                    }
                    /// Recursive and for multiple field.
                    public static IDictionary<Kontext, IDictionary<string, FieldInfo>> FindFieldsRecursive(Kontext owner, ISet<string> fieldtypes)
                    {
                        IDictionary<Kontext, IDictionary<string, FieldInfo>> result = new Dictionary<Kontext, IDictionary<string, FieldInfo>>();
                        IDictionary<string, FieldInfo> fields = new Dictionary<string, FieldInfo>();
                        result[owner] = fields;
                        foreach (FieldInfo f in owner.GetClass().GetFields())
                        {
                            Feld? annotation = f.GetCustomAttribute<Feld>();
                            if (annotation != null && fieldtypes.Contains(annotation.Value))
                            {
                                ;
                                fields[annotation.Value] = f;
                            }

                    ;
                            object obj = f.GetValue(owner);
                            if (obj != null)
                            {
                                if (obj.GetType().GetCustomAttribute<Objekt>() != null)
                                {
                                    foreach (var x in KontextregelHelper.FindFieldsRecursive((Kontext)obj, fieldtypes)) { result[x.Key] = x.Value; };
                                }
                                else
                                {
                                    if (obj is IEnumerable<object>)
                                    {
                                        foreach (object o in (IEnumerable<Kontext>)obj)
                                        {
                                            if (o.GetType().GetCustomAttribute<Objekt>() != null)
                                            {
                                                foreach (var x in KontextregelHelper.FindFieldsRecursive((Kontext)o, fieldtypes)) { result[x.Key] = x.Value; };
                                            }

                                        }
                                    }

                                }

                            }

                        }
                        return result;
                    }
                    public static object GetFieldValue(FieldInfo field, object owner)
                    {
                        if (field == null)
                        {
                            Trace.TraceWarning("No field given, cannot check for content");
                            return null;
                        }

                    ;
                        return field.GetValue(owner);
                    }
                }
            }
        }
    }
}
