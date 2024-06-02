/*
 * Copyright 2016-2024  Christoph Brill <opensource@christophbrill.de>
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
namespace libldt3
{
    namespace model
    {
        namespace enums
        {

            /// <summary>
            /// E053
            /// </summary>
            public enum Dokumententyp
            {
                /// Muster 6
                Muster6,
                /// Muster 10
                Muster10,
                /// Muster 10A
                Muster10A,
                /// Muster 39
                Muster39,
                /// Auftragsdokument PKV-FA
                AuftragsdokumentPKV_FA,
                /// Auftragsdokument PKV-LG
                AuftragsdokumentPKV_LG,
                /// Auftragsdokument IGeL
                AuftragsdokumentIGeL,
                /// Auftragsdokument Sonstige Kostenübernahme
                AuftragsdokumentSonstigeKostenuebernahme,
                /// Auftragsdokument Selektivvertrag
                AuftragsdokumentSelektivvertrag,
                Laborbefund,
                Mutterpass,
                Impfpass,
                Notfallausweis,
                Patientenbefund,
                Medikationsplan,
                Verlaufsbericht,
                Behandlungsbericht,
                /// GenDG (Gen-Diagnostik-Gesetz)
                Einverstaendniserklaerung_lt_GenDG,
                /// weitere laborspezifische Dokumente
                weitere_laborspezifischeDokumente,
                /// Allergie/RAST
                Allergie_RAST,
                Molekulardiagnostik,
                Endokrinologie,
                Virologie,
                Mikrobiologie,
                Funktionsdiagnostik,
                Infektionsserologie,
                Kinderwunsch,
                /// Meldung gemäß IfSG (Infektionsschutz-Gesetz)
                Meldung_gemaessIfSG,
                /// Meldung Krebsregister
                MeldungKrebsregister,
                Normbereichsgrafik,
                Rechnung,
                /// LDT-Datensatz
                LDT_Datensatz,
                /// Versorgung
                sonstige
            }

            public static class DokumententypExtensions
            {
                public static string GetCode(this Dokumententyp self)
                {
                    switch (self)
                    {
                        case Dokumententyp.Muster6: return "006";
                        case Dokumententyp.Muster10: return "010";
                        case Dokumententyp.Muster10A: return "10A";
                        case Dokumententyp.Muster39: return "039";
                        case Dokumententyp.AuftragsdokumentPKV_FA: return "090";
                        case Dokumententyp.AuftragsdokumentPKV_LG: return "091";
                        case Dokumententyp.AuftragsdokumentIGeL: return "092";
                        case Dokumententyp.AuftragsdokumentSonstigeKostenuebernahme: return "093";
                        case Dokumententyp.AuftragsdokumentSelektivvertrag: return "094";
                        case Dokumententyp.Laborbefund: return "100";
                        case Dokumententyp.Mutterpass: return "101";
                        case Dokumententyp.Impfpass: return "102";
                        case Dokumententyp.Notfallausweis: return "103";
                        case Dokumententyp.Patientenbefund: return "110";
                        case Dokumententyp.Medikationsplan: return "120";
                        case Dokumententyp.Verlaufsbericht: return "150";
                        case Dokumententyp.Behandlungsbericht: return "160";
                        case Dokumententyp.Einverstaendniserklaerung_lt_GenDG: return "200";
                        case Dokumententyp.weitere_laborspezifischeDokumente: return "250";
                        case Dokumententyp.Allergie_RAST: return "251";
                        case Dokumententyp.Molekulardiagnostik: return "252";
                        case Dokumententyp.Endokrinologie: return "253";
                        case Dokumententyp.Virologie: return "254";
                        case Dokumententyp.Mikrobiologie: return "255";
                        case Dokumententyp.Funktionsdiagnostik: return "256";
                        case Dokumententyp.Infektionsserologie: return "257";
                        case Dokumententyp.Kinderwunsch: return "258";
                        case Dokumententyp.Meldung_gemaessIfSG: return "300";
                        case Dokumententyp.MeldungKrebsregister: return "301";
                        case Dokumententyp.Normbereichsgrafik: return "400";
                        case Dokumententyp.Rechnung: return "500";
                        case Dokumententyp.LDT_Datensatz: return "900";
                        case Dokumententyp.sonstige: return "999";
                        default: throw new Exception();
                    }
                }
            }
        }
    }
}
