#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::objekte::AbrechnungGKV::AbrechnungGKV;
use crate::model::objekte::AbrechnungIgel::AbrechnungIgel;
use crate::model::objekte::AbrechnungPKV::AbrechnungPKV;
use crate::model::objekte::AbrechnungSelektivvertrag::AbrechnungSelektivvertrag;
use crate::model::objekte::AbrechnungSonstigeKostenuebernahme::AbrechnungSonstigeKostenuebernahme;

/// An Hand der hier gemachten Angaben kann bei der
/// Implementierung eine Prüfroutine hinsichtlich der Vollständigkeit der
/// darunterliegenden Objekte eingeführt werden. Pro Satzart "8215" darf dieses
/// Objekt nur einmal vorhanden sein.
pub struct Abrechnungsinformation {
    #[feld(Value = "8102", Name = "Abrechnung_GKV", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 14)]
    abrechnung_gkv: Vec<AbrechnungGKV>,
    #[feld(Value = "8103", Name = "Abrechnung_PKV", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 14)]
    abrechnung_pkv: Vec<AbrechnungPKV>,
    #[feld(Value = "8104", Name = "Abrechnung_IGe-Leistungen", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 25/* XXX 15 according to spec 3.0.3 */
)]
    abrechnung_igel: AbrechnungIgel,
    #[feld(Value = "8105", Name = "Abrechnung_Sonstige_Kostenuebernahme", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 36)]
    abrechnung_sonstige_kostenuebernahme: AbrechnungSonstigeKostenuebernahme,
    #[feld(Value = "8106", Name = "Abrechnung_Selektivvertrag", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 26)]
    abrechnung_selektivvertrag: AbrechnungSelektivvertrag
}

impl Kontext for Abrechnungsinformation {
}
