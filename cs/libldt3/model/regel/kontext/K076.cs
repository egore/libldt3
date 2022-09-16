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
using System.Diagnostics;
using System.Reflection;
using System.Collections.Generic;
using static libldt3.model.regel.kontext.KontextregelHelper;
using libldt3.model.enums;

namespace libldt3
{
	namespace model
	{
		namespace regel
		{
			namespace kontext
			{

				public class K076 : Kontextregel
				{

					static readonly ISet<string> FIELDTYPES = new HashSet<string> { "3112", "3121", "3114", "3124" };

					public bool IsValid(object owner)
					{

						IDictionary<string, FieldInfo> fields = FindFieldInfos(owner, FIELDTYPES);
						if (fields.Count != FIELDTYPES.Count)
						{
							Trace.TraceError("Class of {0} must have fields {1}", owner, FIELDTYPES);
							return false;
						}

						TestStatus status = (TestStatus)fields["8418"].GetValue(owner);
						switch (status)
						{
							case TestStatus.Material_fehlt_oder_nicht_verwendbar:
							case TestStatus.Wert_fehlt:
							case TestStatus.Untersuchungsanforderung_storniert:
								return true;
							default:
								return ContainsAnyString(fields["8225"], owner);
						}
					}
				}
			}
		}
	}
}
