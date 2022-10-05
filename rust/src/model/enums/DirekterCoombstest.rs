pub struct DirekterCoombstest<'a>(&'a str);

impl DirekterCoombstest<'static> {
    pub const NEGATIV: DirekterCoombstest<'static> = DirekterCoombstest("negativ");
    pub const EINFACH_POSITIV: DirekterCoombstest<'static> = DirekterCoombstest("einfachPositiv");
    pub const ZWEIFACH_POSITIV: DirekterCoombstest<'static> = DirekterCoombstest("zweifachPositiv");
    pub const DREIFACH_POSITIV: DirekterCoombstest<'static> = DirekterCoombstest("dreifachPositiv");
    pub const VIEWFACH_POSITIV: DirekterCoombstest<'static> = DirekterCoombstest("viewfachPositiv");
}
