pub struct BesonderePersonengruppe<'a>(&'a str);

impl BesonderePersonengruppe<'static> {
    pub const KEINE_ANGABE: BesonderePersonengruppe<'static> = BesonderePersonengruppe("keine_Angabe");
    pub const BUNDESSOZIALHILFEGESETZ: BesonderePersonengruppe<'static> = BesonderePersonengruppe("Bundessozialhilfegesetz");
    pub const BVG: BesonderePersonengruppe<'static> = BesonderePersonengruppe("BVG");
    pub const SVAAUFWAND: BesonderePersonengruppe<'static> = BesonderePersonengruppe("SVAAufwand");
    pub const SVAPAUSCHAL: BesonderePersonengruppe<'static> = BesonderePersonengruppe("SVAPauschal");
    pub const ASYLB_LG: BesonderePersonengruppe<'static> = BesonderePersonengruppe("AsylbLG");
}
