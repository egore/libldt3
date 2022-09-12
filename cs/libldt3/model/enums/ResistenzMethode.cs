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
			 * E059
			 */
			public enum ResistenzMethode
			{
				/** kein Antibiogramm erstellt */
				KeinAntibiogrammErstellt,
				/** Agardiffusion */
				Agardiffusion,
				/** Agardilution */
				Agardilution,
				/** PCR + Hybridisierung */
				PcrUndHybridisierung,
				/** sonstige */
				sonstige
			}

			public static class ResistenzMethodeExtensions
			{
				public static string GetCode(this ResistenzMethode self)
				{
					switch (self)
					{
						case ResistenzMethode.KeinAntibiogrammErstellt: return "0";
						case ResistenzMethode.Agardiffusion: return "1";
						case ResistenzMethode.Agardilution: return "2";
						case ResistenzMethode.PcrUndHybridisierung: return "3";
						case ResistenzMethode.sonstige: return "4";
						default: throw new Exception();
					}
				}
			}
		}
	}
}
