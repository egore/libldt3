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
using Microsoft.Extensions.Logging;
using libldt3.model;

namespace libldt3
{
    namespace model
    {
        namespace regel
        {
            namespace kontext
            {
                /// <summary>
                /// Die FK 8310 muss nur dann vorkommen, wenn der Inhalt der FK 8310 im Auftrag
                /// übermittelt wurde.
                /// </summary>
                /// Die Auftragsnummer des Einsenders muss vom Labor im Befund nur
                /// dann zurückübermittelt werden, wenn der Einsender diese bei der
                /// Beauftragung übermittelt hat.
                public class K112 : Kontextregel
                {
                    private static readonly ILogger LOG = LoggerFactory.GetLogger(typeof(K112));
                    private static readonly ISet<string> FIELDTYPES = new HashSet<string> { "8310" };

                    public bool IsValid(Kontext owner)
                    {

                        K112.LOG.Warn("Ignoring rule {}", this.GetType().GetSimpleName())
                        ;
                        return true;
                    }
                }
            }
        }
    }
}
