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
using libldt3.model.enums;
using System.Reflection;

namespace libldt3
{
	namespace model
	{
		namespace regel
		{
			namespace kontext
			{

				public class K004 : Kontextregel
				{

					public bool IsValid(object owner)
					{
						// TODO should also check for FK 8000 = 8205

						FieldInfo source1 = FindFieldInfo(owner, "8401");
						if (source1 == null)
						{
							return true;
						}
						object o1 = source1.GetValue(owner);
						if (o1 == null)
						{
							return true;
						}
						if (!(o1 is string))
						{
							return false;
						}
						if (!"E".Equals(o1) && !"N".Equals(o1))
						{
							return true;
						}

						FieldInfo source2 = FindFieldInfo(owner, "7303");
						if (source2 == null)
						{
							return true;
						}

						object o2 = source2.GetValue(owner);
						if (o2 == null)
						{
							return true;
						}
						if (!(o2 is Abrechnungsinfo))
						{
							return false;
						}

						switch ((Abrechnungsinfo)o2)
						{
							case Abrechnungsinfo.GkvLaborfacharzt:
							case Abrechnungsinfo.GkvLg:
							case Abrechnungsinfo.Asv:
							case Abrechnungsinfo.GkvLaborfacharztPraeventiv:
							case Abrechnungsinfo.GkgLgPraeventiv:
								FieldInfo f = FindFieldInfo(owner, "4241");
								return ContainsAnyString(f, owner);
							default:
								return true;
						}
					}

				}
			}
		}
	}
}

