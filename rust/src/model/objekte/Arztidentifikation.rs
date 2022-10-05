#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::enums::ArztTypId::ArztTypId;
use crate::model::objekte::Person::Person;
use crate::model::regel::F011::F011;
use crate::model::regel::F014::F014;
use crate::model::regel::F022::F022;
use crate::model::regel::Regel::Regel;

pub struct Arztidentifikation_ArztId {
    value: String,
    arzt_typ_id: ArztTypId<'static>
}

/// Hier werden alle notwendigen Informationen zum Einsender zusammengefasst.
pub struct Arztidentifikation {
    #[feld(Value = "8147", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 6)]
    person: Person,
    #[feld(Value = "0212", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Value = { libldt3.model.regel.F011.class, libldt3.model.regel.F022.class }, Laenge = 9)]
    lanr: Vec<String>,
    #[feld(Value = "0223", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Value = { libldt3.model.regel.F011.class, libldt3.model.regel.F022.class }, Laenge = 9)]
    pseudo_lanr: Vec<String>,
    #[feld(Value = "0306", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    vertrags_id: String,
    #[feld(Value = "0307", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    arzt_ids: Vec<Arztidentifikation_ArztId>,
    #[feld(Value = "0222", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Value = libldt3.model.regel.F014.class, Laenge = 9)]
    asv_teamnummer: String
}

impl Kontext for Arztidentifikation {
}
