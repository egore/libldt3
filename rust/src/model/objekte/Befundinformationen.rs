#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::enums::Auftragsstatus::Auftragsstatus;
use crate::model::enums::ZusaetzlicherBefundweg::ZusaetzlicherBefundweg;
use crate::model::objekte::Anhang::Anhang;
use crate::model::objekte::FehlermeldungAufmerksamkeit::FehlermeldungAufmerksamkeit;
use crate::model::objekte::Fliesstext::Fliesstext;
use crate::model::objekte::Kommunikationsdaten::Kommunikationsdaten;
use crate::model::objekte::Namenskennung::Namenskennung;
use crate::model::objekte::Person::Person;
use crate::model::objekte::Timestamp::Timestamp;
use crate::model::regel::kontext::K005::K005;
use crate::model::regel::kontext::Kontextregel::Kontextregel;

pub struct Befundinformationen_OrderNumber {
    value: String,
    nachforderung_id: Vec<String>,
    order_request_timestamp: Timestamp,
    timestamp_auftragseingang: Timestamp
}

pub struct Befundinformationen_Befundweg {
    value: ZusaetzlicherBefundweg<'static>,
    person: Person
}

/// Dieses Objekt bündelt alle Daten zum Befund inklusive aller Kennungen, welche
/// eine eineindeutige Zuordnung von Auftrag und Befund sicherstellen.
pub struct Befundinformationen {
    #[feld(Value = "8310", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(MaxLaenge = 60)]
    order_number: Befundinformationen_OrderNumber,
    #[feld(Value = "8311", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(MaxLaenge = 60)]
    order_id: String,
    #[feld(Value = "7305", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(MaxLaenge = 60)]
    finding_id: String,
    #[feld(Value = "8401", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 1)]
    status: Auftragsstatus<'static>,
    #[feld(Value = "0080", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    fallakte_id: String,
    #[feld(Value = "0081", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(MaxLaenge = 60)]
    fallakte_bezeichnung: Vec<String>,
    #[feld(Value = "7258", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    katalog_id: String,
    #[feld(Value = "7251", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(MaxLaenge = 60)]
    katalog_bezeichnung: String,
    #[feld(Value = "4229", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 5)]
    ausnahmeindikation: Vec<String>,
    #[feld(Value = "8118", Name = "Abweichender_Befundweg", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 22)]
    abweichender_befundweg: Kommunikationsdaten,
    #[feld(Value = "8611", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(Laenge = 1)]
    zusaetzlicher_befundweg: Vec<Befundinformationen_Befundweg>,
    #[feld(Value = "7320", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 1)]
    recall_empfohlen: Option<bool>,
    #[feld(Value = "8154", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(Laenge = 9)]
    recall_empfohlen_timestamp: Timestamp,
    #[feld(Value = "8216", Name = "Timestamp_Befunderstellung", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 26)]
    timestamp_befunderstellung: Timestamp,
    #[feld(Value = "8167", Name = "Zusaetzliche_Informationen", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 26)]
    zusaetzliche_informationen: Vec<Fliesstext>,
    #[feld(Value = "8110", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 6)]
    anhang: Vec<Anhang>,
    #[feld(Value = "8126", Name = "Fehlermeldung_Aufmerksamkeit", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 28)]
    fehlermeldung_aufmerksamkeit: Vec<FehlermeldungAufmerksamkeit>,
    #[feld(Value = "8141", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 13)]
    namenskennung: Namenskennung
}

impl Kontext for Befundinformationen {
}
