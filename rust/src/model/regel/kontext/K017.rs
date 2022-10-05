#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::regel::kontext::Kontextregel::Kontextregel;
use crate::model::regel::kontext::KontextregelHelper::KontextregelHelper;
use std::collections::HashSet;

/// FK 3112 und/oder FK 3121 muss vorhanden sein.
pub struct K017 {
    fieldtypes: HashSet<String>
}

impl Kontextregel for K017 {
}
