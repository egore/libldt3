/*
 * Copyright 2016-2023  Christoph Brill &lt;opensource@christophbrill.de&gt;
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
 * E049
 */
public enum Benachrichtigungsgrund {
    /** Pathologisch auffälliger Befund */
    Pathologisch_auffaelligerBefund("1"),
    /** Lebensbedrohlicher Zustand */
    LebensbedrohlicherZustand("2"),
    /** Wiedervorstellung empfohlen */
    Wiedervorstellung_empfohlen("3"),
    /** Probenmaterial nicht verwendbar */
    Probenmaterial_nicht_verwendbar("4"),
    /** Probenmaterial unvollständig */
    Probenmaterial_unvollstaendig("5"),
    /** Meldung nach KFRG* erfolgt */
    Meldung_nachKFRG_erfolgt("6"),
    /** Meldung nach IfSG** erfolgt
     * *   Krebsfrüherkennungs- und -registergesetz ** Infektionsschutzgesetz Hinweis:
     * Die Information zur erfolgten Meldung nach KFRG bzw. IfSG erfolgt immer im
     * Obj_0026, welches sich in dem Untersuchungsergebnis befindet, dass die
     * Meldepflicht begründet. */
    Meldung_nachIfSG_erfolgt("7");

    public final String code;

    Benachrichtigungsgrund(String code) {
        this.code = code;
    }

}
