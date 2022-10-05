#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::regel::kontext::Kontextregel::Kontextregel;
use crate::model::regel::kontext::KontextregelHelper::KontextregelHelper;
use std::collections::HashSet;

/// Wenn FK 8428 oder FK 8430 oder FK 8429 vorhanden ist, darf FK 8431 vorhanden sein.
pub struct K006 {
    fieldtypes: HashSet<String>
}

impl Kontextregel for K006 {
}
