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
			 * In diesem Objekt wird ein Antibiogramm (Matrix) aus dem Bereich Mikrobiologie
			 * transportiert. Die Darstellung des Antibiogramms erfolgt als mehrdimensionale
			 * Matrix.
			 */
			[Objekt(Value = "0011")]
			public class Antibiogramm
			{
				[Objekt]
				public class WirkstoffIdent
				{
					public string Value;
					[Feld(Value = "7288", Feldart = Feldart.bedingt_kann)]
					[Regelsatz(MaxLaenge = 60)]
					public IList<string> WirkstoffGenericNummer;
					[Feld(Value = "7359", Feldart = Feldart.bedingt_kann)]
					[Regelsatz(MaxLaenge = 60)]
					public IList<string> WirkstoffOid;
					[Feld(Value = "7370", Feldart = Feldart.bedingt_kann)]
					[Regelsatz(MaxLaenge = 60)]
					public IList<string> Wirkstoffname;
					[Feld(Value = "7354", Feldart = Feldart.kann)]
					[Regelsatz(MaxLaenge = 60)]
					public IList<KeimIdentifizierung> keimIdentifizierung;
				}

				[Objekt]
				public class KeimIdentifizierung
				{
					public string Value;
					[Feld(Value = "7367", Feldart = Feldart.bedingt_muss)]
					[Regelsatz(Laenge = 1)]
					public Sensitivitaet? Sensitivitaet;
					[Feld(Value = "7289", Feldart = Feldart.bedingt_kann)]
					[Regelsatz(MaxLaenge = 60)]
					public string Mhk;
					[Feld(Value = "7369", Feldart = Feldart.bedingt_kann)]
					[Regelsatz(MaxLaenge = 60)]
					public string MhkEinheit;
					[Feld(Value = "7290", Feldart = Feldart.kann)]
					[Regelsatz(Laenge = 1)]
					public IList<ResistenzInterpretationErweitert> ResistenzInterpretation;
				}

				[Objekt]
				public class ResistenzInterpretationErweitert
				{
					public ResistenzInterpretation? Value;
					[Feld(Value = "7424", Feldart = Feldart.kann)]
					[Regelsatz(Laenge = 1)]
					public ResistenzNach? ResistenzNach;
				}

				[Feld(Value = "7287", Feldart = Feldart.muss)]
				[Regelsatz(MaxLaenge = 60)]
				public IList<WirkstoffIdent> wirkstoffIdent;
				[Feld(Value = "8237", Name = "Ergebnistext", Feldart = Feldart.bedingt_kann)]
				[Regelsatz(Laenge = 12)]
				public Fliesstext ergebnistext;

			}
		}
	}
}
