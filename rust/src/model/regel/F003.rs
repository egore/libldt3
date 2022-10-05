#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::regel::RegularExpressionRegel::RegularExpressionRegel;
use regex::Regex;

/// Format Geburtsdatum eines Patienten
pub struct F003 {
    pattern: Regex
}
