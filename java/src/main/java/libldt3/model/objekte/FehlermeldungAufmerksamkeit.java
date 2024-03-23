/*
 * Copyright 2016-2024  Christoph Brill &lt;opensource@christophbrill.de&gt;
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
package libldt3.model.objekte;

import java.util.List;

import libldt3.annotations.Feld;
import libldt3.annotations.Feldart;
import libldt3.annotations.Objekt;
import libldt3.annotations.Regelsatz;
import libldt3.model.Kontext;
import libldt3.model.enums.Benachrichtigungsgrund;

/**
 * Dieses Objekt soll genutzt werden, wenn es aus Sicht des Auftragsnehmers
 * Vorkommnisse im Prozess gegeben hat, die eine zusätzliche Benachrichtigung des
 * Einsenders erfordern.
 */
@Objekt("0026")
public class FehlermeldungAufmerksamkeit implements Kontext {

    @Objekt
    public static class GrundBenachrichtigung implements Kontext {
        @SuppressWarnings("unused")
        public Benachrichtigungsgrund value;
        @Feld(value = "7320", feldart = Feldart.bedingt_kann)
        @Regelsatz(laenge = 1)
        public RecallEmpfohlen recallEmpfohlen;
    }

    @Objekt
    public static class RecallEmpfohlen implements Kontext {
        @SuppressWarnings("unused")
        public Boolean value;
        @Feld(value = "8154", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 9)
        public Timestamp timestamp;
    }

    @Feld(value = "7280", feldart = Feldart.muss)
    @Regelsatz(laenge = 1)
    public List<GrundBenachrichtigung> grundBenachrichtigung;
    @Feld(value = "8147", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 6)
    public Person person;
    @Feld(value = "8167", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 26)
    public List<Fliesstext> zusaetzlicheInformationen;
    @Feld(value = "8110", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 6)
    public List<Anhang> anhang;

}
