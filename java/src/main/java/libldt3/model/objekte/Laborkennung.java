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
import libldt3.model.enums.Laborart;
import libldt3.model.regel.kontext.K083;
import libldt3.model.regel.kontext.K084;

/**
 * Das Objekt enthält die Angaben zu dem Labor, welches den Auftrag ausgeführt hat.
 */
@Objekt(value = "0036", kontextregeln = {K083.class, K084.class})
public class Laborkennung implements Kontext {

    @Feld(value = "8239", feldart = Feldart.bedingt_muss)
    @Regelsatz(laenge = 16)
    public Organisation laborbezeichnung;
    @Feld(value = "7352", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 60)
    public List<String> urlKataloge;
    @Feld(value = "8324", feldart = Feldart.kann)
    @Regelsatz(maxLaenge = 60)
    public String einesLaborstandortesId;
    @Feld(value = "7266", feldart = Feldart.muss)
    @Regelsatz(laenge = 1)
    public Laborart laborart;

}
