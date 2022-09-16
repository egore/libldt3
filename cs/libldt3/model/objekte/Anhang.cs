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
using libldt3.model.regel.kontext;

namespace libldt3
{
	namespace model
	{
		namespace objekte
		{


			/**
			 * Im Objekt Anhang können Informationen wie Befunde, Fotos oder sonstige
			 * Dokumentationen, die in einem digitalen Standardformat vorliegen,
			 * transportiert werden.
			 */
			[Objekt(Value = "0010", Kontextregeln = new[] { typeof(K001), typeof(K075) })]
			public class Anhang
			{

				[Feld(Value = "9970", Feldart = Feldart.muss)]
				[Regelsatz(MaxLaenge = 3)]
				public string dokumentTyp;
				[Feld(Value = "6221", Feldart = Feldart.kann)]
				[Regelsatz(Laenge = 1)]
				public bool? kennzeichnungFremdbefund;
				[Feld(Value = "6305", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(MaxLaenge = 60)]
				public string dateiVerweis;
				[Feld(Value = "8242", Name = "base64-kodierte_Anlage", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(Laenge = 22)]
				public Fliesstext base64Anlage;
				[Feld(Value = "6303", Feldart = Feldart.muss)]
				[Regelsatz(MaxLaenge = 60)]
				public string dateiformat;
				[Feld(Value = "6328", Feldart = Feldart.kann)]
				[Regelsatz(MaxLaenge = 60)]
				public string dateicodierung;
				[Feld(Value = "6327", Feldart = Feldart.bedingt_muss)]
				[Regelsatz(MaxLaenge = 60)]
				public string beschreibung;
				[Feld(Value = "9908", Feldart = Feldart.bedingt_kann)]
				[Regelsatz(MaxLaenge = 60)]
				public string originaldokumentPfad;
				[Feld(Value = "9909", Feldart = Feldart.bedingt_kann)]
				[Regelsatz(MaxLaenge = 60)]
				public string langzeitArchivierungPfad;
				[Feld(Value = "9980", Feldart = Feldart.bedingt_kann)]
				public IList<string> externeDokumentIds;
				[Feld(Value = "9981", Feldart = Feldart.bedingt_kann)]
				[Regelsatz(Laenge = 1)]
				public Dokumentenquelle? dokumentenquelle;

			}
		}
	}
}
