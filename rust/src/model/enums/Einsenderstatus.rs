pub struct Einsenderstatus<'a>(&'a str);

impl Einsenderstatus<'static> {
    pub const ERSTVERANLASSER: Einsenderstatus<'static> = Einsenderstatus("Erstveranlasser");
    pub const EINSENDER_ARZT: Einsenderstatus<'static> = Einsenderstatus("EinsenderArzt");
    pub const EINSENDER_SONSTIGE: Einsenderstatus<'static> = Einsenderstatus("EinsenderSonstige");
    pub const VERSICHERTER: Einsenderstatus<'static> = Einsenderstatus("Versicherter");
    pub const RECHNUNGSEMPFAENGER: Einsenderstatus<'static> = Einsenderstatus("Rechnungsempfaenger");
    pub const BEVOLLMAECHTIGTER: Einsenderstatus<'static> = Einsenderstatus("Bevollmaechtigter");
    pub const LABORARZT_BEFUNDERSTELLER: Einsenderstatus<'static> = Einsenderstatus("Laborarzt_Befundersteller");
    pub const LEISTUNGSERBRINGER: Einsenderstatus<'static> = Einsenderstatus("Leistungserbringer");
    pub const TIERHALTER: Einsenderstatus<'static> = Einsenderstatus("Tierhalter");
    pub const PATIENT: Einsenderstatus<'static> = Einsenderstatus("Patient");
    pub const UEBERWEISER: Einsenderstatus<'static> = Einsenderstatus("Ueberweiser");
    pub const STAATLICHE_EINRICHTUNG: Einsenderstatus<'static> = Einsenderstatus("staatliche_Einrichtung");
    pub const SONSTIGE_JURISTISCHE_PERSON: Einsenderstatus<'static> = Einsenderstatus("sonstige_juristische_Person");
    pub const SONSTIGE_MEDIZINISCHE_EINRICHTUNG: Einsenderstatus<'static> = Einsenderstatus("sonstige_medizinische_Einrichtung");
}
