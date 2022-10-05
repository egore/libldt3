#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::enums::MedikationsStatus::MedikationsStatus;
use crate::model::objekte::Fliesstext::Fliesstext;
use crate::model::objekte::Timestamp::Timestamp;
use crate::model::objekte::Wirkstoff::Wirkstoff;

/// Hier werden Informationen zu Medikamenten zusammengefasst.
pub struct Medikament {
    #[feld(Value = "8243", Name = "Timestamp_Zeitpunkt_Medikamenteneinnahme", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 40)]
    timestamp_zeitpunkt_medikamenteneinnahme: Timestamp,
    #[feld(Value = "6208", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(MaxLaenge = 60)]
    handelsname: String,
    #[feld(Value = "6207", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 990)]
    rezeptur: String,
    #[feld(Value = "8171", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 9)]
    wirkstoff: Vec<Wirkstoff>,
    #[feld(Value = "8523", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    wirkstoffmenge: String,
    #[feld(Value = "8421", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(MaxLaenge = 20)]
    einheit: String,
    #[feld(Value = "3689", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 1)]
    status: Vec<MedikationsStatus<'static>>,
    #[feld(Value = "8226", Name = "Timestamp_Gueltig_ab", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 20)]
    timestamp_gueltig_ab: Timestamp,
    #[feld(Value = "8227", Name = "Timestamp_Gueltig_bis", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 21)]
    timestamp_gueltig_bis: Timestamp,
    #[feld(Value = "8167", Name = "Zusaetzliche_Informationen", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 26)]
    zusaetzliche_informationen: Fliesstext
}

impl Kontext for Medikament {
}
