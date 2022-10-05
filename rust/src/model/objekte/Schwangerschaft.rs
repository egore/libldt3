#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::regel::F002::F002;
use crate::model::regel::F005::F005;
use crate::model::regel::F018::F018;
use datetime::LocalDate;

/// Dieses Objekt enthält schwangerschaftsspezifische Informationen.
pub struct Schwangerschaft {
    #[feld(Value = "8511", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Value = libldt3.model.regel.F005.class, Laenge = 3)]
    schwangerschaftsdauer: String,
    #[feld(Value = "8512", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Value = libldt3.model.regel.F018.class, Laenge = 8)]
    erster_tag_letzter_zyklus: LocalDate,
    #[feld(Value = "3471", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(Value = libldt3.model.regel.F002.class, Laenge = 8)]
    entbindungstermin: LocalDate
}

impl Kontext for Schwangerschaft {
}
