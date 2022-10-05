#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::objekte::Anhang::Anhang;
use crate::model::objekte::Fliesstext::Fliesstext;
use crate::model::objekte::Timestamp::Timestamp;
use crate::model::regel::F002::F002;
use crate::model::regel::F017::F017;
use datetime::LocalDate;

/// In diesem Objekt können Information zu einem Tumor sowohl für die
/// Beauftragung und für den Befund transportiert werden.
pub struct Tumor {
    #[feld(Value = "7364", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(MaxLaenge = 60)]
    probengefaess_ident: String,
    #[feld(Value = "7372", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    tumorklassifikation: String,
    #[feld(Value = "7373", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 5)]
    grading: String,
    #[feld(Value = "7374", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 4)]
    stadium: String,
    #[feld(Value = "7375", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Value = libldt3.model.regel.F017.class, Laenge = 4)]
    jahr_tumordiagnose: String,
    #[feld(Value = "7376", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(MaxLaenge = 60)]
    lokalisation_tumor: String,
    #[feld(Value = "7377", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    masse: Vec<String>,
    #[feld(Value = "7378", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    farbe: String,
    #[feld(Value = "7379", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    infiltrationstiefe: String,
    #[feld(Value = "3424", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Value = libldt3.model.regel.F002.class, Laenge = 8)]
    therapiebeginn: LocalDate,
    #[feld(Value = "3425", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Value = libldt3.model.regel.F002.class, Laenge = 8)]
    therapieende: LocalDate,
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
    #[feld(Value = "8225", Name = "Timestamp_Messung", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 17)]
    timestamp_messung: Timestamp,
    #[feld(Value = "8167", Name = "Zusaetzliche_Informationen", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 26)]
    zusaetzliche_informationen: Vec<Fliesstext>,
    #[feld(Value = "8110", Name = "Anhang", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 6)]
    anhang: Vec<Anhang>
}

impl Kontext for Tumor {
}
