/*
 * Copyright 2016-2023  Christoph Brill <opensource@christophbrill.de>
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
package libldt3.model.regel.kontext;

import libldt3.model.Kontext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Wenn Inhalt von FK 8418 = 11 oder FK 7368 vorhanden ist, muss FK 8126 im
 * Obj_0037 vorhanden sein.
 *
 * Wenn auf Grund von fehlendem bzw. nicht verwertbarem Material die
 * Analytik nicht durchgeführt werden konnte, muss der Einsender im Befund
 * darauf aufmerksam gemacht werden.
 */
public class K082 implements Kontextregel {

    private static final Logger LOG = LoggerFactory.getLogger(K082.class);

    @Override
    public boolean isValid(Kontext owner) throws IllegalAccessException {
        throw new UnsupportedOperationException();
    }

}
