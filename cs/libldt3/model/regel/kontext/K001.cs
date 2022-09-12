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
using System.Diagnostics;
using System.Reflection;
using static libldt3.model.regel.kontext.KontextregelHelper;

namespace libldt3
{
	namespace model
	{
		namespace regel
		{
			namespace kontext
			{

				/// <summary>
				/// Entweder FK 6305 oder FK 8242 ist vorhanden.
				/// </summary>
				public class K001 : Kontextregel
				{

					static readonly ISet<string> FIELDTYPES = new HashSet<string> { "6305", "8242" };

					public bool IsValid(object owner)
					{

						IDictionary<string, FieldInfo> fields = FindFieldInfos(owner, FIELDTYPES);
						if (fields.Count != FIELDTYPES.Count) {
							Trace.TraceError("Class of {0} must have fields {1}", owner, FIELDTYPES);
							return false;
						}

						foreach (FieldInfo f in fields.Values) {
							if (ContainsAnyString(f, owner))
							{
								return true;
							}
						}
						return false;
					}
				}
			}
		}
	}
}