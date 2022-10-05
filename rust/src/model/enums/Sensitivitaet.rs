pub struct Sensitivitaet<'a>(&'a str);

impl Sensitivitaet<'static> {
    pub const SENSIBEL: Sensitivitaet<'static> = Sensitivitaet("sensibel");
    pub const INTERMEDIAER: Sensitivitaet<'static> = Sensitivitaet("intermediaer");
    pub const RESISTENT: Sensitivitaet<'static> = Sensitivitaet("resistent");
}
