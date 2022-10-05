pub struct Behandlungsanlass<'a>(&'a str);

impl Behandlungsanlass<'static> {
    pub const KURATIV: Behandlungsanlass<'static> = Behandlungsanlass("Kurativ");
    pub const PRAEVENTIV: Behandlungsanlass<'static> = Behandlungsanlass("Praeventiv");
    pub const EMPFAENGNISREGELUNG_STERILISATION_SCHWANGERSCHAFTSABBRUCH: Behandlungsanlass<'static> = Behandlungsanlass("EmpfaengnisregelungSterilisationSchwangerschaftsabbruch");
    pub const BELEGAERZTLICHE_BEHANDLUNG: Behandlungsanlass<'static> = Behandlungsanlass("BelegaerztlicheBehandlung");
}
