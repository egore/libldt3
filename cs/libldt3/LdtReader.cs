/*
 * Copyright 2016  Christoph Brill <egore911@gmail.com>
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
using System.Linq;
using System.Reflection;
using System.Text;
using libldt3.attributes;
using libldt3.model.enums;
using libldt3.model.regel;
using libldt3.model.regel.kontext;
using libldt3.model.saetze;
using NodaTime;

namespace libldt3
{

	/**
	 * Simple, reflection and annotation based reader for LDT 3.0.
	 * 
	 * @author Christoph Brill &lt;egore911@gmail.com&gt;
	 */
	public class LdtReader
	{

		readonly IDictionary<Type, Regel> regelCache = new Dictionary<Type, Regel>();

		readonly LdtConstants.Mode mode;

		public LdtReader(LdtConstants.Mode mode)
		{
			this.mode = mode;
		}

		/**
		 * Read the LDT found on a given path.
		 * 
		 * @param path
		 *			the path of the LDT file (any format handled by NIO
		 *			{@link Path})
		 * @return the list of Satz elements found in the LDT file
		 * @throws IOException
		 *			 thrown if reading the file failed
		 */
		public IList<Satz> Read(string path)
		{
			using (var f = File.Open(path, FileMode.Open))
			{
				return Read(f);
			}
		}

		/**
		 * Read the LDT found on a given path.
		 * 
		 * @param path
		 *			the path of the LDT file
		 * @return the list of Satz elements found in the LDT file
		 * @throws IOException
		 *			 thrown if reading the file failed
		 */
		public IList<Satz> Read(FileStream path)
		{
			var stream = new StreamReader(path, Encoding.GetEncoding("ISO-8859-1"));
			return Read(stream);
		}

		/**
		 * Read the LDT from a given string stream.
		 * 
		 * @param stream
		 *			the LDT lines as string stream
		 * @return the list of Satz elements found in the LDT file
		 */
		public IList<Satz> Read(StreamReader stream)
		{
			Stack<object> stack = new Stack<object>();
			IList<Satz> data = new List<Satz>();
			string line;
			int integer = 0;
			while ((line = stream.ReadLine()) != null)
			{
				HandleInput(line, stack, data, integer++);
			}
			return data;
		}

		void HandleInput(string line, Stack<object> stack, IList<Satz> data, int lineNo)
		{
			Trace.TraceInformation("Reading line {0}", line);

			// Check if the line meets the minimum requirements (3 digits for
			// length, 4 digits for the identifier)
			if (line.Length < 7)
			{
				if (mode == LdtConstants.Mode.STRICT)
				{
					throw new ArgumentException("Line '" + line + "' (" + lineNo + ") was less than 7 characters, aborting");
				}
				else
				{
					Trace.TraceInformation("Line '{0}' ({1}) was less than 7 characters, continuing anyway", line, lineNo);
				}
			}

			// Read the length and check whether it had the correct length
			int length = int.Parse(line.Substring(0, 3));
			if (length != line.Length + 2)
			{
				if (mode == LdtConstants.Mode.STRICT)
				{
					throw new ArgumentException(
							"Line '" + line + "' (" + lineNo + ") should have length " + (line.Length + 2) + ", but was " + length);
				}
				else
				{
					Trace.TraceInformation("Line '{0}' ({1}) should have length {2}, but was {3}. Ignoring specified length", line, lineNo,
						(line.Length + 2), length);
					length = line.Length + 2;
				}
			}

			// Read identifier and payload
			string identifier = line.Substring(3, 7 - 3);
			string payload = line.Substring(7, length - 2 - 7);

			switch (identifier)
			{
				case "8000":
					{
						// Start: Satz
						AssureLength(line, length, 13);
						if (stack.Count > 0)
						{
							if (mode == LdtConstants.Mode.STRICT)
							{
								throw new InvalidOperationException(
										"Stack must be empty when starting a new Satz, but was " + stack.Count + " long");
							}
							else
							{
								Trace.TraceInformation("Stack must be empty when starting a new Satz, but was {0}. Clearing and continuing",
										stack);
								stack.Clear();
							}
						}

						// Extract Satzart from payload and create Satz matching it
						Satzart satzart = GetSatzart(payload);
						switch (satzart)
						{
							case Satzart.Befund:
								stack.Push(new Befund());
								break;
							case Satzart.Auftrag:
								stack.Push(new Auftrag());
								break;
							case Satzart.LaborDatenpaketHeader:
								stack.Push(new LaborDatenpaketHeader());
								break;
							case Satzart.LaborDatenpaketAbschluss:
								stack.Push(new LaborDatenpaketAbschluss());
								break;
							case Satzart.PraxisDatenpaketHeader:
								stack.Push(new PraxisDatenpaketHeader());
								break;
							case Satzart.PraxisDatenpaketAbschluss:
								stack.Push(new PraxisDatenpaketAbschluss());
								break;
							default:
								throw new ArgumentException("Unsupported Satzart '" + payload + "' found");
						}
						break;
					}
				case "8001":
					{
						// End: Satz
						AssureLength(line, length, 13);
						object o = stack.Pop();
						Datenpaket datenpaket = o.GetType().GetCustomAttribute<Datenpaket>();
						if (datenpaket != null)
						{
							EvaluateContextRules(o, datenpaket.Kontextregeln);
						}
						if (stack.Count == 0)
						{
							data.Add((Satz)o);
						}
						break;
					}
				case "8002":
					{
						// Start: Objekt
						AssureLength(line, length, 17);
						object currentObject1 = PeekCurrentObject(stack);
						Objekt annotation1 = currentObject1.GetType().GetCustomAttribute<Objekt>();
						if (annotation1 != null)
						{
							if (annotation1.Value.Length == 0)
							{
								// If annotation is empty, the parent object would actually
								// be the one to deal with
							}
							else
							{
								// Match found, everything is fine
								if (payload.Equals("Obj_" + annotation1.Value))
								{
									break;
								}

								// No match found, abort or inform the developer
								if (mode == LdtConstants.Mode.STRICT)
								{
									throw new ArgumentException(
											"In line '" + line + "' (" + lineNo + ") expected Obj_" + annotation1.Value + ", got " + payload);
								}
								else
								{
									Trace.TraceError("In line {0} ({1}) expected Obj_{2}, got {3}", line, lineNo, annotation1.Value, payload);
									break;
								}
							}
						}
						if (mode == LdtConstants.Mode.STRICT)
						{
							throw new ArgumentException("Line '" + line + "' (" + lineNo + ") started an unexpeted object, stack was " + stack.ToArray());
						}
						else
						{
							Trace.TraceWarning("Line '{0}' ({1}) started an unexpeted object, stack was {2}", line, lineNo, stack);
						}
						break;
					}
				case "8003":
					{
						// End: Objekt
						AssureLength(line, length, 17);
						object o;
						Objekt annotation1;
						do
						{
							o = stack.Pop();
							annotation1 = o.GetType().GetCustomAttribute<Objekt>();
							if (annotation1 != null)
							{
								if (annotation1.Value.Length != 0 && !("Obj_" + annotation1.Value).Equals(payload)) {
									Trace.TraceWarning("Line: {0} ({1}), annotation {2}, payload {3}", line, lineNo, annotation1.Value, payload);
								}
								EvaluateContextRules(o, annotation1.Kontextregeln);
							}
						} while (annotation1 != null && annotation1.Value.Length == 0);
						if (stack.Count == 0)
						{
							data.Add((Satz)o);
						}
						break;
					}
				default:
					// Any line not starting or completing a Satz or Objekt
					object currentObject = PeekCurrentObject(stack);
					if (currentObject == null)
					{
						throw new InvalidOperationException("No object when appplying line " + line + " (" + lineNo + ")");
					}
					// XXX iterating the fields could be replaced by a map to be a bit
					// faster when dealing with the same class
					foreach (FieldInfo info in currentObject.GetType().GetFields())
					{

						// Check if we found a Feld annotation, if not this is not our
						// field
						Feld annotation2 = info.GetCustomAttribute<Feld>();
						if (annotation2 == null)
						{
							continue;
						}

						// Check if the annotation matches the identifier, if not, this
						// is not our field
						if (!identifier.Equals(annotation2.Value))
						{
							continue;
						}

						try
						{
							// Check if there is currently a value set
							object o = info.GetValue(currentObject);
							if (o != null && GetGenericList(info.FieldType) == null)
							{
								if (mode == LdtConstants.Mode.STRICT)
								{
									throw new InvalidOperationException(
										"Line '" + line + "' (" + lineNo + ") would overwrite existing value " + o + " of " + currentObject + "." + info.Name);
								}
								else
								{
									Trace.TraceWarning("Line '{0}' ({1}) would overwrite existing value {2} in object {3}.{4}", line, lineNo, o, currentObject, info);
								}
							}

							ValidateFieldPayload(info, payload);

							// Convert the value to its target type ...
							object value = ConvertType(info, info.FieldType, payload, stack);

							// .. and set the value on the target object
							info.SetValue(currentObject, value);
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
						// We are done with this line
						return;
					}

					// No field with a matching Feld annotation found, check if we are
					// an Objekt with an empty value (anonymous object), if so try our
					// parent
					Objekt annotation = currentObject.GetType().GetCustomAttribute<Objekt>();
					if (annotation != null && annotation.Value.Length == 0)
					{
						stack.Pop();
						HandleInput(line, stack, data, lineNo);
						return;
					}

					// Neither we nor our parent could deal with this line
					if (mode == LdtConstants.Mode.STRICT)
					{
						throw new ArgumentException("Failed reading line " + line + " (" + lineNo + "), current stack: " + string.Join(" ", stack.ToArray()));
					}
					else
					{
						Trace.TraceWarning("Failed reading line {0} ({1}), current stack: {2}, skipping line", line, lineNo, string.Join(" ", stack.ToArray()));
					}
					break;
			}
		}

		private void EvaluateContextRules(object o, Type[] kontextRegeln)
		{
			foreach (Type kontextregel in kontextRegeln)
			{
				try
				{
					if (!((Kontextregel)Activator.CreateInstance(kontextregel)).IsValid(o))
					{
						if (mode == LdtConstants.Mode.STRICT)
						{
							throw new ArgumentException("Context rule " + kontextregel.Name + " failed on object " + o);
						}
						else
						{
							Trace.TraceWarning("Context rule {} failed on object {}", kontextregel.Name, o);
						}
					}
				}
				catch (Exception e)
				{
					if (mode == LdtConstants.Mode.STRICT)
					{
						throw new ArgumentException("Context rule " + kontextregel.Name + " failed on object " + o, e);
					}
					else
					{
						Trace.TraceWarning("Context rule {} failed on object {}", kontextregel.Name, o, e);
					}
				}
			}
		}

		void ValidateFieldPayload(FieldInfo field, string payload)
		{
			foreach (Regelsatz regelsatz in field.GetCustomAttributes<Regelsatz>())
			{

				if (regelsatz.Laenge >= 0)
				{
					if (payload.Length != regelsatz.Laenge)
					{
						ValidationFailed(field.DeclaringType.Name + "." + field.Name + ": Value " + payload + " did not match expected length "
								+ regelsatz.Laenge + ", was " + payload.Length);
					}
				}

				if (regelsatz.MinLaenge >= 0)
				{
					if (payload.Length < regelsatz.MinLaenge)
					{
						ValidationFailed(field.DeclaringType.Name + "." + field.Name + ": Value " + payload + " did not match expected minimum length "
								+ regelsatz.MinLaenge + ", was " + payload.Length);
					}
				}

				if (regelsatz.MaxLaenge >= 0)
				{
					if (payload.Length > regelsatz.MaxLaenge)
					{
						ValidationFailed(field.DeclaringType.Name + "." + field.Name + ": Value " + payload + " did not match expected maximum length "
								 + regelsatz.MaxLaenge + ", was " + payload.Length);
					}
				}

				// No specific rules given, likely only length checks
				if (regelsatz.Value.Length == 0)
				{
					continue;
				}

				bool found = false;
				foreach (Type regel in regelsatz.Value)
				{
					if (GetRegel(regel).IsValid(payload))
					{
						found = true;
						break;
					}
				}
				if (!found)
				{

					ValidationFailed(field.DeclaringType.Name + "." + field.Name + ": Value " + payload + " did not confirm to any rule of "
									   + ToString(regelsatz.Value));
				}
			}
		}

		void ValidationFailed(string message)
		{
			if (mode == LdtConstants.Mode.STRICT)
			{
				throw new InvalidOperationException(message);
			}
			else
			{
				Trace.TraceWarning(message);
			}
		}

		string ToString(Type[] regeln)
		{
			StringBuilder buffer = new StringBuilder();
			foreach (Type regel in regeln)
			{
				if (buffer.Length > 0)
				{
					buffer.Append(" or ");
				}
				buffer.Append(regel.Name);
			}
			return buffer.ToString();
		}

		Regel GetRegel(Type regel)
		{
			Regel instance;
			regelCache.TryGetValue(regel, out instance);
			if (instance == null)
			{
				instance = (Regel)Activator.CreateInstance(regel);
				regelCache[regel] = instance;
			}
			return instance;
		}

		/**
		 * Extract the Satzart form a given payload
		 * 
		 * @param payload
		 *			the payload of the line
		 * @return the Satzart or {@code null}
		 */
		Satzart GetSatzart(string payload)
		{
			foreach (Satzart sa in Enum.GetValues(typeof(Satzart)).Cast<Satzart>())
			{
				if (sa.GetCode().Equals(payload))
				{
					return sa;
				}
			}
			throw new ArgumentException("Unsupported Satzart '" + payload + "' found");
		}

		/**
		 * Peek the current objekt from the stack, if any.
		 * 
		 * @param stack
		 *			the stack to peek the object from
		 * @return the current top level element of the stack or {@code null}
		 */
		static object PeekCurrentObject(Stack<object> stack)
		{
			if (stack.Count == 0)
			{
				return null;
			}
			return stack.Peek();
		}

		/**
		 * Check if the line matches the expected length.
		 * 
		 * @param line
		 *			the line to check
		 * @param length
		 *			the actual length
		 * @param target
		 *			the length specified by the line
		 */
		void AssureLength(string line, int length, int target)
		{
			if (length != target)
			{
				if (mode == LdtConstants.Mode.STRICT)
				{
					throw new ArgumentException(
							"Line '" + line + "' must have length " + target + ", was " + length);
				}
				else
				{
					Trace.TraceInformation("Line '{0}' must have length {1}, was {2}", line, target, length);
				}
			}
		}

		/**
		 * Convert the string payload into a target class. (Note: There are
		 * certainly better options out there but this one is simple enough for our
		 * needs.)
		 */
		static object ConvertType(FieldInfo field, Type type, string payload, Stack<object> stack)
		{
			if (type == typeof(string))
			{
				return payload;
			}
			if (type == typeof(float) || type == typeof(float?))
			{
				return float.Parse(payload);
			}
			if (type == typeof(int) || type == typeof(int?))
			{
				return int.Parse(payload);
			}
			if (type == typeof(long) || type == typeof(long?))
			{
				return long.Parse(payload);
			}
			if (type == typeof(bool) || type == typeof(bool?))
			{
				return "1".Equals(payload);
			}
			if (type == typeof(LocalDate?))
			{
				return LdtConstants.FORMAT_DATE.Parse(payload).Value;
			}
			if (type == typeof(LocalTime?))
			{
				return LdtConstants.FORMAT_TIME.Parse(payload).Value;
			}
			if (IsNullableEnum(type))
			{
				Type enumType = Nullable.GetUnderlyingType(type);
				MethodInfo method = Type.GetType(enumType.FullName + "Extensions").GetMethod("GetCode");
				if (method != null)
				{
					foreach (object e in Enum.GetValues(enumType))
					{
						string code = (string)method.Invoke(e, new object[] { e });
						if (payload.Equals(code))
						{
							return e;
						}
					}
					return null;
				}
			}
			if (type.IsEnum)
			{
				MethodInfo method = Type.GetType(type.FullName + "Extensions").GetMethod("GetCode");
				if (method != null)
				{
					foreach (object e in Enum.GetValues(type))
					{
						string code = (string)method.Invoke(e, new object[] { e });
						if (payload.Equals(code))
						{
							return e;
						}
					}
					return null;
				}
			}
			Type genericType = GetGenericList(type);
			if (genericType != null)
			{
				object currentObject = PeekCurrentObject(stack);
				var o = (System.Collections.IList) field.GetValue(currentObject);
				if (o == null)
				{
					o = (System.Collections.IList) Activator.CreateInstance(typeof(List<>).MakeGenericType(genericType.GetGenericArguments()[0]));
					field.SetValue(currentObject, o);
				}
				o.Add(ConvertType(field, type.GenericTypeArguments[0], payload, stack));
				return o;
			}
			if (type.GetCustomAttribute<Objekt>() != null)
			{
				object instance = Activator.CreateInstance(type);
				stack.Push(instance);
				FieldInfo declaredField = type.GetField("Value");
				if (declaredField != null)
				{
					declaredField.SetValue(instance, ConvertType(declaredField, declaredField.FieldType, payload, stack));
				}
				return instance;
			}
			throw new ArgumentException("Don't know how to handle type " + type);
		}

		static bool IsNullableEnum(Type t)
		{
			Type u = Nullable.GetUnderlyingType(t);
			return (u != null) && u.IsEnum;
		}

		static Type GetGenericList(Type type)
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
