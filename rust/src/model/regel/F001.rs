#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::regel::RegularExpressionRegel::RegularExpressionRegel;
use regex::Regex;

/// Format der Abrechnungs-VKNR
pub struct F001 {
    pattern: Regex
}
