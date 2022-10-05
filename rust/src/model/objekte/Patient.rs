#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::enums::GeschlechtNormalbereich::GeschlechtNormalbereich;
use crate::model::objekte::Person::Person;
use crate::model::regel::F002::F002;
use crate::model::regel::F013::F013;
use datetime::LocalDate;

/// In diesem Objekt werden die Informationen über einen Patienten aufgeführt.
pub struct Patient {
    #[feld(Value = "8147", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 6)]
    person: Person,
    #[feld(Value = "3119", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Value = libldt3.model.regel.F013.class, Laenge = 10)]
    versicherten_id: String,
    #[feld(Value = "3105", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(MinLaenge = 6, MaxLaenge = 12)]
    versichertennummer: String,
    #[feld(Value = "7329", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 1)]
    geschlecht: GeschlechtNormalbereich<'static>,
    #[feld(Value = "7922", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Value = libldt3.model.regel.F002.class, Laenge = 8)]
    sterbedatum: LocalDate,
    #[feld(Value = "3000", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    patient_number: String,
    #[feld(Value = "3620", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    profession: Vec<String>,
    #[feld(Value = "3621", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    current_profession: String
}

impl Kontext for Patient {
}
