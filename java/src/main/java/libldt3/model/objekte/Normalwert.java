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
import libldt3.model.enums.EinheitMesswert;
import libldt3.model.enums.Grenzwertindikator;
import libldt3.model.enums.Normwertspezifikation;
import libldt3.model.regel.kontext.K002;
import libldt3.model.regel.kontext.K054;
import libldt3.model.regel.kontext.K055;
import libldt3.model.regel.kontext.K099;

/**
 * Mit diesem Objekt werden Norm- und Referenzbereiche strukturiert dargestellt.
 */
@Objekt(value = "0042", kontextregeln = {K054.class, K055.class})
public class Normalwert implements Kontext {

    @Objekt
    public static class Normalwertspezifikation implements Kontext {
        @SuppressWarnings("unused")
        public Normwertspezifikation value;
        @Feld(value = "8167", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 26)
        public Fliesstext zusaetzlicheInformationen;
    }

    @Objekt(kontextregeln = K002.class)
    public static class NormalwertUntereGrenze implements Kontext {
        @SuppressWarnings("unused")
        public float value;
        @Feld(value = "8419", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 1)
        public EinheitensystemMesswerteWertes einheitensystemMesswerteWertes;
    }

    @Objekt(kontextregeln = K002.class)
    public static class EinheitensystemMesswerteWertes implements Kontext {
        @SuppressWarnings("unused")
        public EinheitMesswert value;
        @Feld(value = "8421", feldart = Feldart.bedingt_muss)
        @Regelsatz(maxLaenge = 60)
        public String masseinheitMesswerteWertes;
    }

    @Objekt(kontextregeln = K002.class)
    public static class NormalwertObereGrenze implements Kontext {
        @SuppressWarnings("unused")
        public float value;
        @Feld(value = "8419", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 1)
        public EinheitMesswert einheitensystemMesswerteWertes;
    }

    @Objekt
    public static class NormalwertListenbezeichnung implements Kontext {
        @SuppressWarnings("unused")
        public String value;
        @Feld(value = "7317", feldart = Feldart.bedingt_muss)
        @Regelsatz(maxLaenge = 60)
        public List<String> normalwertListenzeile;
    }

    @Objekt(kontextregeln = K002.class)
    public static class AlarmwertUntereGrenze implements Kontext {
        @SuppressWarnings("unused")
        public float value;
        @Feld(value = "8419", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 1)
        public EinheitMesswert einheitensystemMesswerteWertes;
    }

    @Objekt(kontextregeln = K002.class)
    public static class AlarmwertObereGrenze implements Kontext {
        @SuppressWarnings("unused")
        public float value;
        @Feld(value = "8419", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 1)
        public EinheitMesswert einheitensystemMesswerteWertes;
    }

    @Objekt(kontextregeln = K099.class)
    public static class GrenzwertindikatorLaborwerte implements Kontext {
        @SuppressWarnings("unused")
        public Grenzwertindikator value;
        @Feld(value = "8126", feldart = Feldart.bedingt_muss)
        @Regelsatz(laenge = 28)
        public FehlermeldungAufmerksamkeit fehlermeldungAufmerksamkeit;
    }

    @Feld(value = "8424", feldart = Feldart.muss)
    @Regelsatz(laenge = 2)
    public Normalwertspezifikation normalwertspezifikation;
    @Feld(value = "8460", feldart = Feldart.bedingt_muss)
    @Regelsatz(maxLaenge = 990)
    public List<String> normalwertText;
    @Feld(value = "8461", feldart = Feldart.bedingt_muss)
    @Regelsatz(maxLaenge = 60)
    public NormalwertUntereGrenze normalwertUntereGrenze;
    @Feld(value = "8462", feldart = Feldart.bedingt_muss)
    @Regelsatz(maxLaenge = 60)
    public NormalwertObereGrenze normalwertObereGrenze;
    @Feld(value = "7316", feldart = Feldart.bedingt_muss)
    @Regelsatz(maxLaenge = 60)
    public NormalwertListenbezeichnung normalwertListenbezeichnung;
    @Feld(value = "7363", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 60)
    public AlarmwertUntereGrenze alarmwertUntereGrenze;
    @Feld(value = "7371", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 60)
    public AlarmwertObereGrenze alarmwertObereGrenze;
    @Feld(value = "8422", feldart = Feldart.muss)
    @Regelsatz(maxLaenge = 2)
    public GrenzwertindikatorLaborwerte grenzwertindikatorLaborwerte;

}
