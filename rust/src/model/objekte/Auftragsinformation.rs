#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::enums::Dringlichkeit::Dringlichkeit;
use crate::model::enums::StatusDringlichkeit::StatusDringlichkeit;
use crate::model::enums::ZusaetzlicherBefundweg::ZusaetzlicherBefundweg;
use crate::model::objekte::Fliesstext::Fliesstext;
use crate::model::objekte::Kommunikationsdaten::Kommunikationsdaten;
use crate::model::objekte::Namenskennung::Namenskennung;
use crate::model::objekte::Person::Person;
use crate::model::objekte::Timestamp::Timestamp;

pub struct Auftragsinformation_Befundweg {
    value: ZusaetzlicherBefundweg<'static>,
    person: Person
}

/// In diesem Objekt werden die Informationen zur Zuordnung im sendenden System
/// zum Auftrag zusammengefasst sowie zusätzliche Befundwege und die
/// Dringlichkeit des Auftrags definiert.
pub struct Auftragsinformation {
    #[feld(Value = "8310", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(MaxLaenge = 60)]
    auftragsnummer_einsender: String,
    #[feld(Value = "8313", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(MaxLaenge = 60)]
    nachforderung_id: Vec<String>,
    #[feld(Value = "8311", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    auftragsnummer_labor: String,
    #[feld(Value = "7268", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    fachrichtung_stationskennung: String,
    #[feld(Value = "0080", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    fallakte_id: String,
    #[feld(Value = "0081", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(MaxLaenge = 60)]
    fallakte_bezeichnung: Vec<String>,
    #[feld(Value = "8501", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(Laenge = 1)]
    dringlichkeit: Dringlichkeit<'static>,
    #[feld(Value = "7262", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(Laenge = 1)]
    status_dringlichkeit: StatusDringlichkeit<'static>,
    #[feld(Value = "8118", Name = "Abweichender_Befundweg", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 22)]
    abweichender_befundweg: Kommunikationsdaten,
    #[feld(Value = "8611", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(Laenge = 1)]
    zusaetzlicher_befundweg: Vec<Auftragsinformation_Befundweg>,
    #[feld(Value = "8213", Name = "Timestamp_Erstellung_Untersuchungsanforderung", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 45)]
    timestamp_erstellung_untersuchungsanforderung: Timestamp,
    #[feld(Value = "8238", Name = "Auftragsbezogene_Hinweise", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 25)]
    auftragsbezogene_hinweise: Fliesstext,
    #[feld(Value = "8141", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 13)]
    namenskennung: Namenskennung
}

impl Kontext for Auftragsinformation {
}
