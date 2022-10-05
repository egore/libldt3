#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::regel::RegelConstants::RegelConstants;
use crate::model::regel::RegularExpressionRegel::RegularExpressionRegel;
use regex::Regex;

/// Format BSNR Terminservicestelle (Anlage 28 BMV-Ä)
pub struct F021 {
    pattern: Regex
}
