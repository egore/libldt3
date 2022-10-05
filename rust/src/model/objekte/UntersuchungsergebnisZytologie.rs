#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::enums::Ergebnis2::Ergebnis2;
use crate::model::enums::Ergebnis::Ergebnis;
use crate::model::enums::Grenzwertindikator::Grenzwertindikator;
use crate::model::enums::KatalogIdAnforderbareLeistungen::KatalogIdAnforderbareLeistungen;
use crate::model::enums::TestStatus::TestStatus;
use crate::model::objekte::Anhang::Anhang;
use crate::model::objekte::FehlermeldungAufmerksamkeit::FehlermeldungAufmerksamkeit;
use crate::model::objekte::Fliesstext::Fliesstext;
use crate::model::objekte::Namenskennung::Namenskennung;
use crate::model::objekte::Timestamp::Timestamp;
use crate::model::objekte::Untersuchungsabrechnung::Untersuchungsabrechnung;
use crate::model::regel::kontext::K076::K076;

pub struct UntersuchungsergebnisZytologie_RecallEmpfohlen {
    value: String,
    timestamp: Timestamp
}

pub struct UntersuchungsergebnisZytologie_KatalogReferenz {
    value: KatalogIdAnforderbareLeistungen<'static>,
    katalog_url: String,
    katalog_bezeichnung: String,
    analysen_id: String,
    leistungsbezeichnung: String
}

pub struct UntersuchungsergebnisZytologie_GrenzwertindikatorLaborwert {
    value: Grenzwertindikator<'static>,
    fehlermeldung_aufmerksamkeit: FehlermeldungAufmerksamkeit
}

pub struct UntersuchungsergebnisZytologie_Test {
    value: String,
    testbezeichnung: String,
    teststatus: TestStatus<'static>,
    grenzwertindikator_laborwert: Vec<UntersuchungsergebnisZytologie_GrenzwertindikatorLaborwert>
}

/// In diesem Objekt können weitere Ergebnisse aus dem Bereich Zytologie
/// transportiert werden.
pub struct UntersuchungsergebnisZytologie {
    #[feld(Value = "7304", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(MaxLaenge = 60)]
    ergebnis_id: String,
    #[feld(Value = "7320", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(Laenge = 1)]
    recall_empfohlen: Vec<UntersuchungsergebnisZytologie_RecallEmpfohlen>,
    #[feld(Value = "7364", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(MaxLaenge = 60)]
    probengefaess_ident: Vec<String>,
    #[feld(Value = "7260", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 1)]
    katalog_id_anforderbare_leistungen: UntersuchungsergebnisZytologie_KatalogReferenz,
    #[feld(Value = "8410", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(MaxLaenge = 60)]
    test_ident: Vec<UntersuchungsergebnisZytologie_Test>,
    #[feld(Value = "8237", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 10/* XXX 12 according to spec */
)]
    ergebnistext: Fliesstext,
    #[feld(Value = "7368", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 1)]
    zellmaterial_nicht_verwertbar: Option<bool>,
    #[feld(Value = "7400", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 1)]
    hpv_befund: String,
    #[feld(Value = "7401", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 1)]
    high_risk: Ergebnis<'static>,
    #[feld(Value = "7402", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(MaxLaenge = 2)]
    high_risk_typ: Vec<String>,
    #[feld(Value = "7403", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 1)]
    low_risk: Ergebnis<'static>,
    #[feld(Value = "7404", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(MaxLaenge = 2)]
    low_risk_typ: Vec<String>,
    #[feld(Value = "7418", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 1)]
    p16ki67: Ergebnis<'static>,
    #[feld(Value = "7419", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 1)]
    l1: Ergebnis<'static>,
    #[feld(Value = "7422", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 1)]
    chlamydien: Ergebnis<'static>,
    #[feld(Value = "7425", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 1)]
    extragynaekologische_zytologie: Ergebnis2<'static>,
    #[feld(Value = "7426", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 1)]
    neisseria_gonorrhoeae: Ergebnis<'static>,
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
    #[feld(Value = "8141", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 13)]
    namenskennung: Namenskennung,
    #[feld(Value = "8158", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 23)]
    untersuchungsabrechnung: Untersuchungsabrechnung
}

impl Kontext for UntersuchungsergebnisZytologie {
}
