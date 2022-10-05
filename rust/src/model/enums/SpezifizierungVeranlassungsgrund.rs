pub struct SpezifizierungVeranlassungsgrund<'a>(&'a str);

impl SpezifizierungVeranlassungsgrund<'static> {
    pub const EINGRIFF: SpezifizierungVeranlassungsgrund<'static> = SpezifizierungVeranlassungsgrund("Eingriff");
    pub const MEDIKAMENTENGABE: SpezifizierungVeranlassungsgrund<'static> = SpezifizierungVeranlassungsgrund("Medikamentengabe");
    pub const UNKLARES_FIEBER: SpezifizierungVeranlassungsgrund<'static> = SpezifizierungVeranlassungsgrund("UnklaresFieber");
    pub const INFEKTION: SpezifizierungVeranlassungsgrund<'static> = SpezifizierungVeranlassungsgrund("Infektion");
    pub const RHEUMA: SpezifizierungVeranlassungsgrund<'static> = SpezifizierungVeranlassungsgrund("Rheuma");
    pub const ALLERGIE: SpezifizierungVeranlassungsgrund<'static> = SpezifizierungVeranlassungsgrund("Allergie");
    pub const HERZ_KREISLAUF: SpezifizierungVeranlassungsgrund<'static> = SpezifizierungVeranlassungsgrund("HerzKreislauf");
    pub const TUMOR: SpezifizierungVeranlassungsgrund<'static> = SpezifizierungVeranlassungsgrund("Tumor");
    pub const IMPFUNGEN: SpezifizierungVeranlassungsgrund<'static> = SpezifizierungVeranlassungsgrund("Impfungen");
    pub const REISEN: SpezifizierungVeranlassungsgrund<'static> = SpezifizierungVeranlassungsgrund("Reisen");
    pub const IMMUNITAET_NACH_INFEKTION: SpezifizierungVeranlassungsgrund<'static> = SpezifizierungVeranlassungsgrund("ImmunitaetNachInfektion");
    pub const SONSTIGES: SpezifizierungVeranlassungsgrund<'static> = SpezifizierungVeranlassungsgrund("Sonstiges");
}
