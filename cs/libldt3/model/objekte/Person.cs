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
using NodaTime;
using System.Collections.Generic;
using libldt3.attributes;
using libldt3.model.enums;
using libldt3.model.regel;

namespace libldt3
{
	namespace model
	{
		namespace objekte
		{
			/**
			 * Mit dem Objekt Person werden alle die natürlichen Personen dargestellt, deren
			 * Daten für die Abwicklung, Abrechnung oder Dokumentation von Aufträgen und
			 * Befun-den notwendig sind.
			 */
			[Objekt(Value = "0047")]
			public class Person
			{
				[Feld(Value = "7420", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(Laenge = 2)]
				public StatusPerson? status;
				[Feld(Value = "3100", Feldart = Feldart.kann)]
				[Regelsatz(MaxLaenge = 20)]
				public string namenszusatz;
				[Feld(Value = "3120", Feldart = Feldart.kann)]
				[Regelsatz(MaxLaenge = 20)]
				public string vorsatzwort;
				[Feld(Value = "3101", Feldart = Feldart.muss)]
				[Regelsatz(MaxLaenge = 45)]
				public string nachname;
				[Feld(Value = "3102", Feldart = Feldart.muss)]
				[Regelsatz(MaxLaenge = 45)]
				public IList<string> vorname;
				[Feld(Value = "3103", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(Value = new[] { typeof(F003) }, Laenge = 8)]
				public LocalDate? geburtsdatum;
				[Feld(Value = "3104", Feldart = Feldart.kann)]
				[Regelsatz(MaxLaenge = 20)]
				public string titel;
				[Feld(Value = "3110", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(Laenge = 1)]
				public Geschlecht? geschlecht;
				[Feld(Value = "3628", Feldart = Feldart.kann)]
				[Regelsatz(MaxLaenge = 60)]
				public string muttersprache;
				[Feld(Value = "8990", Feldart = Feldart.kann)]
				[Regelsatz(MaxLaenge = 60)]
				public string namenskuerzelNamenszeichen;
				[Feld(Value = "8228", Name = "Wohnanschrift", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(Laenge = 13)]
				public Anschrift wohnanschrift;
				[Feld(Value = "8229", Name = "Anschrift_Arbeitsstelle", Feldart = Feldart.kann)]
				[Regelsatz(Laenge = 23)]
				public Anschrift anschriftArbeitsstelle;
				[Feld(Value = "8230", Name = "Rechnungsanschrift", Feldart = Feldart.kann)]
				[Regelsatz(Laenge = 18)]
				public Anschrift rechnungsanschrift;
				[Feld(Value = "8232", Name = "Private_Kommunikationsdaten", Feldart = Feldart.kann)]
				[Regelsatz(Laenge = 27)]
				public Kommunikationsdaten privateKommunikationsdaten;
				[Feld(Value = "8233", Name = "Geschaeftliche_Kommunikationsdaten", Feldart = Feldart.kann)]
				[Regelsatz(Laenge = 34)]
				public Kommunikationsdaten geschaeftlicheKommunikationsdaten;
			}
		}
	}
}
