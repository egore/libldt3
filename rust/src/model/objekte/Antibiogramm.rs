#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::enums::ResistenzInterpretation::ResistenzInterpretation;
use crate::model::enums::ResistenzNach::ResistenzNach;
use crate::model::enums::Sensitivitaet::Sensitivitaet;
use crate::model::objekte::Fliesstext::Fliesstext;

pub struct Antibiogramm_WirkstoffIdent {
    value: String,
    wirkstoff_generic_nummer: Vec<String>,
    wirkstoff_oid: Vec<String>,
    wirkstoffname: Vec<String>,
    keim_identifizierung: Vec<Antibiogramm_KeimIdentifizierung>
}

pub struct Antibiogramm_KeimIdentifizierung {
    value: String,
    sensitivitaet: Sensitivitaet<'static>,
    mhk: String,
    mhk_einheit: String,
    resistenz_interpretation: Vec<Antibiogramm_ResistenzInterpretationErweitert>
}

pub struct Antibiogramm_ResistenzInterpretationErweitert {
    value: ResistenzInterpretation<'static>,
    resistenz_nach: ResistenzNach<'static>
}

/// Die Darstellung des Antibiogramms erfolgt als mehrdimensionale
/// Matrix.
pub struct Antibiogramm {
    #[feld(Value = "7287", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(MaxLaenge = 60)]
    wirkstoff_ident: Vec<Antibiogramm_WirkstoffIdent>,
    #[feld(Value = "8237", Name = "Ergebnistext", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(Laenge = 12)]
    ergebnistext: Fliesstext
}

impl Kontext for Antibiogramm {
}
