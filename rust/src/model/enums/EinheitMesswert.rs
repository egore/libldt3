pub struct EinheitMesswert<'a>(&'a str);

impl EinheitMesswert<'static> {
    pub const SI_EINHEIT: EinheitMesswert<'static> = EinheitMesswert("SI_Einheit");
    pub const KONVENTIONELLE_EINHEIT: EinheitMesswert<'static> = EinheitMesswert("konventionelle_Einheit");
    pub const DIMENSIONSLOSE_GROESSE: EinheitMesswert<'static> = EinheitMesswert("dimensionslose_Groesse");
}
