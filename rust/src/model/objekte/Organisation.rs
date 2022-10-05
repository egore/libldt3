#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::objekte::Anschrift::Anschrift;
use crate::model::objekte::Kommunikationsdaten::Kommunikationsdaten;
use crate::model::objekte::Person::Person;

pub struct Organisation_Funktionsbezeichnung {
    value: String,
    person: Vec<Person>
}

/// Mit diesem Objekt werden Organisationsstrukturen abgebildet.
pub struct Organisation {
    #[feld(Value = "1250", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(MaxLaenge = 60)]
    organisation_firma: String,
    #[feld(Value = "1251", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    rechtsform_organisation: String,
    #[feld(Value = "1252", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    funktionsbezeichnung: Vec<Organisation_Funktionsbezeichnung>,
    #[feld(Value = "8229", Name = "Anschrift_Arbeitsstelle", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 23)]
    anschrift_arbeitsstelle: Vec<Anschrift>,
    #[feld(Value = "8230", Name = "Rechnungsanschrift", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 18)]
    rechnungsanschrift: Anschrift,
    #[feld(Value = "8131", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 19)]
    kommunikationsdaten: Kommunikationsdaten
}

impl Kontext for Organisation {
}
