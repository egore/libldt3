#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::enums::Zeiteinheit::Zeiteinheit;
use crate::model::objekte::Anhang::Anhang;
use crate::model::objekte::Anschrift::Anschrift;
use crate::model::objekte::Person::Person;
use crate::model::regel::F002::F002;
use datetime::LocalDate;

/// Enthält ein Auftrag Materialien die nicht Humanen Ursprungs sind, so werden
/// die entsprechenden Informationen zur Materialquelle in diesem Objekt
/// beschrieben.
pub struct Tier {
    #[feld(Value = "7319", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(MaxLaenge = 60)]
    identifikationsnummer_quelle: String,
    #[feld(Value = "7313", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(MaxLaenge = 60)]
    art_rasse_material: String,
    #[feld(Value = "7314", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(MaxLaenge = 60)]
    name_kennung: String,
    #[feld(Value = "7315", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 10)]
    alter: String,
    #[feld(Value = "7326", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 1)]
    alter_in: Zeiteinheit<'static>,
    #[feld(Value = "7351", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Value = libldt3.model.regel.F002.class, Laenge = 8)]
    geburtsdatum: LocalDate,
    #[feld(Value = "8107", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 9)]
    anschrift: Anschrift,
    #[feld(Value = "8147", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 6)]
    person: Person,
    #[feld(Value = "8110", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 6)]
    anhang: Vec<Anhang>
}

impl Kontext for Tier {
}
