pub struct Wachstum<'a>(&'a str);

impl Wachstum<'static> {
    pub const NICHT_NACHWEISBAR_KEIN_WACHSTUM: Wachstum<'static> = Wachstum("nicht_nachweisbar_kein_Wachstum");
    pub const SPAERLICH: Wachstum<'static> = Wachstum("spaerlich");
    pub const MAESSIG_VEREINZELT: Wachstum<'static> = Wachstum("maessig_vereinzelt");
    pub const REICHLICH: Wachstum<'static> = Wachstum("reichlich");
    pub const MASSENHAFT: Wachstum<'static> = Wachstum("massenhaft");
}
