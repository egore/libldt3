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
using System; namespace libldt3
{
	namespace model
	{
		namespace enums
		{

			/**
			 * E052
			 */
			public enum Normwertspezifikation
			{

				/** Methodenspezifische Standards nach WHO */
				MethodenspezifischeStandardsWHO,
				/** Methodenspezifische Standards nach IFCC (u.a. serologische Verfahren) */
				MethodenspezifischeStandardsIFCC,
				/** Methodenspezifische Standards nach DGKL */
				MethodenspezifischeStandardsDGKL,
				/** Sonstige Standards */
				SonstigeStandards,
				/** Patientenspezifische Einflussgröße Alter betreffend */
				PatientenspezifischeEinflussgroesseAlter,
				/** Patientenspezifische Einflussgröße Geschlecht betreffend */
				PatientenspezifischeEinflussgroesseGeschlecht,
				/** Patientenspezifische Einflussgröße Alter und Geschlecht betreffend */
				PatientenspezifischeEinflussgroesseAlterGeschlecht,
				/** Patientenspezifische Einflussgröße SSW betreffend */
				PatientenspezifischeEinflussgroesseSSW,
				/** Patientenspezifische Einflussgröße Alter und SSW betreffend */
				PatientenspezifischeEinflussgroesseAlterSSW,
				/** weitere patientenspezifische Einflussgrößen (z.B. Mediaktion) */
				WeiterePatientenspezifischeEinflussgroessen,
				/** Information zu Patientenspezifischer Einflussgröße Alter fehlte */
				InformationPatientenspezifischerEinflussgroesseAlterFehlte,
				/** Information zu Patientenspezifischer Einflussgröße Geschlecht fehlte */
				InformationPatientenspezifischerEinflussgroesseGeschlechtFehlte,
				/** Information zu Patientenspezifischer Einflussgröße Alter und Geschlecht fehlte */
				InformationPatientenspezifischerEinflussgroesseAlterGeschlechtFehlte,
				/** Funktionsprofile */
				Funktionsprofile
			}
			public static class NormwertspezifikationExtensions
			{
				public static string GetCode(this Normwertspezifikation self)
				{
					switch (self)
					{
						case Normwertspezifikation.MethodenspezifischeStandardsWHO: return "10";
						case Normwertspezifikation.MethodenspezifischeStandardsIFCC: return "11";
						case Normwertspezifikation.MethodenspezifischeStandardsDGKL: return "12";
						case Normwertspezifikation.SonstigeStandards: return "13";
						case Normwertspezifikation.PatientenspezifischeEinflussgroesseAlter: return "20";
						case Normwertspezifikation.PatientenspezifischeEinflussgroesseGeschlecht: return "21";
						case Normwertspezifikation.PatientenspezifischeEinflussgroesseAlterGeschlecht: return "22";
						case Normwertspezifikation.PatientenspezifischeEinflussgroesseSSW: return "23";
						case Normwertspezifikation.PatientenspezifischeEinflussgroesseAlterSSW: return "24";
						case Normwertspezifikation.WeiterePatientenspezifischeEinflussgroessen: return "25";
						case Normwertspezifikation.InformationPatientenspezifischerEinflussgroesseAlterFehlte: return "26";
						case Normwertspezifikation.InformationPatientenspezifischerEinflussgroesseGeschlechtFehlte: return "27";
						case Normwertspezifikation.InformationPatientenspezifischerEinflussgroesseAlterGeschlechtFehlte: return "28";
						case Normwertspezifikation.Funktionsprofile: return "30";
						default: throw new Exception();
					}
				}
			}
		}
	}
}
