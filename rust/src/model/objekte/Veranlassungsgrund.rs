#![allow(dead_code, unused_imports, non_camel_case_types)]

use crate::model::Kontext::Kontext;
use crate::model::enums::Abrechnungsinfo::Abrechnungsinfo;
use crate::model::enums::SpezifizierungVeranlassungsgrund::SpezifizierungVeranlassungsgrund;
use crate::model::enums::Untersuchungsanlass::Untersuchungsanlass;
use crate::model::objekte::Anhang::Anhang;
use crate::model::objekte::Diagnose::Diagnose;
use crate::model::objekte::Fliesstext::Fliesstext;

pub struct Veranlassungsgrund_AbrechnungsinfoErweitert {
    value: Abrechnungsinfo<'static>,
    anlass: Untersuchungsanlass<'static>,
    spezifizierung: SpezifizierungVeranlassungsgrund<'static>,
    praezisierung: Fliesstext,
    akut_diagnose: Vec<Diagnose>,
    vorbefund_medikation: Vec<Veranlassungsgrund_Medikation>
}

pub struct Veranlassungsgrund_Medikation {
    value: String,
    arzneimittelwirkstoff: Vec<Veranlassungsgrund_Arzneimittelwirkstoff>
}

pub struct Veranlassungsgrund_Arzneimittelwirkstoff {
    value: String,
    wirkstoff_klassifikation: String
}

/// Mit diesem Objekt können Angaben zum Grund der Veranlassung der
/// laboratoriumsmedizinischen Untersuchung übertragen werden.
pub struct Veranlassungsgrund {
    #[feld(Value = "7303", Feldart = libldt3.annotations.Feldart.bedingt_kann)]
    #[regelsatz(MaxLaenge = 2)]
    abrechnungsinfo: Vec<Veranlassungsgrund_AbrechnungsinfoErweitert>,
    #[feld(Value = "8110", Feldart = libldt3.annotations.Feldart.kann)]
    #[regelsatz(Laenge = 6)]
    anhang: Vec<Anhang>
}

impl Kontext for Veranlassungsgrund {
}
