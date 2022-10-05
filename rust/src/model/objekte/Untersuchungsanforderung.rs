#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::enums::Abrechnungsinfo::Abrechnungsinfo;
use crate::model::enums::Dringlichkeit::Dringlichkeit;
use crate::model::enums::KatalogIdAnforderbareLeistungen::KatalogIdAnforderbareLeistungen;
use crate::model::enums::StatusDringlichkeit::StatusDringlichkeit;
use crate::model::objekte::Anhang::Anhang;
use crate::model::objekte::Fliesstext::Fliesstext;
use crate::model::objekte::KrebsfrueherkennungFrauen::KrebsfrueherkennungFrauen;
use crate::model::objekte::Namenskennung::Namenskennung;
use crate::model::objekte::Timestamp::Timestamp;
use crate::model::objekte::Tumor::Tumor;
use crate::model::regel::kontext::K003::K003;
use crate::model::regel::kontext::Kontextregel::Kontextregel;

pub struct Untersuchungsanforderung_KatalogReferenz {
    value: KatalogIdAnforderbareLeistungen<'static>,
    katalog_url: String,
    katalog_bezeichnung: String,
    analysen_id: String,
    leistungsbezeichnung: String
}

pub struct Untersuchungsanforderung_Test {
    value: String,
    testbezeichnung: String
}

pub struct Untersuchungsanforderung_ProbengefaessIdent {
    value: String,
    probenmaterial_ident: String,
    probenmaterial_index: String
}

pub struct Untersuchungsanforderung_Einwilligungserklaerung {
    value: Option<bool>,
    anhang: Anhang
}

/// In diesem Objekt werden alle Informationen zur Untersuchungsanforderung
/// zusammengefasst.
pub struct Untersuchungsanforderung {
    #[feld(Value = "7260", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(Laenge = 1)]
    anforderbare_leistungen_katalog_id: Untersuchungsanforderung_KatalogReferenz,
    #[feld(Value = "7276", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    nummernpool_id: String,
    #[feld(Value = "8410", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(MaxLaenge = 60)]
    test_ident: Untersuchungsanforderung_Test,
    #[feld(Value = "7303", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(MaxLaenge = 2)]
    abrechnungsinfo: Abrechnungsinfo<'static>,
    #[feld(Value = "8501", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 1)]
    dringlichkeit: Dringlichkeit<'static>,
    #[feld(Value = "7262", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(Laenge = 1)]
    status_dringlichkeit: StatusDringlichkeit<'static>,
    #[feld(Value = "8423", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 1)]
    pathologisch_bekannt: Option<bool>,
    #[feld(Value = "7364", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(MaxLaenge = 60)]
    probengefaess_ident: Vec<Untersuchungsanforderung_ProbengefaessIdent>,
    #[feld(Value = "8434", Feldart = libldt3.annotations.Feldart.bedingt_muss)]
    #[regelsatz(MaxLaenge = 60)]
    anforderungen: String,
    #[feld(Value = "8134", Name = "Krebsfrueherkennung_Frauen", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 26)]
    krebsfrueherkennung_frauen: KrebsfrueherkennungFrauen,
    #[feld(Value = "8156", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 5)]
    tumor: Tumor,
    #[feld(Value = "8110", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 6)]
    anhang: Vec<Anhang>,
    #[feld(Value = "8167", Name = "Zusaetzliche_Informationen", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 26)]
    zusaetzliche_informationen: Vec<Fliesstext>,
    #[feld(Value = "8238", Name = "Auftragsbezogene_Hinweise", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 25)]
    auftragsbezogene_hinweise: Fliesstext,
    #[feld(Value = "8491", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 1)]
    einwilligungserklaerung_liegt_vor: Untersuchungsanforderung_Einwilligungserklaerung,
    #[feld(Value = "8213", Name = "Timestamp_Erstellung_Untersuchungsanforderung", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Laenge = 45)]
    timestamp_erstellung_untersuchungsanforderung: Timestamp,
    #[feld(Value = "8141", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 13)]
    namenskennung: Namenskennung
}

impl Kontext for Untersuchungsanforderung {
}
