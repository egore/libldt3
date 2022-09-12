using System;

namespace libldt3
{
	namespace model
	{
		namespace enums
		{

			/**
			 * E054
			 * 
			 * Antikörpersuchtest (gegen Erythrozytenantigene)
			 */
			public enum Antikoerpersuchtest
			{

				positiv,
				negativ,
				unspezifisch,
				/** in Abklärung */
				InAbklaerung,
				/** Abklärung empfohlen */
				AbklaerungEmpfohlen

			}

			public static class AntikoerpersuchtestExtensions
			{
				public static string GetCode(this Antikoerpersuchtest self)
				{
					switch (self)
					{
						case Antikoerpersuchtest.positiv: return "1";
						case Antikoerpersuchtest.negativ: return "2";
						case Antikoerpersuchtest.unspezifisch: return "3";
						case Antikoerpersuchtest.InAbklaerung: return "4";
						case Antikoerpersuchtest.AbklaerungEmpfohlen: return "5";
						default: throw new Exception();
					}
				}
			}
		}
	}
}
