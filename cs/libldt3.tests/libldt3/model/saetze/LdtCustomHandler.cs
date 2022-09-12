using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using de.egore911.fuzz;
using libldt3.attributes;
using libldt3.model.enums;
using libldt3.model.objekte;
using libldt3.model.regel;

namespace libldt3
{
	namespace model
	{
		namespace saetze
		{
			class LdtCustomHandler : Fuzzer.CustomHandler
			{
				readonly Random random = new Random();

				public object randomValue(FieldInfo field)
				{
					var annotation = field.GetCustomAttribute<Feld>();
					if (annotation != null)
					{
						foreach (Regelsatz regelsatz in field.GetCustomAttributes<Regelsatz>())
						{
							foreach (Type klass in regelsatz.Value)
							{
								if (klass == typeof(F014))
								{
									return "001234561";
								}
								if (klass == typeof(F011))
								{
									if (field.FieldType.IsAssignableFrom(typeof(IList<string>)))
									{
										return new List<string> { "123456112" };
									}
									if (field.FieldType.IsAssignableFrom(typeof(string)))
									{
										return "123456112";
									}
								}
								if (klass == typeof(F010))
								{
									return "0123456[]";
								}
								if (klass == typeof(F013))
								{
									return "X012345671";
								}
								if (klass == typeof(F005))
								{
									return "896";
								}
								if (klass == typeof(F001))
								{
									return "12345";
								}
								if (klass == typeof(F006))
								{
									return "9959";
								}
								if (klass == typeof(F015))
								{
									return "012/987";
								}
								if (klass == typeof(F017))
								{
									return "0123";
								}
								if (klass == typeof(F004))
								{
									return "C12";
								}
								if (klass == typeof(F007))
								{
									return LdtVersion.LDT3_0_6;
								}
								if (klass == typeof(F012))
								{
									return "X/31/0101/01/[]}";
								}
								if (klass == typeof(F020))
								{
									return "01234567";
								}
								if (klass == typeof(F009))
								{
									var gebuehrennummer = new Untersuchungsabrechnung.Gebuehrennummer();
									gebuehrennummer.Value = "01234";
									return new List<Untersuchungsabrechnung.Gebuehrennummer> { gebuehrennummer };
								}
							}
							if (regelsatz.Laenge >= 0)
							{
								var x = getRandomAtLength(field, regelsatz.Laenge);
								if (x != null) return x;
							}
							else if (regelsatz.MaxLaenge >= 0)
							{
								int min = 0;
								int max = regelsatz.MaxLaenge;
								if (regelsatz.MinLaenge >= 0)
								{
									min = regelsatz.MinLaenge;
									max -= min;
								}
								var x = getRandomAtLength(field, min + random.Next(max));
								if (x != null) return x;
							}
						}
					}
					return null;
				}

				object getRandomAtLength(FieldInfo field, int laenge)
				{
					if (field.FieldType.IsAssignableFrom(typeof(int?)))
					{
						if (laenge == 1)
						{
							// chosen by fair dice roll
							return 4;
						}
						return (int)Math.Pow(10, laenge - 1);
					}
					if (field.FieldType.IsAssignableFrom(typeof(string)))
					{
						return randomAlphanumeric(laenge);
					}
					if (field.FieldType.IsAssignableFrom(typeof(IList<string>)))
					{
						return new List<string> { randomAlphanumeric(laenge) };
					}
					return null;
				}

				public string randomAlphanumeric(int length)
				{
					const string chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
					return new string(Enumerable.Repeat(chars, length)
					  .Select(s => s[random.Next(s.Length)]).ToArray());
				}
			}
		}
	}
}
