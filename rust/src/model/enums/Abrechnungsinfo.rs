pub struct Abrechnungsinfo<'a>(&'a str);

impl Abrechnungsinfo<'static> {
    pub const GKV_LABORFACHARZT: Abrechnungsinfo<'static> = Abrechnungsinfo("GkvLaborfacharzt");
    pub const GKV_LG: Abrechnungsinfo<'static> = Abrechnungsinfo("GkvLg");
    pub const PKV_LABORFACHARZT: Abrechnungsinfo<'static> = Abrechnungsinfo("PkvLaborfacharzt");
    pub const PKV_LG: Abrechnungsinfo<'static> = Abrechnungsinfo("PkvLg");
    pub const SELEKTIVVERTRAG: Abrechnungsinfo<'static> = Abrechnungsinfo("Selektivvertrag");
    pub const IGE_L: Abrechnungsinfo<'static> = Abrechnungsinfo("IGeL");
    pub const SONSTIGE_KOSTENUEBERNAHME: Abrechnungsinfo<'static> = Abrechnungsinfo("Sonstige_Kostenuebernahme");
    pub const ASV: Abrechnungsinfo<'static> = Abrechnungsinfo("Asv");
    pub const GKV_LABORFACHARZT_PRAEVENTIV: Abrechnungsinfo<'static> = Abrechnungsinfo("GkvLaborfacharztPraeventiv");
    pub const GKG_LG_PRAEVENTIV: Abrechnungsinfo<'static> = Abrechnungsinfo("GkgLgPraeventiv");
    pub const KEINE_ZUORDNUNG: Abrechnungsinfo<'static> = Abrechnungsinfo("keine_Zuordnung");
    pub const STORNIERT: Abrechnungsinfo<'static> = Abrechnungsinfo("storniert");
}
