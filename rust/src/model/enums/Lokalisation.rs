pub struct Lokalisation<'a>(&'a str);

impl Lokalisation<'static> {
    pub const RECHTS: Lokalisation<'static> = Lokalisation("rechts");
    pub const LINKS: Lokalisation<'static> = Lokalisation("links");
    pub const BEIDERSEITS: Lokalisation<'static> = Lokalisation("beiderseits");
}
