#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::enums::Behandlungsanlass::Behandlungsanlass;
use crate::model::enums::BesonderePersonengruppe::BesonderePersonengruppe;
use crate::model::enums::DmpKennzeichnung::DmpKennzeichnung;
use crate::model::enums::KostentraegerAbrechnungsbereich::KostentraegerAbrechnungsbereich;
use crate::model::enums::Scheinuntergruppe::Scheinuntergruppe;
use crate::model::enums::Versichertenart::Versichertenart;
use crate::model::enums::WOP::WOP;
use crate::model::regel::F001::F001;
use crate::model::regel::F002::F002;
use crate::model::regel::F010::F010;
use crate::model::regel::F011::F011;
use crate::model::regel::F022::F022;
use crate::model::regel::Regel::Regel;
use crate::model::regel::kontext::K041::K041;
use crate::model::regel::kontext::Kontextregel::Kontextregel;
use datetime::LocalDate;

/// Der Patient ist in der gesetzlichen
/// Kranken-versicherung pflichtversichert oder freiwillig versichert. Der
/// Auftrag für die geplanten Untersuchungen erfolgt über Muster 10/Muster
/// 10A/Muster 39.
/// </p>
/// <p>
/// Mit diesem Objekt werden die Informationen für die Abrechnung von
/// Untersuchungsanforderungen zusammengefasst, die im Regelleistungskatalog der
/// Krankenkassen vorhanden sind oder anderweitig z.B. über eDMP dem Patienten
/// zugeordnet werden können.
/// </p>
pub struct AbrechnungGkv {
    #[feld(Value = "4239", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 2)]
    scheinuntergruppe: Scheinuntergruppe<'static>,
    #[feld(Value = "4134", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(MaxLaenge = 28)]
    kostentraegername: String,
    #[feld(Value = "4104", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Value = libldt3.model.regel.F001.class, Laenge = 5)]
    abrechnungs_vknr: String,
    #[feld(Value = "4106", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 2)]
    kostentraeger_abrechnungsbereich: KostentraegerAbrechnungsbereich<'static>,
    #[feld(Value = "4108", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    zulassungsnummer: String,
    #[feld(Value = "3116", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 2)]
    wop: WOP<'static>,
    #[feld(Value = "3108", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 1)]
    versichertenart: Versichertenart<'static>,
    #[feld(Value = "4109", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Value = libldt3.model.regel.F002.class, Laenge = 8)]
    letzter_einlesetag_versichertenkarte_im_quartal: LocalDate,
    #[feld(Value = "4133", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Value = libldt3.model.regel.F002.class, Laenge = 8)]
    versicherungsschutz_beginn: LocalDate,
    #[feld(Value = "4110", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Value = libldt3.model.regel.F002.class, Laenge = 8)]
    versicherungsschutz_ende: LocalDate,
    #[feld(Value = "4111", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 9)]
    kstentraegerkennung: String,
    #[feld(Value = "4229", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(Laenge = 5)]
    ausnahmeindikation: Vec<String>,
    #[feld(Value = "4122", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 2)]
    abrechnungsgebiet: String,
    #[feld(Value = "4124", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MinLaenge = 5, MaxLaenge = 60)]
    skt_zusatzangaben: String,
    #[feld(Value = "4126", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    skt_zusatzbemerkungen: Vec<String>,
    #[feld(Value = "4131", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 2)]
    besondere_personengruppe: BesonderePersonengruppe<'static>,
    #[feld(Value = "4132", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 2)]
    dmp_kennzeichnung: DmpKennzeichnung<'static>,
    #[feld(Value = "4202", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 1)]
    unfallfolgen: Option<bool>,
    #[feld(Value = "4204", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 1)]
    eingeschraenkter_leistungsanspruch: Option<bool>,
    #[feld(Value = "4221", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 1)]
    kurativ_praeventiv_ess: Behandlungsanlass<'static>,
    #[feld(Value = "4231", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 1)]
    kontrolluntersuchung_bekannter_infektion: String,
    #[feld(Value = "4241", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Value = { libldt3.model.regel.F011.class, libldt3.model.regel.F022.class }, Laenge = 9)]
    lebenslange_arztnummer: String,
    #[feld(Value = "4248", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 9)]
    pseudo_lanr: String,
    #[feld(Value = "4217", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Value = libldt3.model.regel.F010.class, Laenge = 9)]
    bsnr_erstveranlasser: String,
    #[feld(Value = "4225", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 9)]
    asv_teamnummer: String
}

impl Kontext for AbrechnungGkv {
}
