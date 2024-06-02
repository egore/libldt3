/*
 * Copyright 2016-2024  Christoph Brill <opensource@christophbrill.de>
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
using Microsoft.Extensions.Logging;
using libldt3.model;
using libldt3.model.enums;

namespace libldt3
{
    namespace model
    {
        namespace regel
        {
            namespace kontext
            {
                /// <summary>
                /// Wenn Feldinhalt von FK 8419 = 1 oder 2, muss FK 8421 vorkommen.
                /// </summary>
                /// Wenn Feldinhalt
                /// von FK 8419 = 9, darf FK 8421 nicht vorkommen.
                /// 
                /// Wenn zu einem Ergebniswert Maßeinheit angegeben wird, muss
                /// angegeben werden, ob es sich bei der Maßeinheit um eine konventionelle
                /// oder SI-Einheit handelt. Wenn zu einem Ergebniswert keine Maßeinheit
                /// angegeben wird, muss angegeben werden, dass es sich bei dem
                /// Ergebniswert um eine sogenannte "dimensionslose Größe" handelt.
                public class K002 : Kontextregel
                {
                    private static readonly ILogger LOG = LoggerFactory.GetLogger(typeof(K002));
                    private static readonly ISet<string> FIELDTYPES = new HashSet<string> { "8419", "8421" };

                    public bool IsValid(Kontext owner)
                    {
                        IDictionary<string, FieldInfo> fields = KontextregelHelper.FindFields(owner, K002.FIELDTYPES);
                        if (fields.Count != K002.FIELDTYPES.Count)
                        {
                            Trace.TraceError("Class of {0} must have fields {1}", owner, K002.FIELDTYPES);
                            return false;
                        }

                        EinheitMesswert? feld8419 = (EinheitMesswert?)KontextregelHelper.GetFieldValue(fields["8419"], owner);
                        if (feld8419 == null)
                        {
                            return true;
                        }

                        // Wenn Feldinhalt von FK 8419 = 1 oder 2, muss FK 8421 vorkommen
                        if (feld8419 == EinheitMesswert.SI_Einheit || feld8419 == EinheitMesswert.abweichendeEinheit)
                        {
                            if (!KontextregelHelper.ContainsAnyValue(fields["8421"], owner))
                            {
                                return false;
                            }

                        }

                        // Wenn Feldinhalt von FK 8419 = 9, darf FK 8421 nicht vorkommen
                        if (feld8419 == EinheitMesswert.dimensionsloseGroesse)
                        {
                            if (!KontextregelHelper.ContainsAnyValue(fields["8421"], owner))
                            {
                                return false;
                            }

                        }

                        return true;
                    }
                }
            }
        }
    }
}
