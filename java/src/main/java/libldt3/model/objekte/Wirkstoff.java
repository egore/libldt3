/*
 * Copyright 2016-2023  Christoph Brill &lt;opensource@christophbrill.de&gt;
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

import libldt3.annotations.Feld;
import libldt3.annotations.Feldart;
import libldt3.annotations.Objekt;
import libldt3.annotations.Regelsatz;
import libldt3.model.Kontext;

/**
 * Hier werden Informationen zu Wirkstoffen zusammengefasst.
 */
@Objekt("0071")
public class Wirkstoff implements Kontext {

    @Objekt
    public static class ArzneimittelwirkstoffWirkstoffWirkstoffbezeichnung implements Kontext {
        @SuppressWarnings("unused")
        public String value;
        @Feld(value = "6224", feldart = Feldart.bedingt_kann)
        @Regelsatz(maxLaenge = 60)
        public WirkstoffCode wirkstoffCode;
        @Feld(value = "8523", feldart = Feldart.bedingt_kann)
        @Regelsatz(maxLaenge = 60)
        public WirkstoffmengeMengeBezugsmengeWirkstaerke wirkstoffmengeMengeBezugsmengeWirkstaerke;
    }

    @Objekt
    public static class WirkstoffCode implements Kontext {
        @SuppressWarnings("unused")
        public String value;
        @Feld(value = "6214", feldart = Feldart.bedingt_muss)
        @Regelsatz(maxLaenge = 60)
        public String wirkstoffKlassifikation;
    }

    @Objekt
    public static class WirkstoffmengeMengeBezugsmengeWirkstaerke implements Kontext {
        @SuppressWarnings("unused")
        public float value;
        @Feld(value = "8421", feldart = Feldart.bedingt_muss)
        @Regelsatz(maxLaenge = 60)
        public String masseinheitMesswerteWertes;
    }

    @Feld(value = "6212", feldart = Feldart.muss)
    @Regelsatz(maxLaenge = 60)
    public ArzneimittelwirkstoffWirkstoffWirkstoffbezeichnung arzneimittelwirkstoffWirkstoffWirkstoffbezeichnung;

}
