#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::regel::RegularExpressionRegel::RegularExpressionRegel;
use regex::Regex;

/// Dummy-LANR für Krankenhausärzte
pub struct F022 {
    pattern: Regex
}
