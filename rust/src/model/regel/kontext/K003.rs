#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::enums::Abrechnungsinfo::Abrechnungsinfo;
use crate::model::regel::kontext::Kontextregel::Kontextregel;
use crate::model::regel::kontext::KontextregelHelper::KontextregelHelper;
use std::collections::HashSet;

/// Wird die FK 8410 (Test-Ident) im Kontext mit der Überweisung von
/// Laborleistungen an einen Laborfacharzt verwendet, muss die FK 8411
/// (Testbezeichnung) im Datensatz vorkommen (mit Inhalt der FK 8411 muss das
/// Auftragsfeld des digitalen Musters 10 befüllt werden).
pub struct K003 {
    fieldtypes: HashSet<String>
}

impl Kontextregel for K003 {
}
