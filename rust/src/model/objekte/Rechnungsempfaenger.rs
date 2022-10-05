#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::enums::Privattarif::Privattarif;
use crate::model::enums::StatusRechnungsempfaenger::StatusRechnungsempfaenger;
use crate::model::objekte::Adressat::Adressat;

/// Hier sind alle Angaben zum Rechnungsempfänger enthalten.
pub struct Rechnungsempfaenger {
    #[feld(Value = "8310", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(MaxLaenge = 60)]
    auftragsnummer_einsender: String,
    #[feld(Value = "7421", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 2)]
    status_rechnungsempfaenger: StatusRechnungsempfaenger<'static>,
    #[feld(Value = "0600", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(MaxLaenge = 60)]
    name_einrichtung_auftraggeber: String,
    #[feld(Value = "7328", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(MaxLaenge = 10)]
    zusaetzliche_namenszeile: String,
    #[feld(Value = "8108", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 8)]
    adressat: Adressat,
    #[feld(Value = "8610", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 1)]
    privattarif: Privattarif<'static>,
    #[feld(Value = "8608", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    kommentar_aktenzeichen: String
}

impl Kontext for Rechnungsempfaenger {
}
