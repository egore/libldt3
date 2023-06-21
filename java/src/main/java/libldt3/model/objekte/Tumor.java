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

import java.time.LocalDate;
import java.util.List;

import libldt3.annotations.Feld;
import libldt3.annotations.Feldart;
import libldt3.annotations.Objekt;
import libldt3.annotations.Regelsatz;
import libldt3.model.Kontext;
import libldt3.model.regel.format.F002;
import libldt3.model.regel.format.F017;

/**
 * In diesem Objekt können Information zu einem Tumor sowohl für die Beauftragung
 * und für den Befund transportiert werden.
 */
@Objekt("0056")
public class Tumor implements Kontext {

    @Feld(value = "7364", feldart = Feldart.muss)
    @Regelsatz(maxLaenge = 60)
    public String probengefaessIdent;
    @Feld(value = "7372", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 60)
    public String tumorklassifikation;
    @Feld(value = "7373", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 5)
    public String grading;
    @Feld(value = "7374", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 5)
    public String stadium;
    @Feld(value = "7375", feldart = Feldart.kann)
    @Regelsatz(value = F017.class, laenge = 4)
    public String jahrTumordiagnose;
    @Feld(value = "7376", feldart = Feldart.muss)
    @Regelsatz(maxLaenge = 60)
    public String lokalisationTumor;
    @Feld(value = "7377", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 60)
    public List<String> masse;
    @Feld(value = "7378", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 60)
    public String farbe;
    @Feld(value = "7379", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 60)
    public String infiltrationstiefe;
    @Feld(value = "3424", feldart = Feldart.kann)
    @Regelsatz(value = F002.class, laenge = 8)
    public LocalDate therapiebeginn;
    @Feld(value = "3425", feldart = Feldart.kann)
    @Regelsatz(value = F002.class, laenge = 8)
    public LocalDate therapieende;
    @Feld(value = "8220", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 36)
    public Timestamp timestampEingangserfassungMaterial;
    @Feld(value = "8222", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 25)
    public Timestamp timestampBeginnAnalytik;
    @Feld(value = "8223", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 28)
    public Timestamp timestampErgebniserstellung;
    @Feld(value = "8224", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 22)
    public Timestamp timestampQmErfassung;
    @Feld(value = "8225", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 17)
    public Timestamp timestampMessung;
    @Feld(value = "8126", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 28)
    public FehlermeldungAufmerksamkeit fehlermeldungAufmerksamkeit;
    @Feld(value = "8167", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 26)
    public List<Fliesstext> zusaetzlicheInformationen;
    @Feld(value = "7429", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 990)
    public String drgHinweis;
    @Feld(value = "3473", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Boolean untersuchungsergebnisDurchAuftragslaboratoriumErstellt;
    @Feld(value = "8110", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 6)
    public List<Anhang> anhang;

}
