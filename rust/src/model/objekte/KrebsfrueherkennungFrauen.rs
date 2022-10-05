#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::objekte::Fliesstext::Fliesstext;
use crate::model::regel::F002::F002;
use crate::model::regel::F015::F015;
use crate::model::regel::F018::F018;
use datetime::LocalDate;

/// In diesem Objekt wird das Muster 39, Grundlage für die
/// Krebsfrüherkennungsuntersuchung für Frauen, abgebildet.
pub struct KrebsfrueherkennungFrauen {
    #[feld(Value = "7295", Feldart = libldt3.annotations.Feldart.muss)]
    #[regelsatz(Value = libldt3.model.regel.F002.class, Laenge = 8)]
    untersuchungs_tag: LocalDate,
    #[feld(Value = "7296", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 1)]
    wiederholungsuntersuchung: Option<bool>,
    #[feld(Value = "7297", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Value = libldt3.model.regel.F018.class, Laenge = 8)]
    letzte_untersuchung: LocalDate,
    #[feld(Value = "7298", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 60)]
    nr_letzter_zytologischer_befund: String,
    #[feld(Value = "7299", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 6)]
    gruppe_letzter_befund: String,
    #[feld(Value = "7336", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 1)]
    gynaekologische_op_strahlen_oder_chemotherapie: String,
    #[feld(Value = "7337", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(MaxLaenge = 60)]
    art_gynaekologische_op_strahlen_oder_chemotherapie: Vec<String>,
    #[feld(Value = "7338", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Value = libldt3.model.regel.F018.class, Laenge = 8)]
    datum_gynaekologische_op: LocalDate,
    #[feld(Value = "3668", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 2)]
    anzahl_schwangerschaften: Option<i32>,
    #[feld(Value = "8512", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Value = libldt3.model.regel.F018.class, Laenge = 8)]
    erster_tag_letzter_zyklus: LocalDate,
    #[feld(Value = "7339", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 1)]
    graviditaet: Option<bool>,
    #[feld(Value = "7380", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 1)]
    pathologische_gynaekologische_blutungen: Option<bool>,
    #[feld(Value = "7381", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 1)]
    sonstiger_ausfluss: Option<bool>,
    #[feld(Value = "7382", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 1)]
    iup: Option<bool>,
    #[feld(Value = "7383", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 1)]
    ovulationshemmer: Option<bool>,
    #[feld(Value = "7384", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 1)]
    sonstige_hormonanwendung: Option<bool>,
    #[feld(Value = "7385", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 1)]
    vulva_inspektion_auffaellig: Option<bool>,
    #[feld(Value = "7386", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 1)]
    portio_und_vagina_auffaellig: Option<bool>,
    #[feld(Value = "7387", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 1)]
    innere_genitale_auffaellig: Option<bool>,
    #[feld(Value = "7388", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 1)]
    inguinale_lymphknoten_auffaellig: Option<bool>,
    #[feld(Value = "7389", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 1)]
    behandlungsbeduerftige_nebenbefunde: Option<bool>,
    #[feld(Value = "7390", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 1)]
    haut: Option<bool>,
    #[feld(Value = "7391", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 1)]
    mamma_auffaellig: Option<bool>,
    #[feld(Value = "7392", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 1)]
    axillaere_lymphknoten_auffaellig: Option<bool>,
    #[feld(Value = "7393", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 1)]
    rektum_kolon_blut_oder_schleim: Option<bool>,
    #[feld(Value = "7394", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 1)]
    neu_aufgetretene_unregelmaessigkeiten: Option<bool>,
    #[feld(Value = "7395", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 1)]
    rektum_kolon_tastbefund_auffaellig: Option<bool>,
    #[feld(Value = "7396", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 1)]
    stuhltest_zurueckgegeben: Option<bool>,
    #[feld(Value = "7397", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 1)]
    stuhltest_positiv: Option<bool>,
    #[feld(Value = "7423", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(MaxLaenge = 990)]
    gynaekologische_diagnose: Option<bool>,
    #[feld(Value = "7398", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Value = libldt3.model.regel.F015.class, Laenge = 7)]
    rr_blutdruck: String,
    #[feld(Value = "7399", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Value = libldt3.model.regel.F015.class, Laenge = 7)]
    rr_zweite_messung: String,
    #[feld(Value = "8167", Name = "Zusaetzliche_Informationen", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 26)]
    zusaetzliche_informationen: Vec<Fliesstext>
}

impl Kontext for KrebsfrueherkennungFrauen {
}
