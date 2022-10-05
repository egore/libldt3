pub struct AbrechnungsartPkv<'a>(&'a str);

impl AbrechnungsartPkv<'static> {
    pub const ABRECHNUNG_LABORFACHARZT: AbrechnungsartPkv<'static> = AbrechnungsartPkv("AbrechnungLaborfacharzt");
    pub const ABRECHNUNG_PRIVAT_LG: AbrechnungsartPkv<'static> = AbrechnungsartPkv("AbrechnungPrivatLG");
}
