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

namespace libldt3
{
	namespace model
	{
		namespace objekte
		{
			/**
			 * Mit diesem Objekt können Angaben zum Grund der Veranlassung der
			 * laboratoriumsmedizinischen Untersuchung übertragen werden.
			 */
			[Objekt(Value = "0027")]
			public class Veranlassungsgrund
			{

				[Objekt]
				public class AbrechnungsinfoErweitert
				{

					public Abrechnungsinfo? Value;
					[Feld(Value = "8417", Feldart = Feldart.kann)]
					[Regelsatz(Laenge = 2)]
					public Untersuchungsanlass? anlass;
					[Feld(Value = "8427", Feldart = Feldart.bedingt_kann)]
					[Regelsatz(Laenge = 2)]
					public SpezifizierungVeranlassungsgrund? spezifizierung;
					[Feld(Value = "8217", Name = "Praezisierung_Veranlassungsgrund", Feldart = Feldart.bedingt_kann)]
					[Regelsatz(Laenge = 32)]
					public Fliesstext praezisierung;
					[Feld(Value = "8200", Name = "Akutdiagnose", Feldart = Feldart.bedingt_kann)]
					[Regelsatz(Laenge = 12)]
					public IList<Diagnose> akutDiagnose;
					[Feld(Value = "4208", Feldart = Feldart.kann)]
					[Regelsatz(MaxLaenge = 60)]
					public IList<Medikation> vorbefundMedikation;
				}

				[Objekt]
				public class Medikation
				{
					public string Value;
					[Feld(Value = "6212", Feldart = Feldart.bedingt_kann)]
					[Regelsatz(MaxLaenge = 60)]
					public IList<Arzneimittelwirkstoff> arzneimittelwirkstoff;
				}

				[Objekt]
				public class Arzneimittelwirkstoff
				{
					public string Value;
					[Feld(Value = "6214", Feldart = Feldart.bedingt_muss)]
					[Regelsatz(MaxLaenge = 60)]
					public string wirkstoffKlassifikation;
				}

				[Feld(Value = "7303", Feldart = Feldart.bedingt_kann)]
				[Regelsatz(MaxLaenge = 2)]
				public IList<AbrechnungsinfoErweitert> abrechnungsinfo;
				[Feld(Value = "8110", Feldart = Feldart.kann)]
				[Regelsatz(Laenge = 6)]
				public IList<Anhang> anhang;

			}
		}
	}
}
