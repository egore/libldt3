#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::regel::RegularExpressionRegel::RegularExpressionRegel;
use regex::Regex;

/// Format Gebührennummer
pub struct F009 {
    pattern: Regex
}
