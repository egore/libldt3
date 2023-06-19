#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::enums::AbrechnungsartPkv::AbrechnungsartPkv;
use crate::model::enums::Gebuehrenordnung::Gebuehrenordnung;
use crate::model::objekte::Rechnungsempfaenger::Rechnungsempfaenger;

/// Das Objekt bezieht sich auf Patienten, welche bei privaten
/// Krankenkassen versichert sind. Dabei kann der Rechnungsempfänger aber auch
/// ein anderer sein, als der Versicherte.
pub struct AbrechnungPKV {
    #[feld(Value = "7362", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 1)]
    abrechnungsart_pkv: AbrechnungsartPkv<'static>,
    #[feld(Value = "4134", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 28)]
    kostentraeger_name: Vec<String>,
    #[feld(Value = "4121", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 1)]
    gebuehrenordnung: Gebuehrenordnung<'static>,
    #[feld(Value = "4202", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(Laenge = 1)]
    unfall_folgen: Option<bool>,
    #[feld(Value = "8148", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 12)]
    rechnungsempfaenger: Rechnungsempfaenger
}

impl Kontext for AbrechnungPKV {
}
