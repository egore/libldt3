#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::enums::Laborart::Laborart;
use crate::model::objekte::Organisation::Organisation;

/// Das Objekt enthält die Angaben zu dem Labor, welches den Auftrag ausgeführt
/// hat.
pub struct Laborkennung {
    #[feld(Value = "8239", Name = "Laborbezeichnung", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 16)]
    laborbezeichnung: Organisation,
    #[feld(Value = "7352", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    katalog_url: Vec<String>,
    #[feld(Value = "8324", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    labor_standort_id: String,
    #[feld(Value = "7266", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 1)]
    laborart: Laborart<'static>
}

impl Kontext for Laborkennung {
}
