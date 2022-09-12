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
using System;

namespace libldt3
{
	namespace attributes
	{

		/**
		 * Annotation zur Definition eines Feldes. Der {@link LdtReader} liest die Daten
		 * automatisch in die annotierten Felder, der {@link LdtWriter} schreibt
		 * ausschließlich die annotierten Felder.
		 */
		[AttributeUsage(AttributeTargets.Field, Inherited = false, AllowMultiple = false)]
		public class Feld : Attribute
		{

			/**
			 * @return FK der Spezifikation (z.B. "7331" für Mobiltelefonnummer)
			 */			public string Value;

			/**
			 * @return gibt an, ob es sich um ein Pflichtfeld handelt
			 */
			public Feldart Feldart;

			/**
			 * @return Name des Objektes im LDT (notwendig für das Schreiben von LDT
			 *         durch den {@link LdtWriter})
			 */
			public string Name = "";
		}
	}
}
