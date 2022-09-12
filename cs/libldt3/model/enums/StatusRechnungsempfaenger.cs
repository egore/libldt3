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
			 * E029
			 */
			public enum StatusRechnungsempfaenger
			{
				/** Einsender Arzt */
				EinsenderArzt,
				/** Einsender sonstige */
				EinsenderSonstige,
				/** Versicherter */
				Versicherter,
				/** Rechnungsempfänger */
				Rechnungsempfaenger,
				/** Bevollmächtigter */
				Bevollmaechtigter,
				/** Halter (eines Tieres) */
				Tierhalter,
				/** Patient */
				Patient,
				/** staatliche Einrichtung */
				StaatlicheEinrichtung,
				/** sonstige juristische Person */
				SonstigeJuristischePerson,
				/** sonstige medizinische Einrichtung */
				SonstigeMedizinischeEinrichtung
			}

			public static class StatusRechnungsempfaengerExtensions
			{
				public static string GetCode(this StatusRechnungsempfaenger self)
				{
					switch (self)
					{
						case StatusRechnungsempfaenger.EinsenderArzt: return "02";
						case StatusRechnungsempfaenger.EinsenderSonstige: return "03";
						case StatusRechnungsempfaenger.Versicherter: return "04";
						case StatusRechnungsempfaenger.Rechnungsempfaenger: return "05";
						case StatusRechnungsempfaenger.Bevollmaechtigter: return "06";
						case StatusRechnungsempfaenger.Tierhalter: return "11";
						case StatusRechnungsempfaenger.Patient: return "12";
						case StatusRechnungsempfaenger.StaatlicheEinrichtung: return "15";
						case StatusRechnungsempfaenger.SonstigeJuristischePerson: return "16";
						case StatusRechnungsempfaenger.SonstigeMedizinischeEinrichtung: return "17";
						default: throw new Exception();
					}
				}
			}
		}
	}
}
