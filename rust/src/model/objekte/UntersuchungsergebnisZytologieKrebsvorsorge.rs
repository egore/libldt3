#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::enums::EndozervikaleZellen::EndozervikaleZellen;
use crate::model::enums::GrenzwertindikatorErweitert::GrenzwertindikatorErweitert;
use crate::model::enums::NachkontrollGrund::NachkontrollGrund;
use crate::model::enums::TestStatus::TestStatus;
use crate::model::objekte::Anhang::Anhang;
use crate::model::objekte::FehlermeldungAufmerksamkeit::FehlermeldungAufmerksamkeit;
use crate::model::objekte::Fliesstext::Fliesstext;
use crate::model::objekte::KrebsfrueherkennungFrauen::KrebsfrueherkennungFrauen;
use crate::model::objekte::Namenskennung::Namenskennung;
use crate::model::objekte::Timestamp::Timestamp;
use crate::model::objekte::Untersuchungsabrechnung::Untersuchungsabrechnung;
use crate::model::regel::kontext::K076::K076;
use crate::model::regel::kontext::K099::K099;

pub struct UntersuchungsergebnisZytologieKrebsvorsorge_TestIdent {
    value: String,
    testbezeichnung: String,
    grenzwertindikator: Vec<GrenzwertindikatorErweitert>,
    ergebnistext_verweis: Fliesstext
}

/// Die Inhalte richten sich nach dem Muster 39b.
pub struct UntersuchungsergebnisZytologieKrebsvorsorge {
    #[feld(Value = "7304", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(MaxLaenge = 60)]
    ergebnis_id: String,
    #[feld(Value = "7364", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(MaxLaenge = 60)]
    probengefaess_ident: Vec<String>,
    #[feld(Value = "8410", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(MaxLaenge = 60)]
    test_ident: UntersuchungsergebnisZytologieKrebsvorsorge_TestIdent,
    #[feld(Value = "8418", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 1)]
    teststatus: TestStatus<'static>,
    #[feld(Value = "7368", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 1)]
    zellmaterial_nicht_verwertbar: Option<bool>,
    #[feld(Value = "7405", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 1)]
    endozervikale_zellen: EndozervikaleZellen<'static>,
    #[feld(Value = "7406", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(MaxLaenge = 10)]
    proliferationsgrad: String,
    #[feld(Value = "7407", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 1)]
    doederleinflora: Option<bool>,
    #[feld(Value = "7408", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 1)]
    mischflora: Option<bool>,
    #[feld(Value = "7409", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 1)]
    kokkenflora: Option<bool>,
    #[feld(Value = "7410", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 1)]
    trichomonaden: Option<bool>,
    #[feld(Value = "7411", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 1)]
    candida: Option<bool>,
    #[feld(Value = "7412", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 1)]
    gardnerella: Option<bool>,
    #[feld(Value = "7414", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(MaxLaenge = 5)]
    gruppe: String,
    #[feld(Value = "7413", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(MaxLaenge = 4)]
    codierung_gruppe: String,
    #[feld(Value = "7415", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 1)]
    zytologische_kontrolle: Option<bool>,
    #[feld(Value = "7416", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 1)]
    nachkontroll_grund: NachkontrollGrund<'static>,
    #[feld(Value = "7417", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 1)]
    histologische_klaerung: Vec<Option<bool>>,
    #[feld(Value = "8237", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 10/* XXX 12 according to spec */
)]
    ergebnistext: Fliesstext,
    #[feld(Value = "8134", Name = "Krebsfrueherkennung_Frauen", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 26)]
    krebsfrueherkennung_frauen: KrebsfrueherkennungFrauen,
    #[feld(Value = "8126", Name = "Fehlermeldung_Aufmerksamkeit", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 28)]
    fehlermeldung_aufmerksamkeit: FehlermeldungAufmerksamkeit,
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
    #[feld(Value = "8110", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 6)]
    anhang: Vec<Anhang>,
    #[feld(Value = "8141", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 13)]
    namenskennung: Namenskennung,
    #[feld(Value = "8158", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 23)]
    untersuchungsabrechnung: Untersuchungsabrechnung
}

impl Kontext for UntersuchungsergebnisZytologieKrebsvorsorge {
}
