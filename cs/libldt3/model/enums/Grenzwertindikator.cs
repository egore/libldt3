/*
 * Copyright 2016-2017  Christoph Brill <egore911@gmail.com>
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
			 * E005
			 */
			public enum Grenzwertindikator
			{
				/** im Normalbereich */
				N,
				/** schwach erhöht */
				H,
				/** schwach erhöht */
				PLUS,
				/** stark erhöht */
				HH,
				/** stark erhöht */
				PLUS_PLUS,
				/** schwach erniedrigt */
				L,
				/** schwach erniedrigt */
				MINUS,
				/** stark erniedrigt */
				LL,
				/** stark erniedrigt */
				MINUS_MINUS,
				/** Wert extrem erhöht */
				EXTREM_H,
				/** Wert extrem erhöht */
				EXTREM_PLUS,
				/** Wert extrem erniedrigt */
				EXTREM_L,
				/** Wert extrem erniedrigt */
				EXTREM_MINUS,
				/** auffällig */
				A,
				/** sehr auffällig */
				AA
			}

			public static class GrenzwertindikatorExtensions
			{
				public static string GetCode(this Grenzwertindikator self)
				{
					switch (self)
					{
						case Grenzwertindikator.N: return "N";
						case Grenzwertindikator.H: return "H";
						case Grenzwertindikator.PLUS: return "+";
						case Grenzwertindikator.HH: return "HH";
						case Grenzwertindikator.PLUS_PLUS: return "++";
						case Grenzwertindikator.L: return "L";
						case Grenzwertindikator.MINUS: return "-";
						case Grenzwertindikator.LL: return "LL";
						case Grenzwertindikator.MINUS_MINUS: return "--";
						case Grenzwertindikator.EXTREM_H: return "!H";
						case Grenzwertindikator.EXTREM_PLUS: return "!+";
						case Grenzwertindikator.EXTREM_L: return "!L";
						case Grenzwertindikator.EXTREM_MINUS: return "!-";
						case Grenzwertindikator.A: return "A";
						case Grenzwertindikator.AA: return "AA";
						default: throw new Exception();
					}
				}
			}
		}
	}
}
