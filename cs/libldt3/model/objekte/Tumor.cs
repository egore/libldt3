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
using libldt3.model.regel;

namespace libldt3
{
	namespace model
	{
		namespace objekte
		{

			/**
			 * In diesem Objekt können Information zu einem Tumor sowohl für die
			 * Beauftragung und für den Befund transportiert werden.
			 */
			[Objekt(Value = "0056")]
			public class Tumor
			{

				[Feld(Value = "7364", Feldart = Feldart.muss)]
				[Regelsatz(MaxLaenge = 60)]
				public string probengefaessIdent;
				[Feld(Value = "7372", Feldart = Feldart.kann)]
				[Regelsatz(MaxLaenge = 60)]
				public string tumorklassifikation;
				[Feld(Value = "7373", Feldart = Feldart.kann)]
				[Regelsatz(MaxLaenge = 5)]
				public string grading;
				[Feld(Value = "7374", Feldart = Feldart.kann)]
				[Regelsatz(MaxLaenge = 4)]
				public string stadium;
				[Feld(Value = "7375", Feldart = Feldart.kann)]
				[Regelsatz(Value = new[] { typeof(F017) }, Laenge = 4)]
				public string jahrTumordiagnose;
				[Feld(Value = "7376", Feldart = Feldart.muss)]
				[Regelsatz(MaxLaenge = 60)]
				public string lokalisationTumor;
				[Feld(Value = "7377", Feldart = Feldart.kann)]
				[Regelsatz(MaxLaenge = 60)]
				public IList<string> masse;
				[Feld(Value = "7378", Feldart = Feldart.kann)]
				[Regelsatz(MaxLaenge = 60)]
				public string farbe;
				[Feld(Value = "7379", Feldart = Feldart.kann)]
				[Regelsatz(MaxLaenge = 60)]
				public string infiltrationstiefe;
				[Feld(Value = "3424", Feldart = Feldart.kann)]
				[Regelsatz(Value = new[] { typeof(F002) }, Laenge = 8)]
				public LocalDate? therapiebeginn;
				[Feld(Value = "3425", Feldart = Feldart.kann)]
				[Regelsatz(Value = new[] { typeof(F002) }, Laenge = 8)]
				public LocalDate? therapieende;
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
				[Feld(Value = "8225", Name = "Timestamp_Messung", Feldart = Feldart.kann)]
				[Regelsatz(Laenge = 17)]
				public Timestamp timestampMessung;
				[Feld(Value = "8167", Name = "Zusaetzliche_Informationen", Feldart = Feldart.kann)]
				[Regelsatz(Laenge = 26)]
				public IList<Fliesstext> zusaetzlicheInformationen;
				[Feld(Value = "8110", Name = "Anhang", Feldart = Feldart.kann)]
				[Regelsatz(Laenge = 6)]
				public IList<Anhang> anhang;
			}
		}
	}
}
