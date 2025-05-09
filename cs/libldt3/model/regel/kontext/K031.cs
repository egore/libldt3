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
                /// Wenn in der Satzart 8215 mehrere Objekte mit FK 8002 = Obj_0002 (Obj_Abrechnung
                /// GKV) vorhanden sind, dann müssen sich diese in der Kombination der Inhalte der
                /// FK 4239/FK 4221 unterscheiden.
                /// </summary>
                /// Beispiel:
                /// FK 4239 = 27/FK 4221 = 1
                /// FK 4239 = 27/FK 4221 = 3
                /// FK 4239 = 28/FK 4221 = 1
                /// FK 4239 = 28/FK 4221 = 2
                public class K031 : Kontextregel
                {
                    private static readonly ILogger LOG = LoggerFactory.GetLogger(typeof(K031));

                    public bool IsValid(Kontext owner)
                    {

                        K031.LOG.Warn("Ignoring rule {}", this.GetType().GetSimpleName())
                        ;
                        return true;
                    }
                }
            }
        }
    }
}
