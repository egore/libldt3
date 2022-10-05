pub struct Laborart<'a>(&'a str);

impl Laborart<'static> {
    pub const LABORGEMEINSCHAFT: Laborart<'static> = Laborart("Laborgemeinschaft");
    pub const FACHARZTLABOR: Laborart<'static> = Laborart("Facharztlabor");
    pub const LEISTUNGSERBRINGERGEMEINSCHAFT: Laborart<'static> = Laborart("Leistungserbringergemeinschaft");
    pub const EIGENLABOR: Laborart<'static> = Laborart("Eigenlabor");
}
