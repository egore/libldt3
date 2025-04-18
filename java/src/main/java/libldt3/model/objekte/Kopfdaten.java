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

import libldt3.annotations.Feld;
import libldt3.annotations.Feldart;
import libldt3.annotations.Objekt;
import libldt3.annotations.Regelsatz;
import libldt3.model.Kontext;
import libldt3.model.enums.LdtVersion;
import libldt3.model.regel.format.F007;

/**
 * Hier werden alle Informationen zusammengefasst, die im Kontext mit der
 * Erstellung des Datensatzes stehen.
 */
@Objekt("0032")
public class Kopfdaten implements Kontext {

    @Feld(value = "0001", feldart = Feldart.muss)
    @Regelsatz(value = F007.class, maxLaenge = 12)
    public LdtVersion versionDatensatzbeschreibung;
    @Feld(value = "8151", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 16)
    public SendendesSystem sendendesSystem;
    @Feld(value = "8218", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 30)
    public Timestamp timestampErstellungDatensatz;
    @Feld(value = "8212", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 24)
    public Organisation softwareverantwortlicher;

}
