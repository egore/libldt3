pub struct Zeiteinheit<'a>(&'a str);

impl Zeiteinheit<'static> {
    pub const SEKUNDEN: Zeiteinheit<'static> = Zeiteinheit("Sekunden");
    pub const MINUTEN: Zeiteinheit<'static> = Zeiteinheit("Minuten");
    pub const TAGE: Zeiteinheit<'static> = Zeiteinheit("Tage");
    pub const JAHRE: Zeiteinheit<'static> = Zeiteinheit("Jahre");
}
