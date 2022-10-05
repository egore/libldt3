#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::regel::Regel::Regel;
use regex::Regex;

/// Base class to implement regular expression based rules
pub struct RegularExpressionRegel {
    pattern: Regex
}

impl Regel for RegularExpressionRegel {
}
