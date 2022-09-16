using libldt3.attributes;
using libldt3.model.enums;
using libldt3.model.regel;
using NodaTime;

namespace libldt3
{
    namespace model
    {
        namespace objekte
        {

            /**
             * Mit diesem Objekt werden die Informationen für die Abrechnung von
             * Untersuchungsanforderungen zusammengefasst, welche im Rahmen von
             * Selektivverträgen und damit außerhalb der budgetären Leistungen erbracht
             * werden. Die Möglichkeit zum Abschluss von Selektivverträgen besteht im
             * Wesentlichen in der hausarztzentrierten Versorgung (§ 73 b SGB V), in der
             * besonderen ambulanten ärztlichen Versorgung (§ 73 c SGB V), bei
             * strukturierten Behandlungsprogrammen für chronische Erkrankungen
             * (Disease-Management-Programme) (§ 137 f SGB V) und in der Integrierten
             * Versorgung (§§ 140ff SGB V).
             */
            [Objekt(Value = "0006")]
            public class AbrechnungSelektivvertrag
            {
                [Feld(Value = "3130", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 1)]
                public EinschreibestatusSelektivvertraege? einschreibestatusSelektivvertraege;
                [Feld(Value = "3134", Feldart = Feldart.muss)]
                [Regelsatz(MaxLaenge = 60)]
                public string bezeichnungSelektivvertrag;
                [Feld(Value = "4134", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(MaxLaenge = 28)]
                public string kostentraegername;
                [Feld(Value = "3131", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Value = new[] { typeof(F002) }, Laenge = 8)]
                public LocalDate? teilnahmeVon;
                [Feld(Value = "3132", Feldart = Feldart.bedingt_muss)]
                [Regelsatz(Value = new[] { typeof(F002) }, Laenge = 8)]
                public LocalDate? teilnahmeBis;
                [Feld(Value = "3133", Feldart = Feldart.bedingt_kann)]
                [Regelsatz(Value = new[] { typeof(F002) }, Laenge = 8)]
                public LocalDate? datumAntragstellung;
                [Feld(Value = "4121", Feldart = Feldart.kann)]
                [Regelsatz(Laenge = 1)]
                public Gebuehrenordnung? gebuehrenordnung;
                [Feld(Value = "8148", Feldart = Feldart.muss)]
                [Regelsatz(Laenge = 12)]
                public Rechnungsempfaenger rechnungsempfaenger;
            }
        }
    }
}
