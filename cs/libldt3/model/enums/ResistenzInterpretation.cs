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

namespace libldt3
{
	namespace model
	{
		namespace enums
		{

			/**
			 * E030
			 */
			public enum ResistenzInterpretation
			{

				/** nicht getestet */
				nichtGetestet,
				/** sensibel/wirksam */
				sensibelWirksam,
				/** mäßig sensibel/schwach wirksam */
				maessigSensibelSchwachWirksam,
				/** resistent/unwirksam */
				resistentUnwirksam,
				/** wirksam in hohen Konzentrationen */
				wirksamInHohenKonzentrationen
			}

			public static class ResistenzInterpretationExtensions
			{
				public static string GetCode(this ResistenzInterpretation self)
				{
					switch (self)
					{
						case ResistenzInterpretation.nichtGetestet: return "0";
						case ResistenzInterpretation.sensibelWirksam: return "1";
						case ResistenzInterpretation.maessigSensibelSchwachWirksam: return "2";
						case ResistenzInterpretation.resistentUnwirksam: return "3";
						case ResistenzInterpretation.wirksamInHohenKonzentrationen: return "4";
						default: throw new Exception();
					}
				}
			}
		}
	}
}
