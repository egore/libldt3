#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::regel::RegularExpressionRegel::RegularExpressionRegel;
use regex::Regex;

/// Format Versionsnummer der Datensatzbeschreibung
pub struct F007 {
    pattern: Regex
}
