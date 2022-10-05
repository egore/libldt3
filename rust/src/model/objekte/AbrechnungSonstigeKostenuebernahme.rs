#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::objekte::Rechnungsempfaenger::Rechnungsempfaenger;

/// Es werden hierüber auch
/// Leistungen abgerechnet, welche nicht durch medizinische Einsender abgefordert
/// werden oder die Materialien betreffen, die nicht humanen Ursprungs sind. Der
/// Rechnungsempfänger ist frei wählbar.
pub struct AbrechnungSonstigeKostenuebernahme {
    #[feld(Value = "7261", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    sonstige_versichertennummer: String,
    #[feld(Value = "7253", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 1)]
    kostenuebernahmeerklaerung_auftraggeber: Option<bool>,
    #[feld(Value = "8148", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 12)]
    rechnungsempfaenger: Rechnungsempfaenger
}

impl Kontext for AbrechnungSonstigeKostenuebernahme {
}
