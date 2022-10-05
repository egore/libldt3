#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::enums::DatensatzAbsender::DatensatzAbsender;
use crate::model::enums::Satzart::Satzart;
use crate::model::objekte::Einsenderidentifikation::Einsenderidentifikation;
use crate::model::objekte::Kopfdaten::Kopfdaten;
use crate::model::saetze::Satz::Satz;

/// Satzart: P (Praxis)-Datenpaket-Header "8230"
pub struct PraxisDatenpaketHeader {
    #[feld(Value = "8132", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 9)]
    kopfdaten: Kopfdaten,
    #[feld(Value = "7265", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 1)]
    absender: DatensatzAbsender<'static>,
    #[feld(Value = "8122", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 23)]
    einsenderidentifikation: Vec<Einsenderidentifikation>
}

impl Satz for PraxisDatenpaketHeader {
}

impl Kontext for PraxisDatenpaketHeader {
}
