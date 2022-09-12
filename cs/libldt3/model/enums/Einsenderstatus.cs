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
			 * E023
			 */
			public enum Einsenderstatus
			{
				/** Erstveranlasser */
				Erstveranlasser,
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
				/** Laborarzt/Befundersteller */
				Laborarzt_Befundersteller,
				/** Leistungserbringer */
				Leistungserbringer,
				/** Halter (eines Tieres) */
				Tierhalter,
				/** Patient */
				Patient,
				/** Überweiser */
				Ueberweiser,
				/** staatliche Einrichtung */
				staatliche_Einrichtung,
				/** sonstige juristische Person */
				sonstige_juristische_Person,
				/** sonstige medizinische Einrichtung */
				sonstige_medizinische_Einrichtung
			}
			public static class EinsenderstatusExtensions
			{
				public static string GetCode(this Einsenderstatus self)
				{
					switch (self)
					{
						case Einsenderstatus.Erstveranlasser: return "01";
						case Einsenderstatus.EinsenderArzt: return "02";
						case Einsenderstatus.EinsenderSonstige: return "03";
						case Einsenderstatus.Versicherter: return "04";
						case Einsenderstatus.Rechnungsempfaenger: return "05";
						case Einsenderstatus.Bevollmaechtigter: return "06";
						case Einsenderstatus.Laborarzt_Befundersteller: return "07";
						case Einsenderstatus.Leistungserbringer: return "08";
						case Einsenderstatus.Tierhalter: return "11";
						case Einsenderstatus.Patient: return "12";
						case Einsenderstatus.Ueberweiser: return "14";
						case Einsenderstatus.staatliche_Einrichtung: return "15";
						case Einsenderstatus.sonstige_juristische_Person: return "16";
						case Einsenderstatus.sonstige_medizinische_Einrichtung: return "17";
						default: throw new Exception();
					}
				}
			}
		}
	}
}
