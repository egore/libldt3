#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::enums::Scheinuntergruppe::Scheinuntergruppe;
use crate::model::regel::kontext::K076::K076;
use crate::model::regel::kontext::Kontextregel::Kontextregel;
use crate::model::regel::kontext::KontextregelHelper::KontextregelHelper;
use std::collections::HashSet;

/// Wenn Inhalt von FK 4239 = 27 und FK 8240 vorhanden, dann muss eine der
/// folgenden Kombinationen vorhanden sein:
/// - FK 4217 und FK 4241 oder
/// - FK 4225 und FK 4241 oder
/// - FK 4225 und FK 4248.
pub struct K041 {
    fieldtypes: HashSet<String>
}

impl Kontextregel for K041 {
}
