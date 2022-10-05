pub struct Ergebnis<'a>(&'a str);

impl Ergebnis<'static> {
    pub const POSITIV: Ergebnis<'static> = Ergebnis("positiv");
    pub const NEGATIV: Ergebnis<'static> = Ergebnis("negativ");
    pub const INVALID: Ergebnis<'static> = Ergebnis("invalid");
}
