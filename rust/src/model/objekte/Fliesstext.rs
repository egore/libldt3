#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;

/// In diesem Objekt können semantisch zusammenhängende Texte oder Dateien
/// (Base64-kodiert) übertragen werden.
pub struct Fliesstext {
    #[feld(Value = "3564", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(MaxLaenge = 990)]
    text: Vec<String>,
    #[feld(Value = "6329", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(MaxLaenge = 60)]
    base64text: Vec<String>
}

impl Kontext for Fliesstext {
}
