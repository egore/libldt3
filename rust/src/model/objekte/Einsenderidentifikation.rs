#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::enums::Einsenderstatus::Einsenderstatus;
use crate::model::objekte::Arztidentifikation::Arztidentifikation;
use crate::model::objekte::Betriebsstaette::Betriebsstaette;
use crate::model::objekte::Organisation::Organisation;
use crate::model::objekte::Person::Person;

/// Hier werden alle notwendigen Informationen zum Einsender zusammengefasst.
pub struct Einsenderidentifikation {
    #[feld(Value = "7321", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 2)]
    status: Vec<Einsenderstatus<'static>>,
    #[feld(Value = "8312", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 20)]
    kunden_nummer: String,
    #[feld(Value = "7267", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    auftraggeber_id: String,
    #[feld(Value = "8114", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 18)]
    arztidentifikation: Arztidentifikation,
    #[feld(Value = "8240", Name = "Ueberweisung_von_anderen_Aerzten", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 32)]
    ueberweisung_von: Arztidentifikation,
    #[feld(Value = "8241", Name = "Ueberweisung_an", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 15)]
    ueberweisung_an: Arztidentifikation,
    #[feld(Value = "8147", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 6)]
    person: Person,
    #[feld(Value = "7268", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    fachrichtung: String,
    #[feld(Value = "8119", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 15)]
    permanent_establishment: Betriebsstaette,
    #[feld(Value = "8143", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 12)]
    organisation: Organisation
}

impl Kontext for Einsenderidentifikation {
}
