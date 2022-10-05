pub struct Dringlichkeit<'a>(&'a str);

impl Dringlichkeit<'static> {
    pub const NOTFALL: Dringlichkeit<'static> = Dringlichkeit("Notfall");
    pub const EILIG: Dringlichkeit<'static> = Dringlichkeit("eilig");
}
