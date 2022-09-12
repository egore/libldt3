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
using libldt3.attributes;
using libldt3.model.regel;

namespace libldt3
{
	namespace model
	{
		namespace objekte
		{
			/**
			 * Ein Zeitstempel
			 */
			[Objekt(Value = "0054")]
			public class Timestamp
			{
				[Feld(Value = "7278", Feldart = Feldart.muss)]
				[Regelsatz(Value = new[] { typeof(F002) }, Laenge = 8)]
				public LocalDate? datum;
				[Feld(Value = "7279", Feldart = Feldart.kann)]
				[Regelsatz(Value = new[] { typeof(F016) }, MinLaenge = 6, MaxLaenge = 9)]
				public LocalTime? uhrzeit;
				[Feld(Value = "7272", Feldart = Feldart.kann)]
				[Regelsatz(MaxLaenge = 60)]
				public string freitext;
				[Feld(Value = "8235", Name = "Person_zum_Timestamp", Feldart = Feldart.kann)]
				[Regelsatz(Laenge = 20)]
				public Person person;
			}
		}
	}
}
