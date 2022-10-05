#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::objekte::Timestamp::Timestamp;

pub struct Koerperkenngroessen_Messwert {
    value: Option<f32>,
    einheit: String,
    timestamp: Timestamp
}

/// In diesem Objekt können Körperkenngrößen zum Patienten (Größe, Gewicht)
/// übertragen werden.
pub struct Koerperkenngroessen {
    #[feld(Value = "3622", Feldart = libldt3.annotations.Feldart.kann)]
    groesse: Koerperkenngroessen_Messwert,
    #[feld(Value = "3623", Feldart = libldt3.annotations.Feldart.kann)]
    gewicht: Koerperkenngroessen_Messwert
}

impl Kontext for Koerperkenngroessen {
}
