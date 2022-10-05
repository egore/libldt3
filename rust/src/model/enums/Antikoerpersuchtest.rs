pub struct Antikoerpersuchtest<'a>(&'a str);

impl Antikoerpersuchtest<'static> {
    pub const POSITIV: Antikoerpersuchtest<'static> = Antikoerpersuchtest("positiv");
    pub const NEGATIV: Antikoerpersuchtest<'static> = Antikoerpersuchtest("negativ");
    pub const UNSPEZIFISCH: Antikoerpersuchtest<'static> = Antikoerpersuchtest("unspezifisch");
    pub const IN_ABKLAERUNG: Antikoerpersuchtest<'static> = Antikoerpersuchtest("InAbklaerung");
    pub const ABKLAERUNG_EMPFOHLEN: Antikoerpersuchtest<'static> = Antikoerpersuchtest("AbklaerungEmpfohlen");
}
