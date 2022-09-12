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

/**
 * Dieses Objekt wird als Zusammenfassung aller im Auftrag vorhandenen
 * Abrechnungsarten genutzt. An Hand der hier gemachten Angaben kann bei der
 * Implementierung eine Prüfroutine hinsichtlich der Vollständigkeit der
 * darunterliegenden Objekte eingeführt werden. Pro Satzart "8215" darf dieses
 * Objekt nur einmal vorhanden sein.
 */
@Objekt("0001")
public class Abrechnungsinformation {

	@Feld(value = "8102", name = "Abrechnung_GKV", feldart = Feldart.bedingt_muss)
	@Regelsatz(laenge = 14)
	public List<AbrechnungGkv> abrechnungGkv;
	@Feld(value = "8103", name = "Abrechnung_PKV", feldart = Feldart.bedingt_muss)
	@Regelsatz(laenge = 14)
	public List<AbrechnungPkv> abrechnungPkv;
	@Feld(value = "8104", name = "Abrechnung_IGe-Leistungen", feldart = Feldart.bedingt_muss)
	@Regelsatz(laenge = 25 /* XXX 15 according to spec 3.0.3 */)
	public AbrechnungIgel abrechnungIgel;
	@Feld(value = "8105", name = "Abrechnung_Sonstige_Kostenuebernahme", feldart = Feldart.bedingt_muss)
	@Regelsatz(laenge = 36)
	public AbrechnungSonstigeKostenuebernahme abrechnungSonstigeKostenuebernahme;
	@Feld(value = "8106", name = "Abrechnung_Selektivvertrag", feldart = Feldart.bedingt_muss)
	@Regelsatz(laenge = 26)
	public AbrechnungSelektivvertrag abrechnungSelektivvertrag;

}
