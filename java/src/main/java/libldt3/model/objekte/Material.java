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
import libldt3.model.enums.AnorganischesMaterial;
import libldt3.model.enums.Materialart;
import libldt3.model.enums.OrganischesMaterial;
import libldt3.model.regel.erlaubt.E012;
import libldt3.model.regel.format.F006;
import libldt3.model.regel.kontext.K006;
import libldt3.model.regel.kontext.K038;
import libldt3.model.regel.kontext.K039;
import libldt3.model.regel.kontext.K063;
import libldt3.model.regel.kontext.K082;

/**
 * Im Objekt werden die Informationen zur Identifikation des zu untersuchenden
 * Materials übermittelt sowie Angaben zum Material selbst.
 */
@Objekt(value = "0037", kontextregeln = {K006.class, K038.class, K039.class, K063.class, K082.class})
public class Material implements Kontext {

    @Objekt(kontextregeln = K038.class)
    public static class ArtMaterial implements Kontext {
        @SuppressWarnings("unused")
        public Materialart value;
        @Feld(value = "7311", feldart = Feldart.bedingt_kann)
        @Regelsatz(laenge = 1)
        public OrganischesMaterial organisches;
        @Feld(value = "7312", feldart = Feldart.bedingt_kann)
        @Regelsatz(laenge = 1)
        public Anorganisches anorganisches;
    }

    @Objekt
    public static class Anorganisches implements Kontext {
        @SuppressWarnings("unused")
        public AnorganischesMaterial value;
        @Feld(value = "8167", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 26)
        public Fliesstext zusaetzlicheInformationen;
    }

    @Objekt
    public static class MedikamenteneinnahmeZumZeitpunktMaterialentnahme implements Kontext {
        @SuppressWarnings("unused")
        public Boolean value;
        @Feld(value = "8170", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 10)
        public Medikament medikament;
    }

    @Objekt
    public static class MengeProbenmaterial implements Kontext {
        @SuppressWarnings("unused")
        public float value;
        @Feld(value = "8421", feldart = Feldart.bedingt_muss)
        @Regelsatz(maxLaenge = 60)
        public String masseinheitMesswerteWertes;
        @Feld(value = "8522", feldart = Feldart.bedingt_kann)
        @Regelsatz(value = F006.class, laenge = 4)
        public String sammelzeitProbenmaterial;
    }

    @Feld(value = "7364", feldart = Feldart.muss)
    @Regelsatz(maxLaenge = 60)
    public String probengefaessIdent;
    @Feld(value = "8429", feldart = Feldart.kann)
    @Regelsatz(value = E012.class, maxLaenge = 4)
    public String probenmaterialIndex;
    @Feld(value = "8428", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 60)
    public String probenmaterialIdent;
    @Feld(value = "8430", feldart = Feldart.bedingt_kann)
    @Regelsatz(maxLaenge = 60)
    public String probenmaterialBezeichnung;
    @Feld(value = "8431", feldart = Feldart.bedingt_kann)
    @Regelsatz(maxLaenge = 60)
    public String probenmaterialSpezifikation;
    @Feld(value = "7292", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 60)
    public String lokalisationProbenmaterial;
    @Feld(value = "7310", feldart = Feldart.bedingt_kann)
    @Regelsatz(laenge = 1)
    public ArtMaterial artMaterial;
    @Feld(value = "8504", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public List<MedikamenteneinnahmeZumZeitpunktMaterialentnahme> medikamenteneinnahmeZumZeitpunktMaterialentnahme;
    @Feld(value = "7318", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 60)
    public List<String> nahrungsaufnahmeZumZeitpunktMaterialentnahme;
    @Feld(value = "8520", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 60)
    public MengeProbenmaterial mengeProbenmaterial;
    @Feld(value = "8219", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 34)
    public Timestamp timestampMaterialabnahmeEntnahme;
    @Feld(value = "8220", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 36)
    public Timestamp timestampEingangserfassung;
    @Feld(value = "8126", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 28)
    public FehlermeldungAufmerksamkeit fehlermeldungAufmerksamkeit;
    @Feld(value = "8167", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 26)
    public List<Fliesstext> zusaetzlicheInformationen;
    @Feld(value = "8110", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 6)
    public List<Anhang> anhang;

}
