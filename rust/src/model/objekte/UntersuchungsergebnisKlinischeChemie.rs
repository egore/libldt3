#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::enums::DarstellungErgebniswerte::DarstellungErgebniswerte;
use crate::model::enums::EinheitMesswert::EinheitMesswert;
use crate::model::enums::KatalogIdAnforderbareLeistungen::KatalogIdAnforderbareLeistungen;
use crate::model::enums::TestStatus::TestStatus;
use crate::model::objekte::Anhang::Anhang;
use crate::model::objekte::Fliesstext::Fliesstext;
use crate::model::objekte::Namenskennung::Namenskennung;
use crate::model::objekte::Normalwert::Normalwert;
use crate::model::objekte::Timestamp::Timestamp;
use crate::model::objekte::Untersuchungsabrechnung::Untersuchungsabrechnung;
use crate::model::regel::kontext::K072::K072;
use crate::model::regel::kontext::K076::K076;
use crate::model::regel::kontext::Kontextregel::Kontextregel;

pub struct UntersuchungsergebnisKlinischeChemie_KatalogReferenz {
    value: KatalogIdAnforderbareLeistungen<'static>,
    katalog_url: String,
    katalog_bezeichnung: String,
    analysen_id: String,
    leistungsbezeichnung: String,
    teststatus: TestStatus<'static>
}

pub struct UntersuchungsergebnisKlinischeChemie_Test {
    value: String,
    testbezeichnung: String,
    test_id: String,
    test_geraet_uid: String,
    teststatus: TestStatus<'static>,
    testmethode: Vec<String>
}

pub struct UntersuchungsergebnisKlinischeChemie_DarstellungErgebniswerteErweitert {
    value: DarstellungErgebniswerte<'static>,
    ergebnis_wert: Vec<UntersuchungsergebnisKlinischeChemie_ErgebnisWert>,
    testbezogene_hinweise: Fliesstext
}

pub struct UntersuchungsergebnisKlinischeChemie_ErgebnisWert {
    value: String,
    einheit_norm: EinheitMesswert<'static>,
    einheit: String,
    normal_value: Vec<Normalwert>,
    timestamp: Timestamp,
    ergebnistext: Fliesstext
}

/// In diesem Objekt werden die Ergebnisse aus dem Bereich Klinische Chemie
/// übermittelt.
pub struct UntersuchungsergebnisKlinischeChemie {
    #[feld(Value = "7304", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(MaxLaenge = 60)]
    ergebnis_id: String,
    #[feld(Value = "7364", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(MaxLaenge = 60)]
    probengefaess_ident: Vec<String>,
    #[feld(Value = "7260", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 1)]
    anforderbare_leistungen_katalog_id: UntersuchungsergebnisKlinischeChemie_KatalogReferenz,
    #[feld(Value = "8410", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(MaxLaenge = 60)]
    test_ident: UntersuchungsergebnisKlinischeChemie_Test,
    #[feld(Value = "7306", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 2)]
    darstellung_ergebniswerte: Vec<UntersuchungsergebnisKlinischeChemie_DarstellungErgebniswerteErweitert>,
    #[feld(Value = "8167", Name = "Zusaetzliche_Informationen", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 26)]
    zusaetzliche_informationen: Vec<Fliesstext>,
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

impl Kontext for UntersuchungsergebnisKlinischeChemie {
}
