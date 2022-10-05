#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;

pub struct Kommunikationsdaten_ElektronischePostadresse {
    value: String,
    spezifizierung: String
}

/// mit einer Einrichtung, Firma, Arzt, einem Patienten ermöglichen.
pub struct Kommunikationsdaten {
    #[feld(Value = "7330", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(MaxLaenge = 60)]
    phone: Vec<String>,
    #[feld(Value = "7331", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(MaxLaenge = 60)]
    mobile: Vec<String>,
    #[feld(Value = "7332", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(MaxLaenge = 60)]
    elektronische_postadresse: Vec<Kommunikationsdaten_ElektronischePostadresse>,
    #[feld(Value = "7333", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(MaxLaenge = 60)]
    fax: Vec<String>,
    #[feld(Value = "7335", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(MaxLaenge = 60)]
    email: Vec<String>,
    #[feld(Value = "7334", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(MaxLaenge = 60)]
    website: Vec<String>
}

impl Kontext for Kommunikationsdaten {
}
