pub struct Gebuehrenordnung<'a>(&'a str);

impl Gebuehrenordnung<'static> {
    pub const BMAE: Gebuehrenordnung<'static> = Gebuehrenordnung("BMAE");
    pub const EGO: Gebuehrenordnung<'static> = Gebuehrenordnung("EGO");
    pub const GOAE: Gebuehrenordnung<'static> = Gebuehrenordnung("GOAE");
    pub const BG_TARIF: Gebuehrenordnung<'static> = Gebuehrenordnung("BG_Tarif");
}
