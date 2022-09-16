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

namespace libldt3
{
	namespace model
	{
		namespace enums
		{

			/**
			 * E058
			 */
			public enum DarstellungErgebniswerte
			{

				/** numerisch (exponentielle Darstellung möglich) */
				Numerisch,
				/** numerisch mit Messwertuntergrenze */
				Numerisch_Untergrenze,
				/** numerisch mit Messwertobergrenze */
				Numerisch_Obergrenze,
				/** alpha-numerisch */
				Alphanumerisch,
				/** Titer */
				Titer,
				/** Titer mit Untergrenze */
				Titer_Untergrenze,
				/** Titer mit Obergrenze */
				Titer_Obergrenze,
				/** Sonstige */
				Sonstige
			}

			public static class DarstellungErgebniswerteExtensions
			{
				public static string GetCode(this DarstellungErgebniswerte self)
				{
					switch (self)
					{
						case DarstellungErgebniswerte.Numerisch: return "01";
						case DarstellungErgebniswerte.Numerisch_Untergrenze: return "02";
						case DarstellungErgebniswerte.Numerisch_Obergrenze: return "03";
						case DarstellungErgebniswerte.Alphanumerisch: return "04";
						case DarstellungErgebniswerte.Titer: return "05";
						case DarstellungErgebniswerte.Titer_Untergrenze: return "06";
						case DarstellungErgebniswerte.Titer_Obergrenze: return "07";
						case DarstellungErgebniswerte.Sonstige: return "99";
						default: throw new Exception();
					}
				}
			}
		}
	}
}
