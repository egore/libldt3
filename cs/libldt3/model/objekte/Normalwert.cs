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
using System.Collections.Generic;
using libldt3.attributes;
using libldt3.model.enums;
using libldt3.model.regel.kontext;

namespace libldt3
{
    namespace model
    {
        namespace objekte
        {
            /**
             * Mit diesem Objekt werden Norm- und Referenzbereiche strukturiert dargestellt.
             */
            [Objekt(Value = "0042", Kontextregeln = new[] { typeof(K099) })]
            public class Normalwert
            {
                [Objekt(Kontextregeln = new[] { typeof(K002) })]
                public class NormalwertGrenze
                {
                    public float? Value;
                    [Feld(Value = "8419", Feldart = Feldart.kann)]
                    [Regelsatz(Laenge = 1)]
                    public EinheitMesswert? einheitDesWertes;
                    [Feld(Value = "8421", Feldart = Feldart.bedingt_muss)]
                    [Regelsatz(MaxLaenge = 20)]
                    public string sizeUnit;
                }

                [Feld(Value = "8424", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 2)]
                public Normwertspezifikation? normwertspezifikation;
                [Feld(Value = "8167", Name = "Zusaetzliche_Informationen", Feldart = Feldart.bedingt_kann)]
                [Regelsatz(Laenge = 26)]
                public Fliesstext zusaetzlicheInformationen;
                [Feld(Value = "8460", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(MaxLaenge = 60)]
                public IList<string> normalwertText;
                [Feld(Value = "8461", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(MaxLaenge = 60)]
                public NormalwertGrenze normalwertUntereGrenze;
                [Feld(Value = "8462", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(MaxLaenge = 60)]
                public NormalwertGrenze normalwertObereGrenze;
                [Feld(Value = "7316", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(MaxLaenge = 60)]
                public string normalwertListenbezeichnung;
                [Feld(Value = "7317", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(MaxLaenge = 60)]
                public IList<string> normalwertListenzeile;
                [Feld(Value = "7363", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 60)]
                public NormalwertGrenze alarmwertUntereGrenze;
                [Feld(Value = "7371", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 60)]
                public NormalwertGrenze alarmwertObereGrenze;
                [Feld(Value = "8422", Feldart = Feldart.muss)]
                [Regelsatz(MaxLaenge = 2)]
                public GrenzwertindikatorErweitert grenzwertindikator;
            }
        }
    }
}
