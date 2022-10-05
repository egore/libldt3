#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::enums::Satzart::Satzart;
use crate::model::objekte::Anhang::Anhang;
use crate::model::objekte::Befundinformationen::Befundinformationen;
use crate::model::objekte::Einsenderidentifikation::Einsenderidentifikation;
use crate::model::objekte::Fliesstext::Fliesstext;
use crate::model::objekte::Koerperkenngroessen::Koerperkenngroessen;
use crate::model::objekte::Laborergebnisbericht::Laborergebnisbericht;
use crate::model::objekte::Laborkennung::Laborkennung;
use crate::model::objekte::Material::Material;
use crate::model::objekte::Mutterschaft::Mutterschaft;
use crate::model::objekte::Patient::Patient;
use crate::model::objekte::Schwangerschaft::Schwangerschaft;
use crate::model::objekte::Tier::Tier;
use crate::model::objekte::Veranlassungsgrund::Veranlassungsgrund;
use crate::model::regel::kontext::K005::K005;
use crate::model::saetze::Satz::Satz;

/// Befund "8205"
pub struct Befund {
    #[feld(Value = "8136", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 12)]
    laborkennung: Vec<Laborkennung>,
    #[feld(Value = "8122", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 23)]
    sender_identification: Einsenderidentifikation,
    #[feld(Value = "8145", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 7)]
    patient: Patient,
    #[feld(Value = "8169", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(Laenge = 19)]
    koerperkenngroessen: Koerperkenngroessen,
    #[feld(Value = "8150", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(Laenge = 15)]
    schwangerschaft: Schwangerschaft,
    #[feld(Value = "8140", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(Laenge = 12)]
    mutterschaft: Mutterschaft,
    #[feld(Value = "8153", Name = "Tier_Sonstiges", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 14)]
    tier: Tier,
    #[feld(Value = "8117", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 19)]
    befundinformationen: Befundinformationen,
    #[feld(Value = "8127", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 18)]
    veranlassungsgrund: Vec<Veranlassungsgrund>,
    #[feld(Value = "8137", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 8)]
    material: Vec<Material>,
    #[feld(Value = "8135", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 20)]
    laborergebnisbericht: Laborergebnisbericht,
    #[feld(Value = "8167", Name = "Zusaetzliche_Informationen", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 26)]
    text: Vec<Fliesstext>,
    #[feld(Value = "8110", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 6)]
    anhang: Vec<Anhang>
}

impl Satz for Befund {
}

impl Kontext for Befund {
}
