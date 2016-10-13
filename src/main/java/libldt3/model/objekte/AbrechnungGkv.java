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

import java.time.LocalDate;
import java.util.List;

import libldt3.annotations.Feld;
import libldt3.annotations.Feldart;
import libldt3.annotations.Objekt;
import libldt3.annotations.Regelsatz;
import libldt3.model.enums.Behandlungsanlass;
import libldt3.model.enums.BesonderePersonengruppe;
import libldt3.model.enums.DmpKennzeichnung;
import libldt3.model.enums.KostentraegerAbrechnungsbereich;
import libldt3.model.enums.Scheinuntergruppe;
import libldt3.model.enums.Versichertenart;
import libldt3.model.enums.WOP;
import libldt3.model.regel.F001;
import libldt3.model.regel.F002;
import libldt3.model.regel.F010;
import libldt3.model.regel.F011;
import libldt3.model.regel.F022;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * Hier werden alle Angaben für die Abrechnung von Untersuchungsanforderungen in
 * der GKV gegenüber der KV hinterlegt. Der Patient ist in der gesetzlichen
 * Kranken-versicherung pflichtversichert oder freiwillig versichert. Der
 * Auftrag für die geplanten Untersuchungen erfolgt über Muster 10/Muster
 * 10A/Muster 39.
 * </p>
 * <p>
 * Mit diesem Objekt werden die Informationen für die Abrechnung von
 * Untersuchungsanforderungen zusammengefasst, die im Regelleistungskatalog der
 * Krankenkassen vorhanden sind oder anderweitig z.B. über eDMP dem Patienten
 * zugeordnet werden können.
 * </p>
 */
@Objekt("0002")
public @Getter @Setter class AbrechnungGkv {

	@Feld(value = "4239", feldart = Feldart.muss, regelsaetze = @Regelsatz(laenge = 2))
	private Scheinuntergruppe scheinuntergruppe;
	@Feld(value = "4134", feldart = Feldart.muss, regelsaetze = @Regelsatz(maxLaenge = 28))
	private String kostentraegername;
	@Feld(value = "4104", feldart = Feldart.muss, regelsaetze = @Regelsatz(value = F001.class, laenge = 5))
	private String abrechnungsVknr;
	@Feld(value = "4106", feldart = Feldart.muss, regelsaetze = @Regelsatz(laenge = 2))
	private KostentraegerAbrechnungsbereich kostentraegerAbrechnungsbereich;
	@Feld(value = "4108", feldart = Feldart.kann, regelsaetze = @Regelsatz(maxLaenge = 60))
	private String zulassungsnummer;
	@Feld(value = "3116", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(laenge = 2))
	private WOP wop;
	@Feld(value = "3108", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(laenge = 1))
	private Versichertenart versichertenart;
	@Feld(value = "4109", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(value = F002.class, laenge = 8))
	private LocalDate letzterEinlesetagVersichertenkarteImQuartal;
	@Feld(value = "4133", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(value = F002.class, laenge = 8))
	private LocalDate versicherungsschutzBeginn;
	@Feld(value = "4110", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(value = F002.class, laenge = 8))
	private LocalDate versicherungsschutzEnde;
	@Feld(value = "4111", feldart = Feldart.muss, regelsaetze = @Regelsatz(laenge = 9))
	private String kstentraegerkennung;
	@Feld(value = "4229", feldart = Feldart.bedingt_kann, regelsaetze = @Regelsatz(laenge = 5))
	private List<String> ausnahmeindikation;
	@Feld(value = "4122", feldart = Feldart.muss, regelsaetze = @Regelsatz(laenge = 2))
	private String abrechnungsgebiet;
	@Feld(value = "4124", feldart = Feldart.kann, regelsaetze = @Regelsatz(minLaenge = 5, maxLaenge = 60))
	private String sktZusatzangaben;
	@Feld(value = "4126", feldart = Feldart.kann, regelsaetze = @Regelsatz(maxLaenge = 60))
	private List<String> sktZusatzbemerkungen;
	@Feld(value = "4131", feldart = Feldart.kann, regelsaetze = @Regelsatz(maxLaenge = 2))
	private BesonderePersonengruppe besonderePersonengruppe;
	@Feld(value = "4132", feldart = Feldart.kann, regelsaetze = @Regelsatz(maxLaenge = 2))
	private DmpKennzeichnung dmpKennzeichnung;
	@Feld(value = "4202", feldart = Feldart.kann, regelsaetze = @Regelsatz(laenge = 1))
	private Boolean unfallfolgen;
	@Feld(value = "4204", feldart = Feldart.kann, regelsaetze = @Regelsatz(laenge = 1))
	private Boolean eingeschraenkterLeistungsanspruch;
	@Feld(value = "4221", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(laenge = 1))
	private Behandlungsanlass kurativPraeventivEss;
	@Feld(value = "4231", feldart = Feldart.kann, regelsaetze = @Regelsatz(laenge = 1))
	private String kontrolluntersuchungBekannterInfektion;
	@Feld(value = "4241", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(value = {F011.class, F022.class}, laenge = 9))
	private String lebenslangeArztnummer;
	@Feld(value = "4217", feldart = Feldart.bedingt_muss, regelsaetze = @Regelsatz(value = F010.class, laenge = 9))
	private String bsnrErstveranlasser;

}
