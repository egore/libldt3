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
using System.Linq;
using System.Reflection;
using System.Text;
using NodaTime;

namespace de
{
    namespace egore911
    {
        namespace fuzz
        {

            /**opensource@christophbrill.de
             * @author Christoph Brill &lt;egore911@gmail.com&gt;
             * @since 14.05.16 15:47
             */
            public class Fuzzer
            {

                public interface CustomHandler
                {
                    object randomValue(FieldInfo info);
                }

                readonly Random RANDOM = new Random();

                readonly int MAX_DEPTH = 20;
                readonly int MAX_COLLECTION_ELEMENTS = 2;
                readonly CustomHandler customHandler;

                public Fuzzer(CustomHandler? customHandler = null)
                {
                    this.customHandler = customHandler;
                }

                public T fuzz<T>(Type klass, string packageName)
                {
                    if (packageName == null)
                    {
                        packageName = klass.Namespace;
                    }
                    return fuzz<T>(klass, packageName, 0);
                }

                T instantiate<T>(Type klass, string packageName)
                {
                    T? t = default(T);
                    if (klass.IsAbstract)
                    {
                        IEnumerable<Type> subTypes = klass.Assembly.GetTypes().Where(type => type.IsSubclassOf(klass));
                        Exception? ie = null;
                        foreach (Type subType in subTypes)
                        {
                            try
                            {
                                t = (T)Activator.CreateInstance(subType);
                                break;
                            }
                            catch (Exception e)
                            {
                                // Ok, let's try again
                                ie = e;
                            }
                        }
                        if (t == null)
                        {
                            // Could not be instantiated
                            throw new Exception("Could not instantiate " + klass + " in " + subTypes, ie);
                        }
                    }
                    else
                    {
                        t = (T)Activator.CreateInstance(klass);
                    }
                    return t;
                }

                T? fuzz<T>(Type klass, string packageName, int depth)
                {
                    if (depth > MAX_DEPTH)
                    {
                        return default(T);
                    }
                    T t = instantiate<T>(klass, packageName);

                    Type currentClass = t.GetType();
                    while (currentClass != null)
                    {
                        FieldInfo[] fields = currentClass.GetFields(BindingFlags.Instance | BindingFlags.DeclaredOnly | BindingFlags.Public);
                        foreach (FieldInfo field in fields)
                        {
                            // Don't fuzz static fields
                            if (field.IsStatic)
                            {
                                continue;
                            }
                            Type type = field.FieldType;
                            object? value = null;
                            if (customHandler != null)
                            {
                                value = customHandler.randomValue(field);
                            }
                            if (value == null)
                            {
                                value = randomForType(type, packageName, depth);
                            }
                            field.SetValue(t, value);
                        }

                        currentClass = currentClass.BaseType;
                    }

                    return t;
                }

