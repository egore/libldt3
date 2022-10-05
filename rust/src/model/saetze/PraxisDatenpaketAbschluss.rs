#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::enums::Satzart::Satzart;
use crate::model::saetze::Satz::Satz;

/// Satzart: P (Praxis)-Datenpaket-Abschluss "8231"
pub struct PraxisDatenpaketAbschluss {
    #[feld(Value = "9300", Feldart = libldt3.annotations.Feldart.muss)]
    pruefsumme: String
}

impl Satz for PraxisDatenpaketAbschluss {
}

impl Kontext for PraxisDatenpaketAbschluss {
}
