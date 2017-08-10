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
import libldt3.model.enums.EinheitMesswert;
import libldt3.model.enums.Grenzwertindikator;
import libldt3.model.enums.GrenzwertindikatorErweitert;
import libldt3.model.enums.Normwertspezifikation;
import libldt3.model.regel.kontext.K002;
import libldt3.model.regel.kontext.K099;
import lombok.Getter;
import lombok.Setter;

/**
 * Mit diesem Objekt werden Norm- und Referenzbereiche strukturiert dargestellt.
 */
@Objekt(value = "0042", kontextregeln = K099.class)
public @Getter @Setter class Normalwert {

	@Objekt(kontextregeln = K002.class)
	public static @Getter @Setter class NormalwertGrenze {
		@SuppressWarnings("unused")
		private Float value;
		@Feld(value = "8419", feldart = Feldart.kann)
	@Regelsatz(laenge = 1)
		private EinheitMesswert einheitDesWertes;
		@Feld(value = "8421", feldart = Feldart.bedingt_muss)
	@Regelsatz(maxLaenge = 20)
		private String sizeUnit;
	}

	@Feld(value = "8424", feldart = Feldart.muss)
	@Regelsatz(laenge = 2)
	private Normwertspezifikation normwertspezifikation;
	@Feld(value = "8167", name = "Zusaetzliche_Informationen", feldart = Feldart.bedingt_kann)
	@Regelsatz(laenge = 26)
	private Fliesstext zusaetzlicheInformationen;
	@Feld(value = "8460", feldart = Feldart.bedingt_muss)
	@Regelsatz(maxLaenge = 60)
	private List<String> normalwertText;
	@Feld(value = "8461", feldart = Feldart.bedingt_muss)
	@Regelsatz(maxLaenge = 60)
	private NormalwertGrenze normalwertUntereGrenze;
	@Feld(value = "8462", feldart = Feldart.bedingt_muss)
	@Regelsatz(maxLaenge = 60)
	private NormalwertGrenze normalwertObereGrenze;
	@Feld(value = "7316", feldart = Feldart.bedingt_muss)
	@Regelsatz(maxLaenge = 60)
	private String normalwertListenbezeichnung;
	@Feld(value = "7317", feldart = Feldart.bedingt_muss)
	@Regelsatz(maxLaenge = 60)
	private List<String> normalwertListenzeile;
	@Feld(value = "7363", feldart = Feldart.kann)
	@Regelsatz(maxLaenge = 60)
	private NormalwertGrenze alarmwertUntereGrenze;
	@Feld(value = "7371", feldart = Feldart.kann)
	@Regelsatz(maxLaenge = 60)
	private NormalwertGrenze alarmwertObereGrenze;
	@Feld(value = "8422", feldart = Feldart.muss)
	@Regelsatz(maxLaenge = 2)
	private GrenzwertindikatorErweitert grenzwertindikator;

}