                object randomForType(Type type, string packageName, int depth)
                {
                    if (type.Equals(typeof(int)) || type.Equals(typeof(int?)))
                    {
                        return randomInt();
                    }
                    else if (type.Equals(typeof(long)) || type.Equals(typeof(long?)))
                    {
                        return randomLong();
                    }
                    else if (type.Equals(typeof(double)) || type.Equals(typeof(double?)))
                    {
                        return randomDouble();
                    }
                    else if (type.Equals(typeof(float)) || type.Equals(typeof(float?)))
                    {
                        return randomFloat();
                    }
                    else if (type.Equals(typeof(bool)) || type.Equals(typeof(bool?)))
                    {
                        return randomBoolean();
                    }
                    else if (type.Equals(typeof(string)))
                    {
                        return randomString();
                    }
                    else if (type.Equals(typeof(Guid)))
                    {
                        return randomUUID();
                    }
                    else if (type.Equals(typeof(byte[])))
                    {
                        return Encoding.ASCII.GetBytes(randomString());
                    }
                    else if (type.Equals(typeof(DateTime)))
                    {
                        return randomDate();
                    }
                    else if (type == typeof(LocalDateTime) || type.IsGenericType && Nullable.GetUnderlyingType(type) != null && type.GetGenericArguments()[0].Equals(typeof(LocalDateTime)))
                    {
                        return randomDateTime();
                    }
                    else if (type == typeof(LocalDate) || type.IsGenericType && Nullable.GetUnderlyingType(type) != null && type.GetGenericArguments()[0].Equals(typeof(LocalDate)))
                    {
                        return randomLocalDate();
                    }
                    else if (type == typeof(LocalTime) || type.IsGenericType && Nullable.GetUnderlyingType(type) != null && type.GetGenericArguments()[0].Equals(typeof(LocalTime)))
                    {
                        return randomLocalTime();
                    }
                    else if (type.IsGenericType && Nullable.GetUnderlyingType(type) != null && type.GetGenericArguments()[0].IsEnum)
                    {
                        Array values = Enum.GetValues(type.GetGenericArguments()[0]);
                        return values.GetValue(RANDOM.Next(values.Length));
                    }
                    else if (type.IsGenericType)
                    {
                        System.Collections.IList o;
                        if (type.GetGenericTypeDefinition() == typeof(IEnumerable<>))
                        {
                            o = (System.Collections.IList)Activator.CreateInstance(typeof(List<>).MakeGenericType(type.GetGenericArguments()[0]));
                        }
                        else if (type.GetGenericTypeDefinition() == typeof(IList<>))
                        {
                            o = (System.Collections.IList)Activator.CreateInstance(typeof(List<>).MakeGenericType(type.GetGenericArguments()[0]));
                        }
                        else if (type.GetGenericTypeDefinition() == typeof(ISet<>))
                        {
                            // FIXME o = Activator.CreateInstance(typeof(HashSet<>).MakeGenericType(type.GetGenericArguments()[0]));
                            o = (System.Collections.IList)Activator.CreateInstance(typeof(List<>).MakeGenericType(type.GetGenericArguments()[0]));
                        }
                        else
                        {
                            o = (System.Collections.IList)Activator.CreateInstance(type);
                        }
                        Type[] actualTypeArguments = type.GenericTypeArguments;
                        foreach (Type t in actualTypeArguments)
                        {
                            for (int i = 0; i < 1 + RANDOM.Next(MAX_COLLECTION_ELEMENTS); i++)
                            {
                                object e = randomForType(t, packageName, depth);
                                if (e != null)
                                {
                                    o.Add(e);
                                }
                            }
                        }
                        return o;
                    }
                    else if (type.IsEnum)
                    {
                        Array values = Enum.GetValues(type);
                        return values.GetValue(RANDOM.Next(values.Length));
                    }
                    else
                    {
                        return fuzz<object>(type, packageName, depth + 1);
                    }
                }

                Guid randomUUID()
                {
                    return Guid.NewGuid();
                }

                double randomDouble()
                {
                    return RANDOM.NextDouble();
                }

                float randomFloat()
                {
                    return (float)RANDOM.NextDouble();
                }

                bool randomBoolean()
                {
                    return RANDOM.Next(1) == 1;
                }

                long randomLong()
                {
                    return RANDOM.Next();
                }

                int randomInt()
                {
                    return RANDOM.Next();
                }

                string randomString()
                {
                    return Guid.NewGuid().ToString();
                }

                DateTime randomDate()
                {
                    return randomDateTime().ToDateTimeUnspecified();
                }

                LocalDate randomLocalDate()
                {
                    return new LocalDate().PlusDays(RANDOM.Next(300)).PlusDays(-150);
                }

                LocalTime randomLocalTime()
                {
                    return new LocalTime().PlusHours(RANDOM.Next(24)).PlusHours(-12);
                }

                LocalDateTime randomDateTime()
                {
                    return new LocalDateTime().PlusDays(RANDOM.Next(300)).PlusDays(-150);
                }

            }
        }
    }
}
