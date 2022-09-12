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
using static libldt3.model.regel.kontext.KontextregelHelper;
using System.Reflection;
using System.Collections.Generic;
using System.Diagnostics;

namespace libldt3
{
	namespace model
	{
		namespace regel
		{
			namespace kontext
			{

				/// <summary>
				/// Wenn FK 8428 oder FK 8430 oder FK 8429 vorhanden ist, darf FK 8431 vorhanden sein.
				/// </summary>
				public class K006 : Kontextregel
				{

					static readonly ISet<string> FIELDTYPES = new HashSet<string> { "8428", "8430", "8429", "8431" };

					public bool IsValid(object owner)
					{

						IDictionary<string, FieldInfo> fields = FindFieldInfos(owner, FIELDTYPES);
						if (fields.Count != FIELDTYPES.Count)
						{
							Trace.TraceError("Class of {} must have fields {}", owner, FIELDTYPES);
							return false;
						}

						if (ContainsAnyString(fields["8428"], owner) ||
							ContainsAnyString(fields["8430"], owner) ||
							ContainsAnyString(fields["8429"], owner))
						{
							return true;
						}

						return !ContainsAnyString(fields["8431"], owner);
					}

				}
			}
		}
	}
}
