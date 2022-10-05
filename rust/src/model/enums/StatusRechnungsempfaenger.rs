pub struct StatusRechnungsempfaenger<'a>(&'a str);

impl StatusRechnungsempfaenger<'static> {
    pub const EINSENDER_ARZT: StatusRechnungsempfaenger<'static> = StatusRechnungsempfaenger("EinsenderArzt");
    pub const EINSENDER_SONSTIGE: StatusRechnungsempfaenger<'static> = StatusRechnungsempfaenger("EinsenderSonstige");
    pub const VERSICHERTER: StatusRechnungsempfaenger<'static> = StatusRechnungsempfaenger("Versicherter");
    pub const RECHNUNGSEMPFAENGER: StatusRechnungsempfaenger<'static> = StatusRechnungsempfaenger("Rechnungsempfaenger");
    pub const BEVOLLMAECHTIGTER: StatusRechnungsempfaenger<'static> = StatusRechnungsempfaenger("Bevollmaechtigter");
    pub const TIERHALTER: StatusRechnungsempfaenger<'static> = StatusRechnungsempfaenger("Tierhalter");
    pub const PATIENT: StatusRechnungsempfaenger<'static> = StatusRechnungsempfaenger("Patient");
    pub const STAATLICHE_EINRICHTUNG: StatusRechnungsempfaenger<'static> = StatusRechnungsempfaenger("StaatlicheEinrichtung");
    pub const SONSTIGE_JURISTISCHE_PERSON: StatusRechnungsempfaenger<'static> = StatusRechnungsempfaenger("SonstigeJuristischePerson");
    pub const SONSTIGE_MEDIZINISCHE_EINRICHTUNG: StatusRechnungsempfaenger<'static> = StatusRechnungsempfaenger("SonstigeMedizinischeEinrichtung");
}
