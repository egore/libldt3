/*
 * Copyright 2016-2022  Christoph Brill <opensource@christophbrill.de>
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
import libldt3.model.enums.Dokumentenquelle;
import libldt3.model.enums.Dokumententyp;
import libldt3.model.regel.kontext.K001;
import libldt3.model.regel.kontext.K075;

/**
 * Im Objekt Anhang können Informationen wie Befunde, Fotos oder sonstige
 * Dokumentationen, die in einem digitalen Standardformat vorliegen,
 * transportiert werden.
 */
@Objekt(value = "0010", kontextregeln = { K001.class, K075.class })
public class Anhang implements Kontext {

    @Feld(value = "9970", feldart = Feldart.muss)
    @Regelsatz(maxLaenge = 3)
    public Dokumententyp dokumentTyp;
    @Feld(value = "6221", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Boolean kennzeichnungFremdbefund;
    @Feld(value = "6305", feldart = Feldart.bedingt_muss)
    @Regelsatz(maxLaenge = 60)
    public String dateiVerweis;
    @Feld(value = "8242", name = "base64-kodierte_Anlage", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 22)
    public Fliesstext base64Anlage;
    @Feld(value = "6303", feldart = Feldart.muss)
    @Regelsatz(maxLaenge = 60)
    public String dateiformat;
    @Feld(value = "6328", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 60)
    public String dateicodierung;
    @Feld(value = "6327", feldart = Feldart.bedingt_muss)
    @Regelsatz(maxLaenge = 60)
    public String beschreibung;
    @Feld(value = "9908", feldart = Feldart.bedingt_kann)
    @Regelsatz(maxLaenge = 60)
    public String originaldokumentPfad;
    @Feld(value = "9909", feldart = Feldart.bedingt_kann)
    @Regelsatz(maxLaenge = 60)
    public String langzeitArchivierungPfad;
    @Feld(value = "9980", feldart = Feldart.bedingt_kann)
    public List<String> externeDokumentIds;
    @Feld(value = "9981", feldart = Feldart.bedingt_kann)
    @Regelsatz(laenge = 1)
    public Dokumentenquelle dokumentenquelle;

}
