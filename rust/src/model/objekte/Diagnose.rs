#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::enums::Diagnosesicherheit::Diagnosesicherheit;
use crate::model::enums::Lokalisation::Lokalisation;
use crate::model::regel::F004::F004;

/// Mit diesem Objekt können Angaben zu Diagnosen des Patienten übertragen
/// werden.
pub struct Diagnose {
    #[feld(Value = "4207", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    diagnose_verdachtsdiagnose: Vec<String>,
    #[feld(Value = "6001", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Value = libldt3.model.regel.F004.class)]
    icd_code: String,
    #[feld(Value = "6003", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(Laenge = 1)]
    diagnosesicherheit: Diagnosesicherheit<'static>,
    #[feld(Value = "6004", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(Laenge = 1)]
    lokalisation: Lokalisation<'static>,
    #[feld(Value = "6006", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(MaxLaenge = 60)]
    erlaeuterung: Vec<String>,
    #[feld(Value = "6008", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(MaxLaenge = 60)]
    ausnahmetatbestand: Vec<String>
}

impl Kontext for Diagnose {
}
