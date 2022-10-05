pub struct WOP<'a>(&'a str);

impl WOP<'static> {
    pub const DUMMY: WOP<'static> = WOP("Dummy");
    pub const SCHLESWIG_HOLSTEIN: WOP<'static> = WOP("SchleswigHolstein");
    pub const HAMBURG: WOP<'static> = WOP("Hamburg");
    pub const BREMEN: WOP<'static> = WOP("Bremen");
    pub const NIEDERSACHSEN: WOP<'static> = WOP("Niedersachsen");
    pub const WESTFALEN_LIPPE: WOP<'static> = WOP("WestfalenLippe");
    pub const NORDRHEIN: WOP<'static> = WOP("Nordrhein");
    pub const HESSEN: WOP<'static> = WOP("Hessen");
    pub const KOBLENZ: WOP<'static> = WOP("Koblenz");
    pub const RHEINHESSEN: WOP<'static> = WOP("Rheinhessen");
    pub const PFALZ: WOP<'static> = WOP("Pfalz");
    pub const TRIER: WOP<'static> = WOP("Trier");
    pub const RHEINLAND_PFALZ: WOP<'static> = WOP("RheinlandPfalz");
    pub const BADEN_WUERTTEMBERG: WOP<'static> = WOP("BadenWuerttemberg");
    pub const NORDBADEN: WOP<'static> = WOP("Nordbaden");
    pub const SUEDBADEN: WOP<'static> = WOP("Suedbaden");
    pub const NORDWUERTTEMBERG: WOP<'static> = WOP("Nordwuerttemberg");
    pub const SUEDWUERTTEMBERG: WOP<'static> = WOP("Suedwuerttemberg");
    pub const BAYERN: WOP<'static> = WOP("Bayern");
    pub const BERLIN: WOP<'static> = WOP("Berlin");
    pub const SAARLAND: WOP<'static> = WOP("Saarland");
    pub const KBV: WOP<'static> = WOP("KBV");
    pub const MECKLENBURG_VORPOMMERN: WOP<'static> = WOP("MecklenburgVorpommern");
    pub const BRANDENBURG: WOP<'static> = WOP("Brandenburg");
    pub const SACHSEN_ANHALT: WOP<'static> = WOP("SachsenAnhalt");
    pub const THUERINGEN: WOP<'static> = WOP("Thueringen");
    pub const SACHSEN: WOP<'static> = WOP("Sachsen");
}
