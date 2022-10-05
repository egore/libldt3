pub struct EinschreibestatusSelektivvertraege<'a>(&'a str);

impl EinschreibestatusSelektivvertraege<'static> {
    pub const NICHT_EINGESCHRIEBEN: EinschreibestatusSelektivvertraege<'static> = EinschreibestatusSelektivvertraege("NichtEingeschrieben");
    pub const EINGESCHRIEBEN: EinschreibestatusSelektivvertraege<'static> = EinschreibestatusSelektivvertraege("Eingeschrieben");
    pub const EINSCHREIBUNG_BEANTRAGT: EinschreibestatusSelektivvertraege<'static> = EinschreibestatusSelektivvertraege("EinschreibungBeantragt");
}
