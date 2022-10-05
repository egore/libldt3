#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::enums::Abrechnungsinfo::Abrechnungsinfo;
use crate::model::enums::Gebuehrenordnung::Gebuehrenordnung;
use crate::model::regel::F008::F008;
use crate::model::regel::F009::F009;
use crate::model::regel::Regel::Regel;
use crate::model::regel::kontext::K005::K005;
use crate::model::regel::kontext::Kontextregel::Kontextregel;

pub struct Untersuchungsabrechnung_Gebuehrennummer {
    value: String,
    kosten: String,
    multiplikator: Option<i32>,
    begruendungstext: Vec<String>,
    abgerechnet: Option<bool>,
    asd: i32
}

/// Hier werden alle
/// Werte transportiert, die für die ordnungsgemäße Abrechnung des Auftrages
/// notwendig sind.
pub struct Untersuchungsabrechnung {
    #[feld(Value = "7303", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(MaxLaenge = 2)]
    abrechnungsinfo: Abrechnungsinfo<'static>,
    #[feld(Value = "4121", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 1)]
    gebuehrenordnung: Gebuehrenordnung<'static>,
    #[feld(Value = "5001", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Value = { libldt3.model.regel.F008.class, libldt3.model.regel.F009.class }, MaxLaenge = 9)]
    gebuehrennummer: Vec<Untersuchungsabrechnung_Gebuehrennummer>,
    #[feld(Value = "7259", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    abrechenbare_leistungen_katalog_id: String,
    #[feld(Value = "7251", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    katalog: String
}

impl Kontext for Untersuchungsabrechnung {
}
