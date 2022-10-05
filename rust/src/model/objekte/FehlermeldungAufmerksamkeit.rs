#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::enums::Benachrichtigungsgrund::Benachrichtigungsgrund;
use crate::model::objekte::Anhang::Anhang;
use crate::model::objekte::Fliesstext::Fliesstext;
use crate::model::objekte::Person::Person;
use crate::model::objekte::Timestamp::Timestamp;

/// Dieses Objekt soll genutzt werden, wenn es aus Sicht des Auftragsnehmers
/// Vorkommnisse im Prozess gegeben hat, die eine zusätzliche Benachrichtigung
/// des Einsenders erfordern.
pub struct FehlermeldungAufmerksamkeit {
    #[feld(Value = "7280", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 1)]
    benachrichtigungsgrund: Benachrichtigungsgrund<'static>,
    #[feld(Value = "7320", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(Laenge = 1)]
    recall_empfohlen: Option<bool>,
    #[feld(Value = "8154", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(Laenge = 9)]
    timestamp: Timestamp,
    #[feld(Value = "8147", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 6)]
    person: Person,
    #[feld(Value = "8167", Name = "Zusaetzliche_Informationen", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 26)]
    text: Vec<Fliesstext>,
    #[feld(Value = "8110", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 6)]
    anhang: Vec<Anhang>
}

impl Kontext for FehlermeldungAufmerksamkeit {
}
