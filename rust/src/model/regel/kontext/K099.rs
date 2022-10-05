#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::enums::Grenzwertindikator::Grenzwertindikator;
use crate::model::enums::GrenzwertindikatorErweitert::GrenzwertindikatorErweitert;
use crate::model::regel::kontext::Kontextregel::Kontextregel;
use crate::model::regel::kontext::KontextregelHelper::KontextregelHelper;
use std::collections::HashSet;

pub struct K099 {
    fieldtypes: HashSet<String>
}

impl Kontextregel for K099 {
}
