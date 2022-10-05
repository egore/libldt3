pub struct ArztTypId<'a>(&'a str);

impl ArztTypId<'static> {
    pub const IK: ArztTypId<'static> = ArztTypId("IK");
    pub const TELEMATIK_ID: ArztTypId<'static> = ArztTypId("TelematikId");
    pub const GEVK_ID: ArztTypId<'static> = ArztTypId("GevkId");
    pub const HAEVG_ID: ArztTypId<'static> = ArztTypId("HaevgId");
    pub const MEDI_ID: ArztTypId<'static> = ArztTypId("MediId");
    pub const SELEKTIVVERTRAG: ArztTypId<'static> = ArztTypId("Selektivvertrag");
    pub const SONSTIGE: ArztTypId<'static> = ArztTypId("Sonstige");
}
