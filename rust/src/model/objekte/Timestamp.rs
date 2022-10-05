#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::objekte::Person::Person;
use crate::model::regel::F002::F002;
use crate::model::regel::F016::F016;
use datetime::LocalDate;
use datetime::LocalTime;

/// Ein Zeitstempel
pub struct Timestamp {
    #[feld(Value = "7278", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Value = libldt3.model.regel.F002.class, Laenge = 8)]
    datum: LocalDate,
    #[feld(Value = "7279", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Value = libldt3.model.regel.F016.class, MinLaenge = 6, MaxLaenge = 9)]
    uhrzeit: LocalTime,
    #[feld(Value = "7272", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 990)]
    freitext: String,
    #[feld(Value = "8235", Name = "Person_zum_Timestamp", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 20)]
    person: Person
}

impl Kontext for Timestamp {
}
