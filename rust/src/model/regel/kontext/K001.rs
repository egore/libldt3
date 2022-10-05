#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::regel::kontext::Kontextregel::Kontextregel;
use crate::model::regel::kontext::KontextregelHelper::KontextregelHelper;
use std::collections::HashSet;

/// Entweder FK 6305 oder FK 8242 ist vorhanden.
pub struct K001 {
    fieldtypes: HashSet<String>
}

impl Kontextregel for K001 {
}
