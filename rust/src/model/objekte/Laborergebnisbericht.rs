#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::objekte::Anhang::Anhang;
use crate::model::objekte::Fliesstext::Fliesstext;
use crate::model::objekte::Namenskennung::Namenskennung;
use crate::model::objekte::Timestamp::Timestamp;
use crate::model::objekte::TransfusionsmedizinMutterschaftsvorsorge::TransfusionsmedizinMutterschaftsvorsorge;
use crate::model::objekte::Tumor::Tumor;
use crate::model::objekte::UntersuchungsergebnisKlinischeChemie::UntersuchungsergebnisKlinischeChemie;
use crate::model::objekte::UntersuchungsergebnisMikrobiologie::UntersuchungsergebnisMikrobiologie;
use crate::model::objekte::UntersuchungsergebnisZytologie::UntersuchungsergebnisZytologie;
use crate::model::objekte::UntersuchungsergebnisZytologieKrebsvorsorge::UntersuchungsergebnisZytologieKrebsvorsorge;

/// Im Objekt werden die Untersuchungsergebnisse zusammengefasst.
pub struct Laborergebnisbericht {
    #[feld(Value = "8160", Name = "UE_Klinische_Chemie", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 19)]
    klinische_chemie: Vec<UntersuchungsergebnisKlinischeChemie>,
    #[feld(Value = "8161", Name = "UE_Mikrobiologie", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 16)]
    mikrobiologie: Vec<UntersuchungsergebnisMikrobiologie>,
    #[feld(Value = "8162", Name = "UE_Zytologie_Krebsvorsorge", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 26)]
    zytologie_krebsvorsorge: Vec<UntersuchungsergebnisZytologieKrebsvorsorge>,
    #[feld(Value = "8163", Name = "UE_Zytologie", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 12)]
    zytologie: Vec<UntersuchungsergebnisZytologie>,
    #[feld(Value = "8155", Name = "Transfusionsmedizin_Mutterschaftsvorsorge", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 41)]
    transfusionsmedizin_mutterschaftsvorsorge: Vec<TransfusionsmedizinMutterschaftsvorsorge>,
    #[feld(Value = "8156", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 5)]
    tumor: Vec<Tumor>,
    #[feld(Value = "8221", Name = "Timestamp_Erstellung_Laborergebnisbericht", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 41)]
    timestamp_erstellung_laborergebnisbericht: Timestamp,
    #[feld(Value = "8167", Name = "Zusaetzliche_Informationen", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 26)]
    text: Vec<Fliesstext>,
    #[feld(Value = "8110", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 6)]
    anhang: Vec<Anhang>,
    #[feld(Value = "8141", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 13)]
    namenskennung: Namenskennung
}

impl Kontext for Laborergebnisbericht {
}
