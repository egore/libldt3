pub struct KatalogIdAnforderbareLeistungen<'a>(&'a str);

impl KatalogIdAnforderbareLeistungen<'static> {
    pub const LOINC: KatalogIdAnforderbareLeistungen<'static> = KatalogIdAnforderbareLeistungen("LOINC");
    pub const LDT_ELV: KatalogIdAnforderbareLeistungen<'static> = KatalogIdAnforderbareLeistungen("LDT_ELV");
    pub const LVZ_SONSTIGE: KatalogIdAnforderbareLeistungen<'static> = KatalogIdAnforderbareLeistungen("LVZ_sonstige");
    pub const SONSTIGE_MIT_URL: KatalogIdAnforderbareLeistungen<'static> = KatalogIdAnforderbareLeistungen("sonstigeMitURL");
}
