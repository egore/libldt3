pub struct Dokumententyp<'a>(&'a str);

impl Dokumententyp<'static> {
    pub const MUSTER6: Dokumententyp<'static> = Dokumententyp("Muster6");
    pub const MUSTER10: Dokumententyp<'static> = Dokumententyp("Muster10");
    pub const MUSTER10A: Dokumententyp<'static> = Dokumententyp("Muster10A");
    pub const MUSTER39: Dokumententyp<'static> = Dokumententyp("Muster39");
    pub const AUFTRAGSDOKUMENT_PKV_FA: Dokumententyp<'static> = Dokumententyp("AuftragsdokumentPKV_FA");
    pub const AUFTRAGSDOKUMENT_PKV_LG: Dokumententyp<'static> = Dokumententyp("AuftragsdokumentPKV_LG");
    pub const AUFTRAGSDOKUMENT_IGE_L: Dokumententyp<'static> = Dokumententyp("Auftragsdokument_IGeL");
    pub const AUFTRAGSDOKUMENT_SONSTIGE_KOSTENUEBERNAHME: Dokumententyp<'static> = Dokumententyp("Auftragsdokument_Sonstige_Kostenuebernahme");
    pub const AUFTRAGSDOKUMENT_SELEKTIVVERTRAG: Dokumententyp<'static> = Dokumententyp("Auftragsdokument_Selektivvertrag");
    pub const LABORBEFUND: Dokumententyp<'static> = Dokumententyp("Laborbefund");
    pub const MUTTERPASS: Dokumententyp<'static> = Dokumententyp("Mutterpass");
    pub const IMPFPASS: Dokumententyp<'static> = Dokumententyp("Impfpass");
    pub const NOTFALLAUSWEIS: Dokumententyp<'static> = Dokumententyp("Notfallausweis");
    pub const PATIENTENBEFUND: Dokumententyp<'static> = Dokumententyp("Patientenbefund");
    pub const MEDIKATIONSPLAN: Dokumententyp<'static> = Dokumententyp("Medikationsplan");
    pub const VERLAUFSBERICHT: Dokumententyp<'static> = Dokumententyp("Verlaufsbericht");
    pub const BEHANDLUNGSBERICHT: Dokumententyp<'static> = Dokumententyp("Behandlungsbericht");
    pub const EINVERSTAENDNISERKLÄRUNG_LT_GEN_DG_GEN_DIAGNOSTIK_GESETZ: Dokumententyp<'static> = Dokumententyp("Einverstaendniserklärung_lt_GenDG_Gen_Diagnostik_Gesetz");
    pub const WEITERE_LABORSPEZIFISCHE_DOKUMENTE: Dokumententyp<'static> = Dokumententyp("weitere_laborspezifische_Dokumente");
    pub const ALLERGIE_RAST: Dokumententyp<'static> = Dokumententyp("Allergie_RAST");
    pub const MOLEKULARDIAGNOSTIK: Dokumententyp<'static> = Dokumententyp("Molekulardiagnostik");
    pub const ENDOKRINOLOGIE: Dokumententyp<'static> = Dokumententyp("Endokrinologie");
    pub const VIROLOGIE: Dokumententyp<'static> = Dokumententyp("Virologie");
    pub const MIKROBIOLOGIE: Dokumententyp<'static> = Dokumententyp("Mikrobiologie");
    pub const FUNKTIONSDIAGNOSTIK: Dokumententyp<'static> = Dokumententyp("Funktionsdiagnostik");
    pub const INFEKTIONSSEROLOGIE: Dokumententyp<'static> = Dokumententyp("Infektionsserologie");
    pub const KINDERWUNSCH: Dokumententyp<'static> = Dokumententyp("Kinderwunsch");
    pub const MELDUNG_GEMAESS_IF_SG_INFEKTIONSSCHUTZ_GESETZ: Dokumententyp<'static> = Dokumententyp("Meldung_gemaess_IfSG_Infektionsschutz_Gesetz");
    pub const MELDUNG_KREBSREGISTER: Dokumententyp<'static> = Dokumententyp("Meldung_Krebsregister");
    pub const NORMBEREICHSGRAFIK: Dokumententyp<'static> = Dokumententyp("Normbereichsgrafik");
    pub const RECHNUNG: Dokumententyp<'static> = Dokumententyp("Rechnung");
    pub const LDT_DATEN: Dokumententyp<'static> = Dokumententyp("LDT_Daten");
    pub const SONSTIGE: Dokumententyp<'static> = Dokumententyp("sonstige");
}
