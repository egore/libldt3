#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::enums::Satzart::Satzart;
use crate::model::objekte::Betriebsstaette::Betriebsstaette;
use crate::model::objekte::Kopfdaten::Kopfdaten;
use crate::model::objekte::Laborkennung::Laborkennung;
use crate::model::saetze::Satz::Satz;

/// Satzart: L (Labor)-Datenpaket-Header "8220"
pub struct LaborDatenpaketHeader {
    #[feld(Value = "8132", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 9)]
    kopfdaten: Kopfdaten,
    #[feld(Value = "8136", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 12)]
    laborkennung: Laborkennung,
    #[feld(Value = "8119", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 15)]
    betriebsstaette: Betriebsstaette
}

impl Satz for LaborDatenpaketHeader {
}

impl Kontext for LaborDatenpaketHeader {
}
