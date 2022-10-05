pub struct Ergebnis2<'a>(&'a str);

impl Ergebnis2<'static> {
    pub const POSITIV: Ergebnis2<'static> = Ergebnis2("positiv");
    pub const NEGATIV: Ergebnis2<'static> = Ergebnis2("negativ");
    pub const NICHT_AUSWERTBAR: Ergebnis2<'static> = Ergebnis2("nicht_auswertbar");
    pub const SUSPEKT: Ergebnis2<'static> = Ergebnis2("suspekt");
}
