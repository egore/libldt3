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
import libldt3.model.regel.erlaubt.E010;
import libldt3.model.regel.kontext.K041;

/**
 * Hier werden alle Angaben für die Abrechnung von Untersuchungsanforderungen in
 * der GKV gegenüber der KV hinterlegt. Der Patient ist in der gesetzlichen
 * Krankenversicherung pflichtversichert oder freiwillig versichert. Der Auftrag
 * für die geplanten Untersuchungen erfolgt über Muster 10/Muster 10A/Muster 39.
 * Mit diesem Objekt werden die Informationen für die Abrechnung von
 * Untersuchungsanforderungen zusammengefasst, die im Regelleistungskatalog der
 * Krankenkassen vorhanden sind oder anderweitig z.B. über eDMP dem Patienten
 * zugeordnet werden können.
 */
@Objekt(value = "0002", kontextregeln = { K041.class })
public class AbrechnungGKV implements Kontext {

    @Feld(value = "4239", feldart = Feldart.muss)
    @Regelsatz(laenge = 2)
    public Scheinuntergruppe scheinuntergruppe;
    @Feld(value = "4134", feldart = Feldart.muss)
    @Regelsatz(maxLaenge = 45)
    public String kostentraegername;
    @Feld(value = "4104", feldart = Feldart.muss)
    @Regelsatz(value = F001.class, laenge = 5)
    public String abrechnungsVknr;
    @Feld(value = "4106", feldart = Feldart.muss)
    @Regelsatz(laenge = 2)
    public KostentraegerAbrechnungsbereich kostentraegerAbrechnungsbereich;
    @Feld(value = "4108", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 60)
    public String zulassungsnummer;
    @Feld(value = "3116", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 2)
    public WOP wop;
    @Feld(value = "3108", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 1)
    public Versichertenart versichertenart;
    @Feld(value = "4109", feldart = Feldart.bedingt_muss)
    @Regelsatz(value = F002.class, laenge = 8)
    public LocalDate letzterEinlesetagVersichertenkarteImQuartal;
    @Feld(value = "4133", feldart = Feldart.bedingt_muss)
    @Regelsatz(value = F002.class, laenge = 8)
    public LocalDate versicherungsschutzBeginn;
    @Feld(value = "4110", feldart = Feldart.bedingt_muss)
    @Regelsatz(value = F002.class, laenge = 8)
    public LocalDate versicherungsschutzEnde;
    @Feld(value = "4111", feldart = Feldart.muss)
    @Regelsatz(laenge = 9)
    public String kostentraegerkennung;
    @Feld(value = "4229", feldart = Feldart.bedingt_kann)
    @Regelsatz(laenge = 5)
    public List<String> ausnahmeindikation;
    @Feld(value = "4122", feldart = Feldart.muss)
    @Regelsatz(value = E010.class, laenge = 2)
    public String abrechnungsgebiet;
    @Feld(value = "4124", feldart = Feldart.kann)
    @Regelsatz(minLaenge = 5, maxLaenge = 60)
    public String sktZusatzangaben;
    @Feld(value = "4126", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 60)
    public List<String> sktZusatzbemerkungen;
    @Feld(value = "4131", feldart = Feldart.kann)
    @Regelsatz(laenge = 2)
    public BesonderePersonengruppe besonderePersonengruppe;
    @Feld(value = "4132", feldart = Feldart.kann)
    @Regelsatz(laenge = 2)
    public DmpKennzeichnung dmpKennzeichnung;
    @Feld(value = "4202", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Boolean unfallfolgen;
    @Feld(value = "4204", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Boolean eingeschraenkterLeistungsanspruch;
    @Feld(value = "4221", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 1)
    public Behandlungsanlass kurativPraeventivEss;
    @Feld(value = "4231", feldart = Feldart.kann)
    @Regelsatz(laenge = 1)
    public Boolean kontrolluntersuchungBekannterInfektion;
    @Feld(value = "4241", feldart = Feldart.bedingt_muss)
    @Regelsatz(value = { F011.class, F022.class }, laenge = 9)
    public String lebenslangeArztnummer;
    @Feld(value = "4248", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 9)
    public String pseudoLanr;
    @Feld(value = "4217", feldart = Feldart.bedingt_muss)
    @Regelsatz(value = F010.class, laenge = 9)
    public String bsnrErstveranlasser;
    @Feld(value = "4225", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 9)
    public String asvTeamnummer;

}
