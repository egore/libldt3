pub struct StatusPerson<'a>(&'a str);

impl StatusPerson<'static> {
    pub const ERSTVERANLASSER: StatusPerson<'static> = StatusPerson("Erstveranlasser");
    pub const EINSENDER_ARZT: StatusPerson<'static> = StatusPerson("EinsenderArzt");
    pub const EINSENDER_SONSTIGE: StatusPerson<'static> = StatusPerson("EinsenderSonstige");
    pub const VERSICHERTER: StatusPerson<'static> = StatusPerson("Versicherter");
    pub const RECHNUNGSEMPFAENGER: StatusPerson<'static> = StatusPerson("Rechnungsempfaenger");
    pub const BEVOLLMAECHTIGTER: StatusPerson<'static> = StatusPerson("Bevollmaechtigter");
    pub const LABORARZT_BEFUNDERSTELLER: StatusPerson<'static> = StatusPerson("Laborarzt_Befundersteller");
    pub const LEISTUNGSERBRINGER: StatusPerson<'static> = StatusPerson("Leistungserbringer");
    pub const SOFTWAREVERANTWORTLICHER: StatusPerson<'static> = StatusPerson("Softwareverantwortlicher");
    pub const ZUSAETZLICHER_BEFUNDEMPFAENGER: StatusPerson<'static> = StatusPerson("ZusaetzlicherBefundempfaenger");
    pub const TIERHALTER: StatusPerson<'static> = StatusPerson("Tierhalter");
    pub const PATIENT: StatusPerson<'static> = StatusPerson("Patient");
    pub const UEBERWEISER: StatusPerson<'static> = StatusPerson("Ueberweiser");
    pub const SONSTIGE_JURISTISCHE_PERSON: StatusPerson<'static> = StatusPerson("sonstigeJuristischePerson");
    pub const MTA: StatusPerson<'static> = StatusPerson("MTA");
    pub const MFA: StatusPerson<'static> = StatusPerson("MFA");
}
