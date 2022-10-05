#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::enums::EinheitMesswert::EinheitMesswert;
use crate::model::enums::GrenzwertindikatorErweitert::GrenzwertindikatorErweitert;
use crate::model::enums::Normwertspezifikation::Normwertspezifikation;
use crate::model::objekte::Fliesstext::Fliesstext;
use crate::model::regel::kontext::K002::K002;
use crate::model::regel::kontext::K099::K099;

pub struct Normalwert_NormalwertGrenze {
    value: Option<f32>,
    einheit_des_wertes: EinheitMesswert<'static>,
    size_unit: String
}

/// Mit diesem Objekt werden Norm- und Referenzbereiche strukturiert dargestellt.
pub struct Normalwert {
    #[feld(Value = "8424", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 2)]
    normwertspezifikation: Normwertspezifikation<'static>,
    #[feld(Value = "8167", Name = "Zusaetzliche_Informationen", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(Laenge = 26)]
    zusaetzliche_informationen: Fliesstext,
    #[feld(Value = "8460", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(MaxLaenge = 990)]
    normalwert_text: Vec<String>,
    #[feld(Value = "8461", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(MaxLaenge = 60)]
    normalwert_untere_grenze: Normalwert_NormalwertGrenze,
    #[feld(Value = "8462", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(MaxLaenge = 60)]
    normalwert_obere_grenze: Normalwert_NormalwertGrenze,
    #[feld(Value = "7316", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(MaxLaenge = 60)]
    normalwert_listenbezeichnung: String,
    #[feld(Value = "7317", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(MaxLaenge = 60)]
    normalwert_listenzeile: Vec<String>,
    #[feld(Value = "7363", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    alarmwert_untere_grenze: Normalwert_NormalwertGrenze,
    #[feld(Value = "7371", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    alarmwert_obere_grenze: Normalwert_NormalwertGrenze,
    #[feld(Value = "8422", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(MaxLaenge = 2)]
    grenzwertindikator: GrenzwertindikatorErweitert
}

impl Kontext for Normalwert {
}
