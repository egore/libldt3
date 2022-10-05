#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::enums::Abrechnungsinfo::Abrechnungsinfo;
use crate::model::objekte::Arztidentifikation::Arztidentifikation;
use crate::model::objekte::Einsenderidentifikation::Einsenderidentifikation;
use crate::model::objekte::Untersuchungsanforderung::Untersuchungsanforderung;
use crate::model::regel::kontext::Kontextregel::Kontextregel;
use crate::model::saetze::Auftrag::Auftrag;

/// FK 0222 muss vorhanden sein, wenn in mindestens einem Obj_0059 (Obj_Untersuchungsanforderung) die FK 7303 mit dem
/// Inhalt 8 vorhanden ist.
pub struct K057 {

}

impl Kontextregel for K057 {
}
