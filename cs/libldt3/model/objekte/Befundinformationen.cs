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
using System.Collections.Generic;
using libldt3.attributes;
using libldt3.model.enums;
using libldt3.model.regel.kontext;

namespace libldt3
{
	namespace model
	{
		namespace objekte
		{



			/**
			 * Dieses Objekt bündelt alle Daten zum Befund inklusive aller Kennungen, welche
			 * eine eineindeutige Zuordnung von Auftrag und Befund sicherstellen.
			 */
			[Objekt(Value = "0017")]
			public class Befundinformationen
			{

				[Objekt]
				public class OrderNumber_
				{
					public string Value;
					[Feld(Value = "8313", Feldart = Feldart.bedingt_kann)]
					[Regelsatz(MaxLaenge = 60)]
					public IList<string> NachforderungId;
					[Feld(Value = "8214", Name = "Timestamp_Auftragserteilung", Feldart = Feldart.bedingt_kann)]
					[Regelsatz(Laenge = 27)]
					public Timestamp OrderRequestTimestamp;
					[Feld(Value = "8215", Name = "Timestamp_Auftragseingang", Feldart = Feldart.bedingt_kann)]
					[Regelsatz(Laenge = 25)]
					public Timestamp TimestampAuftragseingang;
				}

				[Objekt]
				public class Befundweg_
				{
					public ZusaetzlicherBefundweg? Value;
					[Feld(Value = "8147", Feldart = Feldart.bedingt_muss)]
					[Regelsatz(Laenge = 6)]
					public Person Person;
				}

				[Feld(Value = "8310", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(MaxLaenge = 60)]
				public OrderNumber_ OrderNumber;
				[Feld(Value = "8311", Feldart = Feldart.muss)]
				[Regelsatz(MaxLaenge = 60)]
				public string OrderId;
				[Feld(Value = "7305", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(MaxLaenge = 60)]
				public string FindingId;
				[Feld(Value = "8401", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(Laenge = 1)]
				public Befundtyp? Type;
				[Feld(Value = "0080", Feldart = Feldart.kann)]
				[Regelsatz(MaxLaenge = 60)]
				public string FallakteId;
				[Feld(Value = "0081", Feldart = Feldart.bedingt_kann)]
				[Regelsatz(MaxLaenge = 60)]
				public IList<string> FallakteBezeichnung;
				[Feld(Value = "7258", Feldart = Feldart.kann)]
				[Regelsatz(MaxLaenge = 60)]
				public string KatalogId;
				[Feld(Value = "7251", Feldart = Feldart.bedingt_kann)]
				[Regelsatz(MaxLaenge = 60)]
				public string KatalogBezeichnung;
				[Feld(Value = "4229", Feldart = Feldart.kann)]
				[Regelsatz(Laenge = 5)]
				public IList<string> Ausnahmeindikation;
				[Feld(Value = "8118", Name = "Abweichender_Befundweg", Feldart = Feldart.kann)]
				[Regelsatz(Laenge = 22)]
				public Kommunikationsdaten AbweichenderBefundweg;
				[Feld(Value = "8611", Feldart = Feldart.bedingt_kann)]
				[Regelsatz(Laenge = 1)]
				public IList<Befundweg_> ZusaetzlicherBefundweg;
				[Feld(Value = "7320", Feldart = Feldart.kann)]
				[Regelsatz(Laenge = 1)]
				public bool? RecallEmpfohlen;
				[Feld(Value = "8154", Feldart = Feldart.bedingt_kann)]
				[Regelsatz(Laenge = 9)]
				public Timestamp RecallEmpfohlenTimestamp;
				[Feld(Value = "8216", Name = "Timestamp_Befunderstellung", Feldart = Feldart.muss)]
				[Regelsatz(Laenge = 26)]
				public Timestamp TimestampBefunderstellung;
				[Feld(Value = "8167", Name = "Zusaetzliche_Informationen", Feldart = Feldart.kann)]
				[Regelsatz(Laenge = 26)]
				public IList<Fliesstext> ZusaetzlicheInformationen;
				[Feld(Value = "8110", Feldart = Feldart.kann)]
				[Regelsatz(Laenge = 6)]
				public IList<Anhang> Anhang;
				[Feld(Value = "8126", Name = "Fehlermeldung_Aufmerksamkeit", Feldart = Feldart.kann)]
				[Regelsatz(Laenge = 28)]
				public IList<FehlermeldungAufmerksamkeit> fehlermeldungAufmerksamkeit;
				[Feld(Value = "8141", Feldart = Feldart.kann)]
				[Regelsatz(Laenge = 13)]
				public Namenskennung Namenskennung;

			}
		}
	}
}