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
 */using System.Collections.Generic;
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
			 * In diesem Objekt können weitere Ergebnisse aus dem Bereich Zytologie transportiert werden.
			 */
			[Objekt(Value = "0063", Kontextregeln = new[] { typeof(K076)})]
			public class UntersuchungsergebnisZytologie
			{

				[Objekt]
				public class RecallEmpfohlen
				{
					public string Value;
					[Feld(Value = "8154", Feldart = Feldart.bedingt_kann)]
					[Regelsatz(Laenge = 9)]
					public Timestamp timestamp;
				}

				[Objekt]
				public class KatalogReferenz
				{
					public KatalogIdAnforderbareLeistungen? Value;
					[Feld(Value = "7352", Feldart = Feldart.bedingt_muss)]
					[Regelsatz(MaxLaenge = 60)]
					public string katalogUrl;
					[Feld(Value = "7251", Feldart = Feldart.bedingt_kann)]
					[Regelsatz(MaxLaenge = 60)]
					public string katalogBezeichnung;
					[Feld(Value = "7365", Feldart = Feldart.bedingt_muss)]
					[Regelsatz(MaxLaenge = 20)]
					public string analysenId;
					[Feld(Value = "7366", Feldart = Feldart.bedingt_kann)]
					[Regelsatz(MaxLaenge = 60)]
					public string leistungsbezeichnung;
				}

				[Objekt]
				public class GrenzwertindikatorLaborwert
				{
					public Grenzwertindikator? Value;
					[Feld(Value = "8126", Name = "Fehlermeldung_Aufmerksamkeit", Feldart = Feldart.bedingt_muss)]
					[Regelsatz(Laenge = 28)]
					public FehlermeldungAufmerksamkeit fehlermeldungAufmerksamkeit;
				}

				[Objekt]
				public class Test
				{
					public string Value;
					[Feld(Value = "8411", Feldart = Feldart.bedingt_muss)]
					[Regelsatz(MaxLaenge = 60)]
					public string testbezeichnung;
					[Feld(Value = "8418", Feldart = Feldart.bedingt_muss)]
					[Regelsatz(Laenge = 1)]
					public TestStatus? teststatus;
					[Feld(Value = "8422", Feldart = Feldart.bedingt_muss)]
					[Regelsatz(MaxLaenge = 2)]
					public IList<GrenzwertindikatorLaborwert> grenzwertindikatorLaborwert;
				}

				[Feld(Value = "7304", Feldart = Feldart.muss)]
				[Regelsatz(MaxLaenge = 60)]
				public string ergebnisId;
				[Feld(Value = "7320", Feldart = Feldart.bedingt_kann)]
				[Regelsatz(Laenge = 1)]
				public IList<RecallEmpfohlen> recallEmpfohlen;
				[Feld(Value = "7364", Feldart = Feldart.muss)]
				[Regelsatz(MaxLaenge = 60)]
				public IList<string> probengefaessIdent;
				[Feld(Value = "7260", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(Laenge = 1)]
				public KatalogReferenz katalogIdAnforderbareLeistungen;
				[Feld(Value = "8410", Feldart = Feldart.muss)]
				[Regelsatz(MaxLaenge = 60)]
				public IList<Test> testIdent;
				[Feld(Value = "8237", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(Laenge = 10 /* XXX 12 according to spec */)]
				public Fliesstext ergebnistext;
				[Feld(Value = "7368", Feldart = Feldart.kann)]
				[Regelsatz(Laenge = 1)]
				public bool? zellmaterialNichtVerwertbar;
				[Feld(Value = "7400", Feldart = Feldart.kann)]
				[Regelsatz(Laenge = 1)]
				public string hpvBefund;
				[Feld(Value = "7401", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(Laenge = 1)]
				public Ergebnis? highRisk;
				[Feld(Value = "7402", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(MaxLaenge = 2)]
				public IList<string> highRiskTyp;
				[Feld(Value = "7403", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(Laenge = 1)]
				public Ergebnis? lowRisk;
				[Feld(Value = "7404", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(MaxLaenge = 2)]
				public IList<string> lowRiskTyp;
				[Feld(Value = "7418", Feldart = Feldart.kann)]
				[Regelsatz(Laenge = 1)]
				public Ergebnis? p16Ki67;
				[Feld(Value = "7419", Feldart = Feldart.kann)]
				[Regelsatz(Laenge = 1)]
				public Ergebnis? l1;
				[Feld(Value = "7422", Feldart = Feldart.kann)]
				[Regelsatz(Laenge = 1)]
				public Ergebnis? chlamydien;
				[Feld(Value = "7425", Feldart = Feldart.kann)]
				[Regelsatz(Laenge = 1)]
				public Ergebnis2? extragynaekologischeZytologie;
				[Feld(Value = "7426", Feldart = Feldart.kann)]
				[Regelsatz(Laenge = 1)]
				public Ergebnis? neisseriaGonorrhoeae;
				[Feld(Value = "8126", Name = "Fehlermeldung_Aufmerksamkeit", Feldart = Feldart.kann)]
				[Regelsatz(Laenge = 28)]
				public FehlermeldungAufmerksamkeit fehlermeldungAufmerksamkeit;
				[Feld(Value = "8220", Name = "Timestamp_Eingangserfassung_Material", Feldart = Feldart.kann)]
				[Regelsatz(Laenge = 36)]
				public Timestamp timestampEingangserfassungMaterial;
				[Feld(Value = "8222", Name = "Timestamp_Beginn_Analytik", Feldart = Feldart.kann)]
				[Regelsatz(Laenge = 25)]
				public Timestamp timestampBeginnAnalytik;
				[Feld(Value = "8223", Name = "Timestamp_Ergebniserstellung", Feldart = Feldart.kann)]
				[Regelsatz(Laenge = 28)]
				public Timestamp timestampErgebniserstellung;
				[Feld(Value = "8224", Name = "Timestamp_QM_Erfassung", Feldart = Feldart.kann)]
				[Regelsatz(Laenge = 22)]
				public Timestamp timestampQmErfassung;
				[Feld(Value = "8225", Name = "Timestamp_Messung", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(Laenge = 17)]
				public Timestamp timestampMessung;
				[Feld(Value = "8167", Name = "Zusaetzliche_Informationen", Feldart = Feldart.kann)]
				[Regelsatz(Laenge = 26)]
				public IList<Fliesstext> zusaetzlicheInformationen;
				[Feld(Value = "8110", Feldart = Feldart.kann)]
				[Regelsatz(Laenge = 6)]
				public IList<Anhang> anhang;
				[Feld(Value = "8141", Feldart = Feldart.muss)]
				[Regelsatz(Laenge = 13)]
				public Namenskennung namenskennung;
				[Feld(Value = "8158", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(Laenge = 23)]
				public Untersuchungsabrechnung untersuchungsabrechnung;

			}
		}
	}
}
