#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;

/// Das Objekt Mutterschaft fasst die Angaben zur Mutterschaft zusammen.
pub struct Mutterschaft {
    #[feld(Value = "3668", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 2)]
    anzahl_schwangerschaften: Option<i32>,
    #[feld(Value = "3664", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(Laenge = 2)]
    anzahl_geburten: Option<i32>,
    #[feld(Value = "3666", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(Laenge = 2)]
    anzahl_kinder: Option<i32>
}

impl Kontext for Mutterschaft {
}
