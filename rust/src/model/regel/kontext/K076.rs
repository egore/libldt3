#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::regel::kontext::Kontextregel::Kontextregel;

/// Wenn Inhalt von FK 8418 ≠ M, F oder S ist, dann muss FK 8225 mindestens einmal vorkommen.
pub struct K076 {

}

impl Kontextregel for K076 {
}
