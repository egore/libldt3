#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::enums::LdtVersion::LdtVersion;
use crate::model::objekte::Organisation::Organisation;
use crate::model::regel::F007::F007;
use crate::model::regel::F012::F012;

/// Dieses Objekt enthält die Information zum sendenden Softwaresystem, welches
/// den LDT Datensatz erstellt hat.
pub struct SendendesSystem {
    #[feld(Value = "0001", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Value = libldt3.model.regel.F007.class, MaxLaenge = 12)]
    version: LdtVersion<'static>,
    #[feld(Value = "8315", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    empfaenger_id: String,
    #[feld(Value = "8316", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    sender_id: String,
    #[feld(Value = "0105", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Value = libldt3.model.regel.F012.class, Laenge = 16)]
    kvb_pruefnummer: String,
    #[feld(Value = "8212", Name = "Softwareverantwortlicher", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 24)]
    softwareverantwortlicher: Organisation,
    #[feld(Value = "0103", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(MaxLaenge = 60)]
    software_name: String,
    #[feld(Value = "0132", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(MaxLaenge = 60)]
    software_version: String
}

impl Kontext for SendendesSystem {
}
