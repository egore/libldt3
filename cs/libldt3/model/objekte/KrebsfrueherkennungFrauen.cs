using NodaTime;
using System.Collections.Generic;

using libldt3.attributes;
using libldt3.model.regel;

namespace libldt3
{
    namespace model
    {
        namespace objekte
        {

            /**
             * In diesem Objekt wird das Muster 39, Grundlage für die
             * Krebsfrüherkennungsuntersuchung für Frauen, abgebildet.
             */
            [Objekt(Value = "0034")]
            public class KrebsfrueherkennungFrauen
            {

                [Feld(Value = "7295", Feldart = Feldart.muss)]
                [Regelsatz(Value = new[] { typeof(F002) }, Laenge = 8)]
                public LocalDate? untersuchungsTag;
                [Feld(Value = "7296", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? wiederholungsuntersuchung;
                [Feld(Value = "7297", Feldart = Feldart.kann)]
                [Regelsatz(Value = new[] { typeof(F018) }, Laenge = 8)]
                public LocalDate? letzteUntersuchung;
                [Feld(Value = "7298", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 60)]
                public string nrLetzterZytologischerBefund;
                [Feld(Value = "7299", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 6)]
                public string gruppeLetzterBefund;
                [Feld(Value = "7336", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public string gynaekologischeOpStrahlenOderChemotherapie;
                [Feld(Value = "7337", Feldart = Feldart.bedingt_kann)]
                [Regelsatz(MaxLaenge = 60)]
                public IList<string> artGynaekologischeOpStrahlenOderChemotherapie;
                [Feld(Value = "7338", Feldart = Feldart.kann)]
                [Regelsatz(Value = new[] { typeof(F018) }, Laenge = 8)]
                public LocalDate? datumGynaekologischeOp;
                [Feld(Value = "3668", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 2)]
                public int? anzahlSchwangerschaften;
                [Feld(Value = "8512", Feldart = Feldart.kann)]
                [Regelsatz(Value = new[] { typeof(F018) }, Laenge = 8)]
                public LocalDate? ersterTagLetzterZyklus;
                [Feld(Value = "7339", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? graviditaet;
                [Feld(Value = "7380", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? pathologischeGynaekologischeBlutungen;
                [Feld(Value = "7381", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? sonstigerAusfluss;
                [Feld(Value = "7382", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? iup;
                [Feld(Value = "7383", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? ovulationshemmer;
                [Feld(Value = "7384", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? sonstigeHormonanwendung;
                [Feld(Value = "7385", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? vulvaInspektionAuffaellig;
                [Feld(Value = "7386", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? portioUndVaginaAuffaellig;
                [Feld(Value = "7387", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? innereGenitaleAuffaellig;
                [Feld(Value = "7388", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? inguinaleLymphknotenAuffaellig;
                [Feld(Value = "7389", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? behandlungsbeduerftigeNebenbefunde;
                [Feld(Value = "7390", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? haut;
                [Feld(Value = "7391", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? mammaAuffaellig;
                [Feld(Value = "7392", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? axillaereLymphknotenAuffaellig;
                [Feld(Value = "7393", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? rektumKolonBlutOderSchleim;
                [Feld(Value = "7394", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? neuAufgetreteneUnregelmaessigkeiten;
                [Feld(Value = "7395", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? rektumKolonTastbefundAuffaellig;
                [Feld(Value = "7396", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? stuhltestZurueckgegeben;
                [Feld(Value = "7397", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public bool? stuhltestPositiv;
                [Feld(Value = "7423", Feldart = Feldart.kann)]
                [Regelsatz(MaxLaenge = 60)]
                public bool? gynaekologischeDiagnose;
                [Feld(Value = "7398", Feldart = Feldart.kann)]
                [Regelsatz(Value = new[] { typeof(F015) }, Laenge = 7)]
                public string rrBlutdruck;
                [Feld(Value = "7399", Feldart = Feldart.kann)]
                [Regelsatz(Value = new[] { typeof(F015) }, Laenge = 7)]
                public string rrZweiteMessung;
                [Feld(Value = "8167", Name = "Zusaetzliche_Informationen", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 26)]
                public IList<Fliesstext> zusaetzlicheInformationen;

            }
        }
    }
}