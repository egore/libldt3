#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::enums::AnorganischesMaterial::AnorganischesMaterial;
use crate::model::enums::Materialart::Materialart;
use crate::model::enums::OrganischesMaterial::OrganischesMaterial;
use crate::model::objekte::Anhang::Anhang;
use crate::model::objekte::FehlermeldungAufmerksamkeit::FehlermeldungAufmerksamkeit;
use crate::model::objekte::Fliesstext::Fliesstext;
use crate::model::objekte::Medikament::Medikament;
use crate::model::objekte::Timestamp::Timestamp;
use crate::model::regel::F006::F006;
use crate::model::regel::kontext::K006::K006;

pub struct Material_AnorganischesMaterialErweitert {
    value: AnorganischesMaterial<'static>,
    zusaetzliche_informatioen: Fliesstext
}

pub struct Material_Medikamenteneinnahme {
    value: Option<bool>,
    medication: Medikament
}

/// Im Objekt werden die Informationen zur Identifikation des zu untersuchenden
/// Materials übermittelt sowie Angaben zum Material selbst.
pub struct Material {
    #[feld(Value = "7364", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(MaxLaenge = 60)]
    probengefaess_ident: String,
    #[feld(Value = "8429", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 4)]
    probenmaterial_index: String,
    #[feld(Value = "8428", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    probenmaterial_ident: String,
    #[feld(Value = "8430", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(MaxLaenge = 60)]
    probenmaterial_bezeichnung: String,
    #[feld(Value = "8431", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(MaxLaenge = 60)]
    probenmaterial_spezifikation: String,
    #[feld(Value = "7292", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    lokalisation_probenmaterial: String,
    #[feld(Value = "7310", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(Laenge = 1)]
    materialart: Materialart<'static>,
    #[feld(Value = "7311", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(Laenge = 1)]
    organisches_material: OrganischesMaterial<'static>,
    #[feld(Value = "7312", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(Laenge = 1)]
    anorganisches_material: Material_AnorganischesMaterialErweitert,
    #[feld(Value = "8504", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 1)]
    medikamenteneinnahme: Vec<Material_Medikamenteneinnahme>,
    #[feld(Value = "7318", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    nahrungsaufnahme_zeitpunkt_materialentnahme: Vec<String>,
    #[feld(Value = "8520", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    menge: String,
    #[feld(Value = "8421", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(MaxLaenge = 20)]
    einheit: String,
    #[feld(Value = "8522", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(Value = libldt3.model.regel.F006.class, Laenge = 4)]
    sammelzeit: String,
    #[feld(Value = "8219", Name = "Timestamp_Materialabnahme_entnahme", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 34/* XXX 33 according to the spec */
)]
    timestamp_materialabnahme_entnahme: Timestamp,
    #[feld(Value = "8220", Name = "Timestamp_Eingangserfassung_Material", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 36)]
    timestamp_eingangserfassung_material: Timestamp,
    #[feld(Value = "8126", Name = "Fehlermeldung_Aufmerksamkeit", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 28)]
    fehlermeldung_aufmerksamkeit: FehlermeldungAufmerksamkeit,
    #[feld(Value = "8167", Name = "Zusaetzliche_Informationen", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 26)]
    zusaetzliche_informationen: Vec<Fliesstext>,
    #[feld(Value = "8110", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 6)]
    anhang: Vec<Anhang>
}

impl Kontext for Material {
}
