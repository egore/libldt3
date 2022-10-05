#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::enums::Geschlecht::Geschlecht;
use crate::model::enums::StatusPerson::StatusPerson;
use crate::model::objekte::Anschrift::Anschrift;
use crate::model::objekte::Kommunikationsdaten::Kommunikationsdaten;
use crate::model::regel::F003::F003;
use datetime::LocalDate;

/// Mit dem Objekt Person werden alle die natürlichen Personen dargestellt, deren
/// Daten für die Abwicklung, Abrechnung oder Dokumentation von Aufträgen und
/// Befun-den notwendig sind.
pub struct Person {
    #[feld(Value = "7420", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 2)]
    status: StatusPerson<'static>,
    #[feld(Value = "3100", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 20)]
    namenszusatz: String,
    #[feld(Value = "3120", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 20)]
    vorsatzwort: String,
    #[feld(Value = "3101", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(MaxLaenge = 45)]
    nachname: String,
    #[feld(Value = "3102", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(MaxLaenge = 45)]
    vorname: Vec<String>,
    #[feld(Value = "3103", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Value = libldt3.model.regel.F003.class, Laenge = 8)]
    geburtsdatum: LocalDate,
    #[feld(Value = "3104", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 20)]
    titel: String,
    #[feld(Value = "3110", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 1)]
    geschlecht: Geschlecht<'static>,
    #[feld(Value = "3628", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    muttersprache: String,
    #[feld(Value = "8990", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    namenskuerzel_namenszeichen: String,
    #[feld(Value = "8228", Name = "Wohnanschrift", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 13)]
    wohnanschrift: Anschrift,
    #[feld(Value = "8229", Name = "Anschrift_Arbeitsstelle", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 23)]
    anschrift_arbeitsstelle: Anschrift,
    #[feld(Value = "8230", Name = "Rechnungsanschrift", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 18)]
    rechnungsanschrift: Anschrift,
    #[feld(Value = "8232", Name = "Private_Kommunikationsdaten", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 27)]
    private_kommunikationsdaten: Kommunikationsdaten,
    #[feld(Value = "8233", Name = "Geschaeftliche_Kommunikationsdaten", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 34)]
    geschaeftliche_kommunikationsdaten: Kommunikationsdaten
}

impl Kontext for Person {
}
