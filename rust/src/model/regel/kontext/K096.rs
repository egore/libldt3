#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::enums::Auftragsstatus::Auftragsstatus;
use crate::model::enums::TestStatus::TestStatus;
use crate::model::regel::kontext::Kontextregel::Kontextregel;
use crate::model::regel::kontext::KontextregelHelper::KontextregelHelper;
use std::collections::HashSet;

/// Wenn Inhalt von FK 8401 = 2, darf der Inhalt von FK 8418 nicht 02, 05 oder 10 sein.
pub struct K096 {
    fieldtypes: HashSet<String>
}

impl Kontextregel for K096 {
}
