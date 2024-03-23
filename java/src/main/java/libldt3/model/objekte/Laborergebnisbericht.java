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
import libldt3.model.regel.kontext.K009;

/**
 * Im Objekt werden die Untersuchungsergebnisse zusammengefasst. Hinweis: Die
 * Feldkennungen 8160, 8161, 8162, 8163, 8155, 8248 und 8156 können im Obj_0035 in
 * beliebiger Reihenfolge angeordnet und übertragen werden. Damit wird es möglich,
 * im Obj_0035 die Struktur eines schriftlichen Befundes nachzubilden.
 */
@Objekt(value = "0035", kontextregeln = K009.class)
public class Laborergebnisbericht implements Kontext {

    @Feld(value = "8160", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 19)
    public List<UntersuchungsergebnisKlinischeChemie> ueKlinischeChemie;
    @Feld(value = "8161", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 16)
    public List<UntersuchungsergebnisMikrobiologie> ueMikrobiologie;
    @Feld(value = "8162", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 38)
    public List<UntersuchungsergebnisKrebsfrueherkennungZervixKarzinom> ueKrebsfrueherkennungZervixKarzinom;
    @Feld(value = "8163", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 12)
    public List<UntersuchungsergebnisZytologie> ueZytologie;
    @Feld(value = "8155", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 25)
    public List<Blutgruppenzugehoerigkeit> blutgruppenzugehoerigkeit;
    @Feld(value = "8248", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 35)
    public List<SonstigeUntersuchungsergebnisse> ueSonstigeUntersuchungsergebnisse;
    @Feld(value = "8156", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 5)
    public List<Tumor> tumor;
    @Feld(value = "8221", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 41)
    public Timestamp timestampErstellung;
    @Feld(value = "8167", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 26)
    public List<Fliesstext> zusaetzlicheInformationen;
    @Feld(value = "8110", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 6)
    public List<Anhang> anhang;
    @Feld(value = "8141", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 13)
    public Namenskennung namenskennung;

}
