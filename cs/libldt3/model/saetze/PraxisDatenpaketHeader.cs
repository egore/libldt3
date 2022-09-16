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
using libldt3.attributes;
using System.Collections.Generic;
using libldt3.model.enums;
using libldt3.model.objekte;

namespace libldt3
{
	namespace model
	{
		namespace saetze
		{

			/**
			 * Satzart: P (Praxis)-Datenpaket-Header "8230"
			 */
			[Datenpaket(Value = Satzart.PraxisDatenpaketHeader)]
			public class PraxisDatenpaketHeader : Satz
			{
				[Feld(Value = "8132", Feldart = Feldart.muss)]
				[Regelsatz(Laenge = 9)]
				public Kopfdaten Kopfdaten;
				[Feld(Value = "7265", Feldart = Feldart.muss)]
				[Regelsatz(Laenge = 1)]
				public DatensatzAbsender? Absender;
				[Feld(Value = "8122", Feldart = Feldart.muss)]
				[Regelsatz(Laenge = 23)]
				public IList<Einsenderidentifikation> Einsenderidentifikation;
			}
		}
	}
}
