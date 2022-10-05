#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::enums::Dokumentenquelle::Dokumentenquelle;
use crate::model::enums::Dokumententyp::Dokumententyp;
use crate::model::objekte::Fliesstext::Fliesstext;
use crate::model::regel::kontext::K001::K001;
use crate::model::regel::kontext::K075::K075;
use crate::model::regel::kontext::Kontextregel::Kontextregel;

/// Im Objekt Anhang können Informationen wie Befunde, Fotos oder sonstige
/// Dokumentationen, die in einem digitalen Standardformat vorliegen,
/// transportiert werden.
pub struct Anhang {
    #[feld(Value = "9970", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(MaxLaenge = 3)]
    dokument_typ: Dokumententyp<'static>,
    #[feld(Value = "6221", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 1)]
    kennzeichnung_fremdbefund: Option<bool>,
    #[feld(Value = "6305", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(MaxLaenge = 60)]
    datei_verweis: String,
    #[feld(Value = "8242", Name = "base64-kodierte_Anlage", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 22)]
    base64anlage: Fliesstext,
    #[feld(Value = "6303", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(MaxLaenge = 60)]
    dateiformat: String,
    #[feld(Value = "6328", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    dateicodierung: String,
    #[feld(Value = "6327", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(MaxLaenge = 60)]
    beschreibung: String,
    #[feld(Value = "9908", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(MaxLaenge = 60)]
    originaldokument_pfad: String,
    #[feld(Value = "9909", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(MaxLaenge = 60)]
    langzeit_archivierung_pfad: String,
    #[feld(Value = "9980", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    externe_dokument_ids: Vec<String>,
    #[feld(Value = "9981", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(Laenge = 1)]
    dokumentenquelle: Dokumentenquelle<'static>
}

impl Kontext for Anhang {
}
