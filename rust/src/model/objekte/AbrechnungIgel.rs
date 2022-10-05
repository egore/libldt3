#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::enums::Gebuehrenordnung::Gebuehrenordnung;
use crate::model::objekte::Rechnungsempfaenger::Rechnungsempfaenger;

/// Mit diesem Objekt werden die Informationen für die Abrechnung von
/// Untersuchungsanforderungen zusammengefasst, welche als IGe-Leistungen
/// gegenüber gesetzlich versicherten Patienten erbracht werden können.
pub struct AbrechnungIgel {
    #[feld(Value = "4121", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 1)]
    gebuehrenordnung: Gebuehrenordnung<'static>,
    #[feld(Value = "7253", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 1)]
    kostenuebernahmeerklaerung_auftraggeber_liegt_vor: Option<bool>,
    #[feld(Value = "8148", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 12)]
    rechnungsempfaenger: Rechnungsempfaenger
}

impl Kontext for AbrechnungIgel {
}
