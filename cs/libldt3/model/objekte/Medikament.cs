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
			 * Hier werden Informationen zu Medikamenten zusammengefasst.
			 */
			[Objekt(Value = "0070")]
			public class Medikament
			{
				[Feld(Value = "8243", Name = "Timestamp_Zeitpunkt_Medikamenteneinnahme", Feldart = Feldart.kann)]
				[Regelsatz(Laenge = 40)]
				public Timestamp TimestampZeitpunktMedikamenteneinnahme;
				[Feld(Value = "6208", Feldart = Feldart.muss)]
				[Regelsatz(MaxLaenge = 60)]
				public string Handelsname;
				[Feld(Value = "6207", Feldart = Feldart.kann)]
				[Regelsatz(MaxLaenge = 60)]
				public string Rezeptur;
				[Feld(Value = "8171", Feldart = Feldart.kann)]
				[Regelsatz(Laenge = 9)]
				public IList<Wirkstoff> Wirkstoff;
				[Feld(Value = "8523", Feldart = Feldart.kann)]
				[Regelsatz(MaxLaenge = 60)]
				public string Wirkstoffmenge;
				[Feld(Value = "8421", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(MaxLaenge = 20)]
				public string Einheit;
				[Feld(Value = "3689", Feldart = Feldart.kann)]
				[Regelsatz(Laenge = 1)]
				public IList<MedikationsStatus> Status;
				[Feld(Value = "8226", Name = "Timestamp_Gueltig_ab", Feldart = Feldart.kann)]
				[Regelsatz(Laenge = 20)]
				public Timestamp TimestampGueltigAb;
				[Feld(Value = "8227", Name = "Timestamp_Gueltig_bis", Feldart = Feldart.kann)]
				[Regelsatz(Laenge = 21)]
				public Timestamp TimestampGueltigBis;
				[Feld(Value = "8167", Name = "Zusaetzliche_Informationen", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(Laenge = 26)]
				public Fliesstext ZusaetzlicheInformationen;
			}
		}
	}
}
