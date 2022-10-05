pub struct ResistenzMethode<'a>(&'a str);

impl ResistenzMethode<'static> {
    pub const KEIN_ANTIBIOGRAMM_ERSTELLT: ResistenzMethode<'static> = ResistenzMethode("KeinAntibiogrammErstellt");
    pub const AGARDIFFUSION: ResistenzMethode<'static> = ResistenzMethode("Agardiffusion");
    pub const AGARDILUTION: ResistenzMethode<'static> = ResistenzMethode("Agardilution");
    pub const PCR_UND_HYBRIDISIERUNG: ResistenzMethode<'static> = ResistenzMethode("PcrUndHybridisierung");
    pub const SONSTIGE: ResistenzMethode<'static> = ResistenzMethode("sonstige");
}
