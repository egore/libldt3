#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::enums::StatusPerson::StatusPerson;
use crate::model::objekte::Anhang::Anhang;

/// Das Objekt dient der Darstellung und elektronischen Übermittlung von
/// Namenskennzeichnungen.
pub struct Namenskennung {
    #[feld(Value = "7420", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 2)]
    status: StatusPerson<'static>,
    #[feld(Value = "7358", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(MaxLaenge = 60)]
    name: String,
    #[feld(Value = "8990", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(MaxLaenge = 60)]
    shorthand: String,
    #[feld(Value = "8110", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(Laenge = 6)]
    anhang: Anhang
}

impl Kontext for Namenskennung {
}
