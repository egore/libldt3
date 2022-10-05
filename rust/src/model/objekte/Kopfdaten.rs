#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::objekte::Organisation::Organisation;
use crate::model::objekte::SendendesSystem::SendendesSystem;
use crate::model::objekte::Timestamp::Timestamp;

/// Hier werden alle Informationen zusammengefasst, die im Kontext mit der
/// Erstellung des Datensatzes stehen.
pub struct Kopfdaten {
    #[feld(Value = "8151", Name = "Sendendes_System", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 16)]
    sendendes_system: SendendesSystem,
    #[feld(Value = "8218", Name = "Timestamp_Erstellung_Datensatz", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 30)]
    timestamp_erstellung_datensatz: Timestamp,
    #[feld(Value = "8212", Name = "Softwareverantwortlicher", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 24)]
    softwareverantwortlicher: Organisation
}

impl Kontext for Kopfdaten {
}
