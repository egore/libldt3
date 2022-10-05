#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::enums::Dokumententyp::Dokumententyp;
use crate::model::regel::kontext::Kontextregel::Kontextregel;
use crate::model::regel::kontext::KontextregelHelper::KontextregelHelper;
use std::collections::HashSet;

pub struct K075 {
    fieldtypes: HashSet<String>
}

impl Kontextregel for K075 {
}
