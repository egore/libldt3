#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::enums::Satzart::Satzart;
use crate::model::objekte::Abrechnungsinformation::Abrechnungsinformation;
use crate::model::objekte::Anhang::Anhang;
use crate::model::objekte::Auftragsinformation::Auftragsinformation;
use crate::model::objekte::Einsenderidentifikation::Einsenderidentifikation;
use crate::model::objekte::Fliesstext::Fliesstext;
use crate::model::objekte::Koerperkenngroessen::Koerperkenngroessen;
use crate::model::objekte::Material::Material;
use crate::model::objekte::Mutterschaft::Mutterschaft;
use crate::model::objekte::Patient::Patient;
use crate::model::objekte::Schwangerschaft::Schwangerschaft;
use crate::model::objekte::Tier::Tier;
use crate::model::objekte::Untersuchungsanforderung::Untersuchungsanforderung;
use crate::model::objekte::Veranlassungsgrund::Veranlassungsgrund;
use crate::model::saetze::Satz::Satz;

/// Satzart: Auftrag "8215"
pub struct Auftrag {
    #[feld(Value = "8122", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 23)]
    einsenderidentifikation: Einsenderidentifikation,
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
    #[feld(Value = "8113", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 19)]
    auftragsinformation: Auftragsinformation,
    #[feld(Value = "8127", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 18)]
    veranlassungsgrund: Vec<Veranlassungsgrund>,
    #[feld(Value = "8101", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 22)]
    abrechnungsinformationen: Abrechnungsinformation,
    #[feld(Value = "8137", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    material: Vec<Material>,
    #[feld(Value = "8159", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 24)]
    untersuchungsanforderung: Vec<Untersuchungsanforderung>,
    #[feld(Value = "8167", Name = "Zusaetzliche_Informationen", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 26)]
    zusaeztliche_informationen: Vec<Fliesstext>,
    #[feld(Value = "8110", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 6)]
    anhang: Vec<Anhang>
}

impl Satz for Auftrag {
}

impl Kontext for Auftrag {
}
