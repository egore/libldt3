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

namespace libldt3
{
	namespace model
	{
		namespace objekte
		{
			/**
			 * Dieses Objekt wird als Zusammenfassung aller im Auftrag vorhandenen
			 * Abrechnungsarten genutzt. An Hand der hier gemachten Angaben kann bei der
			 * Implementierung eine Prüfroutine hinsichtlich der Vollständigkeit der
			 * darunterliegenden Objekte eingeführt werden. Pro Satzart "8215" darf dieses
			 * Objekt nur einmal vorhanden sein.
			 */
			[Objekt(Value = "0001")]
			public class Abrechnungsinformation
			{

				[Feld(Value = "8102", Name = "Abrechnung_GKV", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(Laenge = 14)]
				public IList<AbrechnungGkv> abrechnungGkv;
				[Feld(Value = "8103", Name = "Abrechnung_PKV", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(Laenge = 14)]
				public IList<AbrechnungPkv> abrechnungPkv;
				[Feld(Value = "8104", Name = "Abrechnung_IGe-Leistungen", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(Laenge = 25 /* XXX 15 according to spec 3.0.3 */)]
				public AbrechnungIgel abrechnungIgel;
				[Feld(Value = "8105", Name = "Abrechnung_Sonstige_Kostenuebernahme", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(Laenge = 36)]
				public AbrechnungSonstigeKostenuebernahme abrechnungSonstigeKostenuebernahme;
				[Feld(Value = "8106", Name = "Abrechnung_Selektivvertrag", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(Laenge = 26)]
				public AbrechnungSelektivvertrag abrechnungSelektivvertrag;

			}
		}
	}
}
