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
 */using System;

namespace libldt3
{
	namespace model
	{
		namespace enums
		{

			/**
			 * E007
			 */
			public enum TestStatus
			{
				Analytik_abgeschlossen,
				bereits_berichtet,
				Wert_fehlt,
				korrigierter_Wert,
				Material_fehlt_oder_nicht_verwendbar,
				weiterer_Wert_fuer_Funktionsprofil_folgt,
				Untersuchungsanforderung_storniert,
				Wert_vorlaeufig



			}
			public static class TestStatusExtensions
			{
				public static string GetCode(this TestStatus self)
				{
					switch (self)
					{
						case TestStatus.Analytik_abgeschlossen: return "A";
						case TestStatus.bereits_berichtet: return "B";
						case TestStatus.Wert_fehlt: return "F";
						case TestStatus.korrigierter_Wert: return "K";
						case TestStatus.Material_fehlt_oder_nicht_verwendbar: return "M";
						case TestStatus.weiterer_Wert_fuer_Funktionsprofil_folgt: return "P";
						case TestStatus.Untersuchungsanforderung_storniert: return "S";
						case TestStatus.Wert_vorlaeufig: return "V";
						default: throw new Exception();
					}
				}
			}
		}
	}
}
