pub struct KostentraegerAbrechnungsbereich<'a>(&'a str);

impl KostentraegerAbrechnungsbereich<'static> {
    pub const PRIMAERABRECHNUNG: KostentraegerAbrechnungsbereich<'static> = KostentraegerAbrechnungsbereich("Primaerabrechnung");
    pub const SOZIALVERSICHERUNGSABKOMMEN: KostentraegerAbrechnungsbereich<'static> = KostentraegerAbrechnungsbereich("Sozialversicherungsabkommen");
    pub const BUNDESVERSORGUNGSGESETZ: KostentraegerAbrechnungsbereich<'static> = KostentraegerAbrechnungsbereich("Bundesversorgungsgesetz");
    pub const BUNDESENTSCHAEDIGUNGSGESETZ: KostentraegerAbrechnungsbereich<'static> = KostentraegerAbrechnungsbereich("Bundesentschaedigungsgesetz");
    pub const GRENZGAENGER: KostentraegerAbrechnungsbereich<'static> = KostentraegerAbrechnungsbereich("Grenzgaenger");
    pub const RHEINSCHIFFER: KostentraegerAbrechnungsbereich<'static> = KostentraegerAbrechnungsbereich("Rheinschiffer");
    pub const SOZIALHILFETRAEGER: KostentraegerAbrechnungsbereich<'static> = KostentraegerAbrechnungsbereich("Sozialhilfetraeger");
    pub const BUNDESVERTRIEBENENGESETZ: KostentraegerAbrechnungsbereich<'static> = KostentraegerAbrechnungsbereich("Bundesvertriebenengesetz");
    pub const ASYLSTELLEN: KostentraegerAbrechnungsbereich<'static> = KostentraegerAbrechnungsbereich("Asylstellen");
    pub const SCHWANGERSCHAFTSABBRUECHE: KostentraegerAbrechnungsbereich<'static> = KostentraegerAbrechnungsbereich("Schwangerschaftsabbrueche");
}
