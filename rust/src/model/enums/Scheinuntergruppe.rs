pub struct Scheinuntergruppe<'a>(&'a str);

impl Scheinuntergruppe<'static> {
    pub const AUFTRAGSLEISTUNGEN: Scheinuntergruppe<'static> = Scheinuntergruppe("Auftragsleistungen");
    pub const KONSILIARUNTERSUCHUNG: Scheinuntergruppe<'static> = Scheinuntergruppe("Konsiliaruntersuchung");
    pub const MIT_WEITERBEHANDLUNG: Scheinuntergruppe<'static> = Scheinuntergruppe("MitWeiterbehandlung");
    pub const MUSTER10: Scheinuntergruppe<'static> = Scheinuntergruppe("Muster10");
    pub const MUSTER10A: Scheinuntergruppe<'static> = Scheinuntergruppe("Muster10A");
}
