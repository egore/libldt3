#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::enums::EinheitMesswert::EinheitMesswert;
use crate::model::regel::kontext::Kontextregel::Kontextregel;
use crate::model::regel::kontext::KontextregelHelper::KontextregelHelper;
use std::collections::HashSet;

/// Wenn zu einem Ergebniswert keine Maßeinheit angegeben wird, muss
/// angegeben werden, dass es sich bei dem Ergebniswert um eine sogenannte
/// "dimensionslose Größe" handelt.
pub struct K002 {
    fieldtypes: HashSet<String>
}

impl Kontextregel for K002 {
}
