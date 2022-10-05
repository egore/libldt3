pub struct GeschlechtNormalbereich<'a>(&'a str);

impl GeschlechtNormalbereich<'static> {
    pub const MAENNLICH: GeschlechtNormalbereich<'static> = GeschlechtNormalbereich("maennlich");
    pub const WEIBLICH: GeschlechtNormalbereich<'static> = GeschlechtNormalbereich("weiblich");
    pub const UNBESTIMMT: GeschlechtNormalbereich<'static> = GeschlechtNormalbereich("unbestimmt");
}
