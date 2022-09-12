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
using libldt3.model.regel.kontext;

namespace libldt3
{
	namespace model
	{
		namespace objekte
		{

			/**
			 * In diesem Objekt werden die Ergebnisse aus dem Bereich Zytologie
			 * Krebsvorsorge transportiert. Die Inhalte richten sich nach dem Muster 39b.
			 */
			[Objekt(Value = "0062", Kontextregeln = new[] { typeof(K076)})]
			public class UntersuchungsergebnisZytologieKrebsvorsorge
			{

				[Objekt(Kontextregeln = new[] { typeof(K099) })]
				public class TestIdent
				{
					public string Value;
					[Feld(Value = "8411", Feldart = Feldart.bedingt_muss)]
					[Regelsatz(MaxLaenge = 60)]
					public string testbezeichnung;
					[Feld(Value = "8422", Feldart = Feldart.bedingt_muss)]
					[Regelsatz(MaxLaenge = 2)]
					public IList<GrenzwertindikatorErweitert> grenzwertindikator;
					[Feld(Value = "8237", Name = "Ergebnistext", Feldart = Feldart.bedingt_muss)]
					[Regelsatz(Laenge = 12)]
					public Fliesstext ergebnistextVerweis;
				}

				[Feld(Value = "7304", Feldart = Feldart.muss)]
				[Regelsatz(MaxLaenge = 60)]
				public string ergebnisId;
				[Feld(Value = "7364", Feldart = Feldart.muss)]
				[Regelsatz(MaxLaenge = 60)]
				public IList<string> probengefaessIdent;
				[Feld(Value = "8410", Feldart = Feldart.muss)]
				[Regelsatz(MaxLaenge = 60)]
				public TestIdent testIdent;
				[Feld(Value = "8418", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(Laenge = 1)]
				public TestStatus? teststatus;
				[Feld(Value = "7368", Feldart = Feldart.kann)]
				[Regelsatz(Laenge = 1)]
				public bool? zellmaterialNichtVerwertbar;
				[Feld(Value = "7405", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(Laenge = 1)]
				public EndozervikaleZellen? endozervikaleZellen;
				[Feld(Value = "7406", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(MaxLaenge = 10)]
				public string proliferationsgrad;
				[Feld(Value = "7407", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(Laenge = 1)]
				public bool? doederleinflora;
				[Feld(Value = "7408", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(Laenge = 1)]
				public bool? mischflora;
				[Feld(Value = "7409", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(Laenge = 1)]
				public bool? kokkenflora;
				[Feld(Value = "7410", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(Laenge = 1)]
				public bool? trichomonaden;
				[Feld(Value = "7411", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(Laenge = 1)]
				public bool? candida;
				[Feld(Value = "7412", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(Laenge = 1)]
				public bool? gardnerella;
				[Feld(Value = "7414", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(MaxLaenge = 5)]
				public string gruppe;
				[Feld(Value = "7413", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(MaxLaenge = 4)]
				public string codierungGruppe;
				[Feld(Value = "7415", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(Laenge = 1)]
				public bool? zytologischeKontrolle;
				[Feld(Value = "7416", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(Laenge = 1)]
				public NachkontrollGrund? nachkontrollGrund;
				[Feld(Value = "7417", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(Laenge = 1)]
				public IList<bool?> histologischeKlaerung;
				[Feld(Value = "8237", Feldart = Feldart.kann)]
				[Regelsatz(Laenge = 10 /* XXX 12 according to spec */)]
				public Fliesstext ergebnistext;
				[Feld(Value = "8134", Name = "Krebsfrueherkennung_Frauen", Feldart = Feldart.kann)]
				[Regelsatz(Laenge = 26)]
				public KrebsfrueherkennungFrauen krebsfrueherkennungFrauen;
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
				[Feld(Value = "8141", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(Laenge = 13)]
				public Namenskennung namenskennung;
				[Feld(Value = "8158", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(Laenge = 23)]
				public Untersuchungsabrechnung untersuchungsabrechnung;

			}
		}
	}
}
