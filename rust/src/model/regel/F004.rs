#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::regel::RegularExpressionRegel::RegularExpressionRegel;
use regex::Regex;

/// Format ICD-Code
pub struct F004 {
    pattern: Regex
}
