pub struct Geschlecht<'a>(&'a str);

impl Geschlecht<'static> {
    pub const MAENNLICH: Geschlecht<'static> = Geschlecht("maennlich");
    pub const WEIBLICH: Geschlecht<'static> = Geschlecht("weiblich");
    pub const UNBESTIMMT: Geschlecht<'static> = Geschlecht("unbestimmt");
    pub const UNBEKANNT: Geschlecht<'static> = Geschlecht("unbekannt");
}
