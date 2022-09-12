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
			 * Untersuchungsanforderungen zusammengefasst, welche als IGe-Leistungen
			 * gegenüber gesetzlich versicherten Patienten erbracht werden können.
			 */
			[Objekt(Value = "0004")]
			public class AbrechnungIgel
			{
				[Feld(Value = "4121", Feldart = Feldart.muss)]
				[Regelsatz(Laenge = 1)]
				public Gebuehrenordnung? gebuehrenordnung;
				[Feld(Value = "7253", Feldart = Feldart.muss)]
				[Regelsatz(Laenge = 1)]
				public bool kostenuebernahmeerklaerungAuftraggeberLiegtVor;
				[Feld(Value = "8148", Feldart = Feldart.muss)]
				[Regelsatz(Laenge = 12)]
				public Rechnungsempfaenger rechnungsempfaenger;
			}
		}
	}
}
