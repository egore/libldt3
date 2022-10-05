#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::enums::EinschreibestatusSelektivvertraege::EinschreibestatusSelektivvertraege;
use crate::model::enums::Gebuehrenordnung::Gebuehrenordnung;
use crate::model::objekte::Rechnungsempfaenger::Rechnungsempfaenger;
use crate::model::regel::F002::F002;
use datetime::LocalDate;

/// Die Möglichkeit zum Abschluss von Selektivverträgen besteht im
/// Wesentlichen in der hausarztzentrierten Versorgung (§ 73 b SGB V), in der
/// besonderen ambulanten ärztlichen Versorgung (§ 73 c SGB V), bei
/// strukturierten Behandlungsprogrammen für chronische Erkrankungen
/// (Disease-Management-Programme) (§ 137 f SGB V) und in der Integrierten
/// Versorgung (§§ 140ff SGB V).
pub struct AbrechnungSelektivvertrag {
    #[feld(Value = "3130", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 1)]
    einschreibestatus_selektivvertraege: EinschreibestatusSelektivvertraege<'static>,
    #[feld(Value = "3134", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(MaxLaenge = 60)]
    bezeichnung_selektivvertrag: String,
    #[feld(Value = "4134", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(MaxLaenge = 28)]
    kostentraegername: String,
    #[feld(Value = "3131", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Value = libldt3.model.regel.F002.class, Laenge = 8)]
    teilnahme_von: LocalDate,
    #[feld(Value = "3132", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Value = libldt3.model.regel.F002.class, Laenge = 8)]
    teilnahme_bis: LocalDate,
    #[feld(Value = "3133", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(Value = libldt3.model.regel.F002.class, Laenge = 8)]
    datum_antragstellung: LocalDate,
    #[feld(Value = "7430", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(Laenge = 60)]
    patienten_id_selektivvertrag: String,
    #[feld(Value = "4121", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 1)]
    gebuehrenordnung: Gebuehrenordnung<'static>,
    #[feld(Value = "8148", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 12)]
    rechnungsempfaenger: Rechnungsempfaenger
}

impl Kontext for AbrechnungSelektivvertrag {
}
