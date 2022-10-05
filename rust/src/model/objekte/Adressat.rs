#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::objekte::Organisation::Organisation;
use crate::model::objekte::Person::Person;

pub struct Adressat {
    #[feld(Value = "8147", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 6)]
    person: Person,
    #[feld(Value = "8143", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 12)]
    organisation: Organisation
}

impl Kontext for Adressat {
}
