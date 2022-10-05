pub struct Untersuchungsanlass<'a>(&'a str);

impl Untersuchungsanlass<'static> {
    pub const VORSORGE: Untersuchungsanlass<'static> = Untersuchungsanlass("Vorsorge");
    pub const VERLAUFSKONTROLLE: Untersuchungsanlass<'static> = Untersuchungsanlass("Verlaufskontrolle");
    pub const ZUSTAND_VOR: Untersuchungsanlass<'static> = Untersuchungsanlass("ZustandVor");
    pub const ZUSTAND_NACH: Untersuchungsanlass<'static> = Untersuchungsanlass("ZustandNach");
    pub const AUSSCHLUSS: Untersuchungsanlass<'static> = Untersuchungsanlass("Ausschluss");
    pub const BESTAETIGUNG: Untersuchungsanlass<'static> = Untersuchungsanlass("Bestaetigung");
    pub const GEZIELTE_SUCHE: Untersuchungsanlass<'static> = Untersuchungsanlass("GezielteSuche");
    pub const UNGEZIELTE_SUCHE: Untersuchungsanlass<'static> = Untersuchungsanlass("UngezielteSuche");
    pub const ERFOLGSKONTROLLE: Untersuchungsanlass<'static> = Untersuchungsanlass("Erfolgskontrolle");
    pub const ABSCHLUSSKONTROLLE: Untersuchungsanlass<'static> = Untersuchungsanlass("Abschlusskontrolle");
    pub const IMMUNITAET_IMPFERFOLG: Untersuchungsanlass<'static> = Untersuchungsanlass("ImmunitaetImpferfolg");
}
