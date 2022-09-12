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
using libldt3.model.enums;
using libldt3.model.saetze;
using static libldt3.model.regel.kontext.KontextregelHelper;

namespace libldt3
{
	namespace model
	{
		namespace regel
		{
			namespace kontext
			{


				public class K005 : Kontextregel
				{

					static readonly ISet<string> fieldtypes = new HashSet<string> { "4121", "7303" };

					bool Kontextregel.IsValid(object owner)
					{

						Befund befund = (Befund)owner;

						FieldInfo field = FindFieldInfo(befund.Befundinformationen, "8401");
						if (field == null)
						{
							Trace.TraceError("Class of {0} must have field 8401", befund.Befundinformationen);
							return false;
						}

						Befundtyp befundtyp = (Befundtyp)field.GetValue(befund.Befundinformationen);

						if (befundtyp != Befundtyp.Endbefund || befundtyp != Befundtyp.Nachforderungsendbefund)
						{
							IDictionary<object, IList<FieldInfo>> findFieldsRecursive2 = FindFieldInfosRecursive(owner, fieldtypes);

							foreach (KeyValuePair<object, IList<FieldInfo>> entry in findFieldsRecursive2)
							{
								if (entry.Value.Count != 2)
								{
									// Likely a different type, e.g. Veranladssungsgrund
									continue;
								}

								Abrechnungsinfo abrechnungsinfo;
								object other;
								object o1 = entry.Value[0].GetValue(entry.Key);
								object o2 = entry.Value[1].GetValue(entry.Key);
								if (o1 is Abrechnungsinfo)
								{
									abrechnungsinfo = (Abrechnungsinfo)o1;
									other = o2;
								}
								else
								{
									other = o1;
									abrechnungsinfo = (Abrechnungsinfo)o2;
								}

								if (abrechnungsinfo == Abrechnungsinfo.GkvLaborfacharzt || abrechnungsinfo == Abrechnungsinfo.GkvLg ||
										abrechnungsinfo == Abrechnungsinfo.Asv || abrechnungsinfo == Abrechnungsinfo.GkvLaborfacharztPraeventiv ||
										abrechnungsinfo == Abrechnungsinfo.GkgLgPraeventiv)
								{

									if (ContainsAnyString(other))
									{
										return false;
									}
								}
							}
						}

						return true;
					}

				}
			}
		}
	}
}