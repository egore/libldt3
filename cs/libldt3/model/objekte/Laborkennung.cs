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
			 * Das Objekt enthält die Angaben zu dem Labor, welches den Auftrag ausgeführt hat.
			 */
			[Objekt(Value = "0036")]
			public class Laborkennung
			{

				[Feld(Value = "8239", Name = "Laborbezeichnung", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(Laenge = 16)]
				public Organisation laborbezeichnung;
				[Feld(Value = "7352", Feldart = Feldart.kann)]
				[Regelsatz(MaxLaenge = 60)]
				public IList<string> katalogUrl;
				[Feld(Value = "8324", Feldart = Feldart.kann)]
				[Regelsatz(MaxLaenge = 60)]
				public string laborStandortId;
				[Feld(Value = "7266", Feldart = Feldart.muss)]
				[Regelsatz(Laenge = 1)]
				public Laborart? laborart;
			}
		}
	}
}
