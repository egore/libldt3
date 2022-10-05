#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::enums::Adresstyp::Adresstyp;
use crate::model::regel::kontext::K017::K017;

/// Dabei kann es sich entweder um
/// ein Postfach oder um eine physische Adresse handeln.
pub struct Anschrift {
    #[feld(Value = "3112", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(MaxLaenge = 10)]
    plz: String,
    #[feld(Value = "3113", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(MaxLaenge = 40)]
    ort: String,
    #[feld(Value = "3107", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(MaxLaenge = 46)]
    strasse: String,
    #[feld(Value = "3109", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(MaxLaenge = 9)]
    hausnummer: String,
    #[feld(Value = "3115", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(MaxLaenge = 40)]
    anschriftenzusatz: String,
    #[feld(Value = "3114", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(MaxLaenge = 3)]
    wohnsitzlaendercode: String,
    #[feld(Value = "3121", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(MaxLaenge = 10)]
    postfach_plz: String,
    #[feld(Value = "3122", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(MaxLaenge = 40)]
    postfach_ort: String,
    #[feld(Value = "3123", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(MaxLaenge = 8)]
    postfach: String,
    #[feld(Value = "3124", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(MaxLaenge = 3)]
    postfach_wohnsitzlaendercode: String,
    #[feld(Value = "1202", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 1)]
    adresstyp: Vec<Adresstyp<'static>>
}

impl Kontext for Anschrift {
}
