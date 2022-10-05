#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::enums::Auftragsstatus::Auftragsstatus;
use crate::model::enums::Satzart::Satzart;
use crate::model::regel::kontext::Kontextregel::Kontextregel;
use crate::model::regel::kontext::KontextregelHelper::KontextregelHelper;
use std::collections::HashSet;

/// Nur in Befunden mit dem Status "Auftrag abgeschlossen" können
/// Abrechnungsinformationen übertragen werden.
pub struct K005 {
    fieldtypes: HashSet<String>
}

impl Kontextregel for K005 {
}
