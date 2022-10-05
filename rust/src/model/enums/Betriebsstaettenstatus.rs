pub struct Betriebsstaettenstatus<'a>(&'a str);

impl Betriebsstaettenstatus<'static> {
    pub const ARZTPRAXIS: Betriebsstaettenstatus<'static> = Betriebsstaettenstatus("Arztpraxis");
    pub const LABORARZTPRAXIS: Betriebsstaettenstatus<'static> = Betriebsstaettenstatus("Laborarztpraxis");
    pub const LABORGEMEINSCHAFT: Betriebsstaettenstatus<'static> = Betriebsstaettenstatus("Laborgemeinschaft");
    pub const SONSTIGE_MEDIZINISCHE_EINRICHTUNG: Betriebsstaettenstatus<'static> = Betriebsstaettenstatus("sonstigeMedizinischeEinrichtung");
    pub const HAUPTBETRIEBSSTAETTE: Betriebsstaettenstatus<'static> = Betriebsstaettenstatus("Hauptbetriebsstaette");
    pub const NEBENBETRIEBSSTAETTE: Betriebsstaettenstatus<'static> = Betriebsstaettenstatus("Nebenbetriebsstaette");
}
