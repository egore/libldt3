#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::enums::AnforderungNothilfepass::AnforderungNothilfepass;
use crate::model::enums::Antikoerpersuchtest::Antikoerpersuchtest;
use crate::model::enums::DirekterCoombstest::DirekterCoombstest;
use crate::model::enums::TestStatus::TestStatus;
use crate::model::objekte::Fliesstext::Fliesstext;
use crate::model::objekte::Timestamp::Timestamp;
use crate::model::objekte::Untersuchungsabrechnung::Untersuchungsabrechnung;
use crate::model::regel::kontext::K076::K076;

pub struct TransfusionsmedizinMutterschaftsvorsorge {
    #[feld(Value = "7304", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(MaxLaenge = 60)]
    ergebnis_id: String,
    #[feld(Value = "7364", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(MaxLaenge = 60)]
    probengefaess_ident: Vec<String>,
    #[feld(Value = "8418", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 1)]
    teststatus: TestStatus<'static>,
    #[feld(Value = "3412", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 6)]
    blutgruppe_eurocode: String,
    #[feld(Value = "3413", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 1)]
    antikoerpersuchtest: Antikoerpersuchtest<'static>,
    #[feld(Value = "3414", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    spezifitaet_weitere_erythrozytenantigene: String,
    #[feld(Value = "3415", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    spezifitaet_erythrozytenantikoerper: String,
    #[feld(Value = "3416", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    spezifitaet_hla_hpa_hna_antigene: String,
    #[feld(Value = "3417", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    spezifitaet_hla_hpa_hna_antikoerper: String,
    #[feld(Value = "7263", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    test_id: String,
    #[feld(Value = "3418", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 1)]
    direkter_coombstest: DirekterCoombstest<'static>,
    #[feld(Value = "3419", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    ergebnis_kreuzprobe: Vec<String>,
    #[feld(Value = "7275", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    terminologie_id: Vec<String>,
    #[feld(Value = "3420", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 1)]
    anforderung_nothilfepass: AnforderungNothilfepass<'static>,
    #[feld(Value = "8220", Name = "Timestamp_Eingangserfassung_Material", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 36)]
    timestamp_eingangserfassung_material: Timestamp,
    #[feld(Value = "8222", Name = "Timestamp_Beginn_Analytik", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 25)]
    timestamp_beginn_analytik: Timestamp,
    #[feld(Value = "8223", Name = "Timestamp_Ergebniserstellung", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 28)]
    timestamp_ergebniserstellung: Timestamp,
    #[feld(Value = "8224", Name = "Timestamp_QM_Erfassung", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 22)]
    timestamp_qm_erfassung: Timestamp,
    #[feld(Value = "8225", Name = "Timestamp_Messung", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 17)]
    timestamp_messung: Timestamp,
    #[feld(Value = "8167", Name = "Zusaetzliche_Informationen", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 26)]
    zusaetzliche_informationen: Vec<Fliesstext>,
    #[feld(Value = "8158", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 23)]
    untersuchungsabrechnung: Untersuchungsabrechnung
}

impl Kontext for TransfusionsmedizinMutterschaftsvorsorge {
}
