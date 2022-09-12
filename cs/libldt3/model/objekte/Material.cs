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
using libldt3.model.regel;
using libldt3.model.regel.kontext;

namespace libldt3
{
	namespace model
	{
		namespace objekte
		{
			/**
			 * Im Objekt werden die Informationen zur Identifikation des zu untersuchenden
			 * Materials übermittelt sowie Angaben zum Material selbst.
			 */
			[Objekt(Value = "0037", Kontextregeln = new[] { typeof(K006) })]
			public class Material
			{

				[Objekt]
				public class AnorganischesMaterialErweitert
				{
					public AnorganischesMaterial? Value;
					[Feld(Value = "8167", Name = "Zusaetzliche_Informationen", Feldart = Feldart.bedingt_kann)]
					[Regelsatz(Laenge = 26)]
					public Fliesstext ZusaetzlicheInformatioen;
				}

				[Objekt]
				public class Medikamenteneinnahme_
				{
					public bool? Value;
					[Feld(Value = "8170", Feldart = Feldart.bedingt_kann)]
					[Regelsatz(Laenge = 10)]
					public Medikament Medication;
				}

				[Feld(Value = "7364", Feldart = Feldart.muss)]
				[Regelsatz(MaxLaenge = 60)]
				public string ProbengefaessIdent;
				[Feld(Value = "8429", Feldart = Feldart.kann)]
				[Regelsatz(MaxLaenge = 4)]
				public string ProbenmaterialIndex;
				[Feld(Value = "8428", Feldart = Feldart.kann)]
				[Regelsatz(MaxLaenge = 60)]
				public string ProbenmaterialIdent;
				[Feld(Value = "8430", Feldart = Feldart.bedingt_kann)]
				[Regelsatz(MaxLaenge = 60)]
				public string ProbenmaterialBezeichnung;
				[Feld(Value = "8431", Feldart = Feldart.bedingt_kann)]
				[Regelsatz(MaxLaenge = 60)]
				public string ProbenmaterialSpezifikation;
				[Feld(Value = "7292", Feldart = Feldart.kann)]
				[Regelsatz(MaxLaenge = 60)]
				public string LokalisationProbenmaterial;
				[Feld(Value = "7310", Feldart = Feldart.bedingt_kann)]
				[Regelsatz(Laenge = 1)]
				public Materialart? Materialart;
				[Feld(Value = "7311", Feldart = Feldart.bedingt_kann)]
				[Regelsatz(Laenge = 1)]
				public OrganischesMaterial? OrganischesMaterial;
				[Feld(Value = "7312", Feldart = Feldart.bedingt_kann)]
				[Regelsatz(Laenge = 1)]
				public AnorganischesMaterialErweitert AnorganischesMaterial;
				[Feld(Value = "8504", Feldart = Feldart.kann)]
				[Regelsatz(Laenge = 1)]
				public IList<Medikamenteneinnahme_> Medikamenteneinnahme;
				[Feld(Value = "7318", Feldart = Feldart.kann)]
				[Regelsatz(MaxLaenge = 60)]
				public IList<string> NahrungsaufnahmeZeitpunktMaterialentnahme;
				[Feld(Value = "8520", Feldart = Feldart.kann)]
				[Regelsatz(MaxLaenge = 60)]
				public string Menge;
				[Feld(Value = "8421", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(MaxLaenge = 20)]
				public string Einheit;
				[Feld(Value = "8522", Feldart = Feldart.bedingt_kann)]
				[Regelsatz(Value = new[] { typeof(F006) }, Laenge = 4)]
				public string Sammelzeit;
				[Feld(Value = "8219", Name = "Timestamp_Materialabnahme_entnahme", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(Laenge = 34 /* XXX 33 according to the spec */)]
				public Timestamp TimestampMaterialabnahmeEntnahme;
				[Feld(Value = "8220", Name = "Timestamp_Eingangserfassung_Material", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(Laenge = 36)]
				public Timestamp TimestampEingangserfassungMaterial;
				[Feld(Value = "8126", Name = "Fehlermeldung_Aufmerksamkeit", Feldart = Feldart.kann)]
				[Regelsatz(Laenge = 28)]
				public FehlermeldungAufmerksamkeit FehlermeldungAufmerksamkeit;
				[Feld(Value = "8167", Name = "Zusaetzliche_Informationen", Feldart = Feldart.kann)]
				[Regelsatz(Laenge = 26)]
				public IList<Fliesstext> ZusaetzlicheInformationen;
				[Feld(Value = "8110", Feldart = Feldart.kann)]
				[Regelsatz(Laenge = 6)]
				public IList<Anhang> Anhang;

			}
		}
	}
}
