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
using System.Collections.Generic;
using libldt3.attributes;
using libldt3.model.enums;

namespace libldt3
{
	namespace model
	{
		namespace objekte
		{

			/**
			 * Mit diesem Objekt werden die Informationen für die Abrechnung von
			 * Untersuchungsanforderungen zusammengefasst, die über die GOÄ abgerechnet
			 * werden. Das Objekt bezieht sich auf Patienten, welche bei privaten
			 * Krankenkassen versichert sind. Dabei kann der Rechnungsempfänger aber auch
			 * ein anderer sein, als der Versicherte.
			 */
			[Objekt(Value = "0003")]
			public class AbrechnungPkv
			{

				[Feld(Value = "7362", Feldart = Feldart.muss)]
				[Regelsatz(Laenge = 1)]
				public AbrechnungsartPkv? abrechnungsartPkv;
				[Feld(Value = "4134", Feldart = Feldart.kann)]
				[Regelsatz(MaxLaenge = 28)]
				public IList<string> kostentraegerName;
				[Feld(Value = "4121", Feldart = Feldart.muss)]
				[Regelsatz(Laenge = 1)]
				public Gebuehrenordnung? gebuehrenordnung;
				[Feld(Value = "4202", Feldart = Feldart.bedingt_kann)]
				[Regelsatz(Laenge = 1)]
				public bool? unfallFolgen;
				[Feld(Value = "8148", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(Laenge = 12)]
				public Rechnungsempfaenger rechnungsempfaenger;

			}
		}
	}
}
