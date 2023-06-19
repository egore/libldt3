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
package libldt3.model.objekte;

import java.util.List;

import libldt3.annotations.Feld;
import libldt3.annotations.Feldart;
import libldt3.annotations.Objekt;
import libldt3.annotations.Regelsatz;
import libldt3.model.Kontext;

/**
 * Im Objekt werden die Untersuchungsergebnisse zusammengefasst. Hinweis: Die
 * Feldkennungen 8160, 8161, 8162, 8163, 8155, 8248 und 8156 können im Obj_0035 in
 * beliebiger Reihenfolge angeordnet und übertragen werden. Damit wird es möglich,
 * im Obj_0035 die Struktur eines schriftlichen Befundes nachzubilden.
 */
@Objekt("0035")
public class Laborergebnisbericht implements Kontext {

    @Feld(value = "8160", name = "UE_Klinische_Chemie", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 19)
    public List<UntersuchungsergebnisKlinischeChemie> klinischeChemie;
    @Feld(value = "8161", name = "UE_Mikrobiologie", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 16)
    public List<UntersuchungsergebnisMikrobiologie> mikrobiologie;
    @Feld(value = "8162", name = "UE_Zytologie_Krebsvorsorge", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 26)
    public List<UntersuchungsergebnisZytologieKrebsvorsorge> zytologieKrebsvorsorge;
    @Feld(value = "8163", name = "UE_Zytologie", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 12)
    public List<UntersuchungsergebnisZytologie> zytologie;
    @Feld(value = "8155", name = "Transfusionsmedizin_Mutterschaftsvorsorge", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 41)
    public List<TransfusionsmedizinMutterschaftsvorsorge> transfusionsmedizinMutterschaftsvorsorge;
    @Feld(value = "8156", feldart = Feldart.kann)
    @Regelsatz(laenge = 5)
    public List<Tumor> tumor;
    @Feld(value = "8221", name = "Timestamp_Erstellung_Laborergebnisbericht", feldart = Feldart.muss)
    @Regelsatz(laenge = 41)
    public Timestamp timestampErstellungLaborergebnisbericht;
    @Feld(value = "8167", name = "Zusaetzliche_Informationen", feldart = Feldart.kann)
    @Regelsatz(laenge = 26)
    public List<Fliesstext> text;
    @Feld(value = "8110", feldart = Feldart.kann)
    @Regelsatz(laenge = 6)
    public List<Anhang> anhang;
    @Feld(value = "8141", feldart = Feldart.kann)
    @Regelsatz(laenge = 13)
    public Namenskennung namenskennung;

}
