/*
 * Copyright 2016  Christoph Brill <egore911@gmail.com>
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
import libldt3.model.enums.AnforderungNothilfepass;
import libldt3.model.enums.Antikoerpersuchtest;
import libldt3.model.enums.DirekterCoombstest;
import libldt3.model.enums.TestStatus;
import libldt3.model.regel.kontext.K076;

@Objekt(value = "0055", kontextregeln = K076.class)
public class TransfusionsmedizinMutterschaftsvorsorge {

	@Feld(value = "7304", feldart = Feldart.muss)
	@Regelsatz(maxLaenge = 60)
	public String ergebnisId;
	@Feld(value = "7364", feldart = Feldart.muss)
	@Regelsatz(maxLaenge = 60)
	public List<String> probengefaessIdent;
	@Feld(value = "8418", feldart = Feldart.muss)
	@Regelsatz(laenge = 1)
	public TestStatus teststatus;
	@Feld(value = "3412", feldart = Feldart.bedingt_muss)
	@Regelsatz(laenge = 6)
	public String blutgruppeEurocode;
	@Feld(value = "3413", feldart = Feldart.kann)
	@Regelsatz(laenge = 1)
	public Antikoerpersuchtest antikoerpersuchtest;
	@Feld(value = "3414", feldart = Feldart.kann)
	@Regelsatz(maxLaenge = 60)
	public String spezifitaetWeitereErythrozytenantigene;
	@Feld(value = "3415", feldart = Feldart.kann)
	@Regelsatz(maxLaenge = 60)
	public String spezifitaetErythrozytenantikoerper;
	@Feld(value = "3416", feldart = Feldart.kann)
	@Regelsatz(maxLaenge = 60)
	public String spezifitaetHlaHpaHnaAntigene;
	@Feld(value = "3417", feldart = Feldart.kann)
	@Regelsatz(maxLaenge = 60)
	public String spezifitaetHlaHpaHnaAntikoerper;
	@Feld(value = "7263", feldart = Feldart.kann)
	@Regelsatz(maxLaenge = 60)
	public String testId;
	@Feld(value = "3418", feldart = Feldart.kann)
	@Regelsatz(laenge = 1)
	public DirekterCoombstest direkterCoombstest;
	@Feld(value = "3419", feldart = Feldart.kann)
	@Regelsatz(maxLaenge = 60)
	public List<String> ergebnisKreuzprobe;
	@Feld(value = "7275", feldart = Feldart.kann)
	@Regelsatz(maxLaenge = 60)
	public List<String> terminologieId;
	@Feld(value = "3420", feldart = Feldart.kann)
	@Regelsatz(laenge = 1)
	public AnforderungNothilfepass anforderungNothilfepass;
	@Feld(value = "8220", name = "Timestamp_Eingangserfassung_Material", feldart = Feldart.kann)
	@Regelsatz(laenge = 36)
	public Timestamp timestampEingangserfassungMaterial;
	@Feld(value = "8222", name = "Timestamp_Beginn_Analytik", feldart = Feldart.kann)
	@Regelsatz(laenge = 25)
	public Timestamp timestampBeginnAnalytik;
	@Feld(value = "8223", name = "Timestamp_Ergebniserstellung", feldart = Feldart.kann)
	@Regelsatz(laenge = 28)
	public Timestamp timestampErgebniserstellung;
	@Feld(value = "8224", name = "Timestamp_QM_Erfassung", feldart = Feldart.kann)
	@Regelsatz(laenge = 22)
	public Timestamp timestampQmErfassung;
	@Feld(value = "8225", name = "Timestamp_Messung", feldart = Feldart.bedingt_muss)
	@Regelsatz(laenge = 17)
	public Timestamp timestampMessung;
	@Feld(value = "8167", name = "Zusaetzliche_Informationen", feldart = Feldart.kann)
	@Regelsatz(laenge = 26)
	public List<Fliesstext> zusaetzlicheInformationen;
	@Feld(value = "8158", feldart = Feldart.bedingt_muss)
	@Regelsatz(laenge = 23)
	public Untersuchungsabrechnung untersuchungsabrechnung;

}
