#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::regel::F020::F020;

/// Hier werden Informationen zu Wirkstoffen zusammengefasst.
pub struct Wirkstoff {
    #[feld(Value = "6212", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(MaxLaenge = 60)]
    arzneimittelwirkstoff: String,
    #[feld(Value = "6206", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Value = libldt3.model.regel.F020.class, Laenge = 8)]
    pzn: String,
    #[feld(Value = "6224", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(MaxLaenge = 60)]
    wirkstoff_code: String,
    #[feld(Value = "6214", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(MaxLaenge = 60)]
    wirkstoff_klassifikation: String,
    #[feld(Value = "8523", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(MaxLaenge = 60)]
    wirkstoffmenge: String,
    #[feld(Value = "8421", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(MaxLaenge = 20)]
    mengeneinheit: String
}

impl Kontext for Wirkstoff {
}
