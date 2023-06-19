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
package libldt3.model.enums;

/**
 * E005
 */
public enum Grenzwertindikator {
    /** im Normalbereich */
    imNormalbereich("N"),
    /** schwach erhöht, */
    schwach_erhoeht("H"),
    /** schwach erhöht, */
    schwach_erhoeht_("+"),
    /** stark erhöht, */
    stark_erhoeht("HH"),
    /** stark erhöht, */
    stark_erhoeht_("++"),
    /** schwach erniedrigt, */
    schwach_erniedrigt("L"),
    /** schwach erniedrigt, */
    schwach_erniedrigt_("-"),
    /** stark erniedrigt, */
    stark_erniedrigt("LL"),
    /** stark erniedrigt, */
    stark_erniedrigt_("–"),
    /** Wert extrem erhöht, */
    Wert_extrem_erhoeht("!H"),
    /** Wert extrem erhöht, */
    Wert_extrem_erhoeht_("!+"),
    /** Wert extrem erniedrigt. */
    Wert_extrem_erniedrigt_("!L"),
    /** Wert extrem erniedrigt.
     * Bei nicht numerischen Werten: */
    Wert_extrem_erniedrigt__("!-"),
    /** normal (anzuwenden bei nicht numerischen Werten), */
    normal("N"),
    /** auffällig (anzuwenden bei nicht numerischen Werten), */
    auffaellig("A"),
    /** sehr auffällig (anzuwenden bei nicht numerischen Werten). */
    sehr_auffaellig_("AA");

    public final String code;

    Grenzwertindikator(String code) {
        this.code = code;
    }

}
