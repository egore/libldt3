/*
 * Copyright 2016-2017  Christoph Brill <egore911@gmail.com>
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

namespace libldt3
{
	namespace model
	{
		namespace regel
		{
			namespace kontext
			{

				/**
				 * FK 3112 und/oder FK 3121 muss vorhanden sein.
				 */
				public class K017 : Kontextregel
				{

					static readonly ISet<string> FIELDTYPES = new HashSet<string> { "3112", "3121", "3114", "3124" };

					public bool IsValid(object owner)
					{

						IDictionary<string, FieldInfo> fields = FindFieldInfos(owner, FIELDTYPES);
						if (fields.Count != FIELDTYPES.Count)
						{
							Trace.TraceError("Class of {} must have fields {}", owner, FIELDTYPES);
							return false;
						}

						return !checkExclusion(owner, fields, "3114", "3112") &&
								!checkExclusion(owner, fields, "3124", "3121") &&
								(ContainsAnyString(fields["3112"], owner) || ContainsAnyString(fields["3121"], owner));

					}

					bool checkExclusion(object owner, IDictionary<string, FieldInfo> fields, string first, string second)
					{
						string value = (string)fields[first].GetValue(owner);
						// XXX 4109 does not exist on the current object, likely we need to traverse the object tree to find it in one
						// of the holding classes
						if (value != null && !"D".Equals(value) && ContainsAnyString(fields["4109"], owner) &&
								ContainsAnyString(fields[second], owner))
						{
							Trace.TraceError("FK {} is present and not 'D'. Also FK 4109 is present. Then {} must not be present", first, second);
							return true;
						}
						return false;
					}
				}
			}
		}
	}
}
