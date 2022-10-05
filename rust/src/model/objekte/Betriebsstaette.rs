#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::enums::Betriebsstaettenstatus::Betriebsstaettenstatus;
use crate::model::objekte::Organisation::Organisation;
use crate::model::regel::F010::F010;
use crate::model::regel::F021::F021;
use crate::model::regel::Regel::Regel;

/// Dieses Objekt fasst die notwendigen Informationen zur Betriebsstätte von
/// medizinischen Einrichtungen zusammen.
pub struct Betriebsstaette {
    #[feld(Value = "0204", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 1)]
    status: Vec<Betriebsstaettenstatus<'static>>,
    #[feld(Value = "0203", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(MaxLaenge = 60)]
    bsnr_bezeichnung: String,
    #[feld(Value = "0200", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(MaxLaenge = 60)]
    betriebsstaetten_id: String,
    #[feld(Value = "0201", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Value = { libldt3.model.regel.F010.class, libldt3.model.regel.F021.class }, Laenge = 9)]
    bsnr: String,
    #[feld(Value = "0213", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 9)]
    institutskennzeichen: String,
    #[feld(Value = "8143", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 12)]
    organisation: Organisation
}

impl Kontext for Betriebsstaette {
}
