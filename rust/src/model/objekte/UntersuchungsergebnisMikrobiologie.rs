#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::enums::Ergebnis::Ergebnis;
use crate::model::enums::KatalogIdAnforderbareLeistungen::KatalogIdAnforderbareLeistungen;
use crate::model::enums::Nachweisverfahren::Nachweisverfahren;
use crate::model::enums::ResistenzMethode::ResistenzMethode;
use crate::model::enums::TestStatus::TestStatus;
use crate::model::enums::Wachstum::Wachstum;
use crate::model::objekte::Anhang::Anhang;
use crate::model::objekte::Antibiogramm::Antibiogramm;
use crate::model::objekte::Fliesstext::Fliesstext;
use crate::model::objekte::Namenskennung::Namenskennung;
use crate::model::objekte::Normalwert::Normalwert;
use crate::model::objekte::Timestamp::Timestamp;
use crate::model::objekte::Untersuchungsabrechnung::Untersuchungsabrechnung;
use crate::model::regel::kontext::K073::K073;
use crate::model::regel::kontext::K076::K076;
use crate::model::regel::kontext::Kontextregel::Kontextregel;

pub struct UntersuchungsergebnisMikrobiologie_Keim {
    value: String,
    keim_name: String,
    ergebnis: Ergebnis<'static>,
    wachstum: Wachstum<'static>,
    einheit: Vec<String>,
    keim_oid: String,
    keim_nummer: String,
    katalog_id: String,
    katalog_bezeichnung: String,
    testbezogene_hinweise: Fliesstext,
    timestamp: Timestamp,
    ergebnistext: Fliesstext
}

pub struct UntersuchungsergebnisMikrobiologie_KatalogReferenz {
    value: KatalogIdAnforderbareLeistungen<'static>,
    katalog_url: String,
    katalog_bezeichnung: String,
    analysen_id: String,
    leistungsbezeichnung: String
}

pub struct UntersuchungsergebnisMikrobiologie_NachweisverfahrenErweitert {
    value: Nachweisverfahren<'static>,
    testmethode: String
}

pub struct UntersuchungsergebnisMikrobiologie_ResistenzMethodeErweitert {
    value: ResistenzMethode<'static>,
    antibiogramm: Antibiogramm
}

/// Um diese Daten strukturiert zu übertragen wird eine in der
/// Mikrobiologie übliche hierarchische Vorgehensweise definiert: Ausgangspunkt
/// ist immer das Material und die dazugehörige Anforderung. Aus diesen
/// Anforderungen erfolgt über verschiedene Nachweisverfahren eine
/// Stufendiagnostik zur Keimbestimmung, optional die Bestimmung der Breakpunkte
/// bzw. MHK´s (Minimale Hemm Konzentration) für einzelne Antibiotika. Die
/// Erregermenge wird als semiquantitatives Ergebnis abhängig des
/// Untersuchungsmaterials dargestellt.
pub struct UntersuchungsergebnisMikrobiologie {
    #[feld(Value = "7304", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(MaxLaenge = 60)]
    ergebnis_id: String,
    #[feld(Value = "7364", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(MaxLaenge = 60)]
    probengefaess_ident: Vec<String>,
    #[feld(Value = "7260", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 1)]
    anforderbare_leistungen_katalog_id: UntersuchungsergebnisMikrobiologie_KatalogReferenz,
    #[feld(Value = "8410", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(MaxLaenge = 60)]
    test_ident: String,
    #[feld(Value = "8411", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(MaxLaenge = 60)]
    testbezeichnung: String,
    #[feld(Value = "8434", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(MaxLaenge = 60)]
    anforderung: String,
    #[feld(Value = "7281", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 1)]
    nachweisverfahren: Vec<UntersuchungsergebnisMikrobiologie_NachweisverfahrenErweitert>,
    #[feld(Value = "8418", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 1)]
    teststatus: TestStatus<'static>,
    #[feld(Value = "7354", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(MaxLaenge = 60)]
    keime: Vec<UntersuchungsergebnisMikrobiologie_Keim>,
    #[feld(Value = "7286", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 1)]
    resistenz_methode: Vec<UntersuchungsergebnisMikrobiologie_ResistenzMethodeErweitert>,
    #[feld(Value = "8142", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 10)]
    normal_value: Vec<Normalwert>,
    #[feld(Value = "8237", Name = "Ergebnistext", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 12)]
    ergebnistext: Fliesstext,
    #[feld(Value = "8220", Name = "Timestamp_Eingangserfassung_Material", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 36)]
    material_delivery_timestamp: Timestamp,
    #[feld(Value = "8222", Name = "Timestamp_Beginn_Analytik", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 25)]
    start_analytics_timestamp: Timestamp,
    #[feld(Value = "8223", Name = "Timestamp_Ergebniserstellung", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 28)]
    result_timestamp: Timestamp,
    #[feld(Value = "8224", Name = "Timestamp_QM_Erfassung", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 22)]
    qm_timestamp: Timestamp,
    #[feld(Value = "8225", Name = "Timestamp_Messung", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 17)]
    timestamp_messung: Timestamp,
    #[feld(Value = "8167", Name = "Zusaetzliche_Informationen", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 26)]
    zusaetzliche_informationen: Vec<Fliesstext>,
    #[feld(Value = "8141", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 13)]
    namenskennung: Namenskennung,
    #[feld(Value = "8158", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 23)]
    untersuchungsabrechnung: Untersuchungsabrechnung,
    #[feld(Value = "8110", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 6)]
    anhang: Vec<Anhang>
}

impl Kontext for UntersuchungsergebnisMikrobiologie {
}
