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
			 * Dieses Objekt soll genutzt werden, wenn es aus Sicht des Auftragsnehmers
			 * Vorkommnisse im Prozess gegeben hat, die eine zusätzliche Benachrichtigung
			 * des Einsenders erfordern.
			 */
			[Objekt(Value = "0026")]
			public class FehlermeldungAufmerksamkeit
			{

				[Feld(Value = "7280", Feldart = Feldart.muss)]
				[Regelsatz(Laenge = 1)]
				public Benachrichtigungsgrund? benachrichtigungsgrund;
				[Feld(Value = "7320", Feldart = Feldart.bedingt_kann)]
				[Regelsatz(Laenge = 1)]
				public bool? recallEmpfohlen;
				[Feld(Value = "8154", Feldart = Feldart.bedingt_kann)]
				[Regelsatz(Laenge = 9)]
				public Timestamp timestamp;
				[Feld(Value = "8147", Feldart = Feldart.muss)]
				[Regelsatz(Laenge = 6)]
				public Person person;
				[Feld(Value = "8167", Name = "Zusaetzliche_Informationen", Feldart = Feldart.kann)]
				[Regelsatz(Laenge = 26)]
				public IList<Fliesstext> text;
				[Feld(Value = "8110", Feldart = Feldart.kann)]
				[Regelsatz(Laenge = 6)]
				public IList<Anhang> anhang;

			}
		}
	}
}

