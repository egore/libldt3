pub struct Versichertenart<'a>(&'a str);

impl Versichertenart<'static> {
    pub const MITGLIED: Versichertenart<'static> = Versichertenart("Mitglied");
    pub const FAMILIENVERSICHERTER: Versichertenart<'static> = Versichertenart("Familienversicherter");
    pub const RENTNER: Versichertenart<'static> = Versichertenart("Rentner");
}
