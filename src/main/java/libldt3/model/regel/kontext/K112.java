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
package libldt3.model.regel.kontext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Die Auftragsnummer des Einsenders muss vom Labor im Befund nur dann zurückübermittelt werden, wenn der Einsender
 * diese bei der Beauftragung übermittelt hat.
 */
public class K112 implements Kontextregel {

    private static final Logger LOG = LoggerFactory.getLogger(K112.class);

    @Override
    public boolean isValid(Object owner) throws IllegalAccessException {
        // Currently impossible to implement as the objects don't know anything about the requests and diagnosis
        LOG.error("Kontext rule K112 not yet supported");
        return true;
    }

}
