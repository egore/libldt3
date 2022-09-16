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
using System.Reflection;
using libldt3.attributes;
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

					/**
					 * Check if a given field has any string content (either simply text or multiline Fliesstext)
					 */
					public static bool ContainsAnyString(FieldInfo Info, object owner)
					{
						if (Info == null)
						{
							Trace.TraceWarning("No field given, cannot check for content");
							return false;
						}
						object value = Info.GetValue(owner);
						return ContainsAnyString(value);
					}

					/**
					 * Check if a given field has any string content (either simply text or multiline Fliesstext)
					 */
					public static bool ContainsAnyString(object value)
					{
						if (value is string)
						{
							string o = (string)value;
							return o.Length > 0;
						}
						if (value is Fliesstext)
						{
							Fliesstext fliesstext = ((Fliesstext)value);
							if (fliesstext.Text != null)
							{
								foreach (string s in fliesstext.Text)
								{
									if (s != null && s.Length > 0)
									{
										return true;
									}
								}
							}
							if (fliesstext.Base64text != null)
							{
								foreach (string s in fliesstext.Base64text)
								{
									if (s != null && s.Length > 0)
									{
										return true;
									}
								}
							}
						}
						return false;
					}

					/**
					 * Find a field matching their {@link Feld#value()} with the given fieldtype. Not recursive, only a single field.
					 */
					public static FieldInfo FindFieldInfo(object owner, string fieldtype)
					{
						foreach (FieldInfo p in owner.GetType().GetFields())
						{
							Feld annotation = p.GetCustomAttribute<Feld>();
							if (annotation != null && annotation.Value.Equals(fieldtype))
							{
								return p;
							}
						}
						return null;
					}

					/**
					 * Find fields matching their {@link Feld#value()} with the given fieldtypes. Not recursive, but for multiple field.
					 */
					public static IDictionary<string, FieldInfo> FindFieldInfos(object owner, ISet<string> fieldtypes)
					{
						IDictionary<string, FieldInfo> result = new Dictionary<string, FieldInfo>(fieldtypes.Count);
						foreach (FieldInfo p in owner.GetType().GetFields())
						{
							Feld annotation = p.GetCustomAttribute<Feld>();
							if (annotation != null && fieldtypes.Contains(annotation.Value))
							{
								result[annotation.Value] = p;
							}
						}
						return result;
					}

					/**
					 * Find fields matching their {@link Feld#value()} with the given fieldtypes. Recursive and for multiple field.
					 */
					public static IDictionary<object, IList<FieldInfo>> FindFieldInfosRecursive(Object owner, ISet<string> fieldtypes) {
						IDictionary<object, IList<FieldInfo>> result = new Dictionary<object, IList<FieldInfo>>();
						IList<FieldInfo> fields = new List<FieldInfo>();
						foreach (FieldInfo p in owner.GetType().GetFields()) {
							Feld annotation = p.GetCustomAttribute<Feld>();
							if (annotation != null && fieldtypes.Contains(annotation.Value)) {
								fields.Add(p);
								result[owner] = fields;
							}
							object o = p.GetValue(owner);
							if (o != null) {
								if (o.GetType().GetCustomAttribute<Objekt>() != null) {
									foreach (var item in FindFieldInfosRecursive(o, fieldtypes))
									{
										result[item.Key] = item.Value;
									}

								} else if (o is IEnumerable<object>) {
									foreach (object o2 in (IEnumerable<object>) o) {
										foreach (var item in FindFieldInfosRecursive(o2, fieldtypes))
										{
											result[item.Key] = item.Value;
										}
									}
								}
							}
						}
						return result;
					}

				}
			}
		}
	}
}
