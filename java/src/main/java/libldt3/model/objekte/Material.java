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
import libldt3.model.enums.AnorganischesMaterial;
import libldt3.model.enums.Materialart;
import libldt3.model.enums.OrganischesMaterial;
import libldt3.model.regel.F006;
import libldt3.model.regel.erlaubt.E012;
import libldt3.model.regel.kontext.K006;

/**
 * Im Objekt werden die Informationen zur Identifikation des zu untersuchenden
 * Materials übermittelt sowie Angaben zum Material selbst.
 */
@Objekt(value = "0037", kontextregeln = K006.class)
public class Material implements Kontext {

    @Objekt
    public static class AnorganischesMaterialErweitert implements Kontext {
        @SuppressWarnings("unused")
        public AnorganischesMaterial value;
        @Feld(value = "8167", name = "Zusaetzliche_Informationen", feldart = Feldart.bedingt_kann)
        @Regelsatz(laenge = 26)
        public Fliesstext zusaetzlicheInformatioen;
    }

    @Objekt
    public static class Medikamenteneinnahme implements Kontext {
        @SuppressWarnings("unused")
        public Boolean value;
        @Feld(value = "8170", feldart = Feldart.bedingt_kann)
        @Regelsatz(laenge = 10)
        public Medikament medication;
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
    public Materialart materialart;
    @Feld(value = "7311", feldart = Feldart.bedingt_kann)
    @Regelsatz(laenge = 1)
    public OrganischesMaterial organischesMaterial;
    @Feld(value = "7312", feldart = Feldart.bedingt_kann)
    @Regelsatz(laenge = 1)
    public AnorganischesMaterialErweitert anorganischesMaterial;
    @Feld(value = "8504", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public List<Medikamenteneinnahme> medikamenteneinnahme;
    @Feld(value = "7318", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 60)
    public List<String> nahrungsaufnahmeZeitpunktMaterialentnahme;
    @Feld(value = "8520", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 60)
    public String menge;
    @Feld(value = "8421", feldart = Feldart.bedingt_muss)
    @Regelsatz(maxLaenge = 20)
    public String einheit;
    @Feld(value = "8522", feldart = Feldart.bedingt_kann)
    @Regelsatz(value = F006.class, laenge = 4)
    public String sammelzeit;
    @Feld(value = "8219", name = "Timestamp_Materialabnahme_entnahme", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 34 /* XXX 33 according to the spec */)
    public Timestamp timestampMaterialabnahmeEntnahme;
    @Feld(value = "8220", name = "Timestamp_Eingangserfassung_Material", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 36)
    public Timestamp timestampEingangserfassungMaterial;
    @Feld(value = "8126", name = "Fehlermeldung_Aufmerksamkeit", feldart = Feldart.kann)
    @Regelsatz(laenge = 28)
    public FehlermeldungAufmerksamkeit fehlermeldungAufmerksamkeit;
    @Feld(value = "8167", name = "Zusaetzliche_Informationen", feldart = Feldart.kann)
    @Regelsatz(laenge = 26)
    public List<Fliesstext> zusaetzlicheInformationen;
    @Feld(value = "8110", feldart = Feldart.kann)
    @Regelsatz(laenge = 6)
    public List<Anhang> anhang;

}
