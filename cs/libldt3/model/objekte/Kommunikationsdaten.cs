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
			 * Hier werden alle Informationen zusammengefasst, die eine Kommunikation bspw.
			 * mit einer Einrichtung, Firma, Arzt, einem Patienten ermöglichen.
			 */
			[Objekt(Value = "0031")]
			public class Kommunikationsdaten
			{

				[Objekt]
				public class ElektronischePostadresse_
				{
					public string Value;
					[Feld(Value = "7340", Feldart = Feldart.bedingt_muss)]
					[Regelsatz(MaxLaenge = 60)]
					public string Spezifizierung;
				}

				[Feld(Value = "7330", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(MaxLaenge = 60)]
				public IList<string> Phone;
				[Feld(Value = "7331", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(MaxLaenge = 60)]
				public IList<string> Mobile;
				[Feld(Value = "7332", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(MaxLaenge = 60)]
				public IList<ElektronischePostadresse_> ElektronischePostadresse;
				[Feld(Value = "7333", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(MaxLaenge = 60)]
				public IList<string> Fax;
				[Feld(Value = "7335", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(MaxLaenge = 60)]
				public IList<string> Email;
				[Feld(Value = "7334", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(MaxLaenge = 60)]
				public IList<string> Website;
			}
		}
	}
}