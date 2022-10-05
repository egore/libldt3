pub struct Grenzwertindikator<'a>(&'a str);

impl Grenzwertindikator<'static> {
    pub const N: Grenzwertindikator<'static> = Grenzwertindikator("N");
    pub const H: Grenzwertindikator<'static> = Grenzwertindikator("H");
    pub const PLUS: Grenzwertindikator<'static> = Grenzwertindikator("PLUS");
    pub const HH: Grenzwertindikator<'static> = Grenzwertindikator("HH");
    pub const PLUS_PLUS: Grenzwertindikator<'static> = Grenzwertindikator("PLUS_PLUS");
    pub const L: Grenzwertindikator<'static> = Grenzwertindikator("L");
    pub const MINUS: Grenzwertindikator<'static> = Grenzwertindikator("MINUS");
    pub const LL: Grenzwertindikator<'static> = Grenzwertindikator("LL");
    pub const MINUS_MINUS: Grenzwertindikator<'static> = Grenzwertindikator("MINUS_MINUS");
    pub const EXTREM_H: Grenzwertindikator<'static> = Grenzwertindikator("EXTREM_H");
    pub const EXTREM_PLUS: Grenzwertindikator<'static> = Grenzwertindikator("EXTREM_PLUS");
    pub const EXTREM_L: Grenzwertindikator<'static> = Grenzwertindikator("EXTREM_L");
    pub const EXTREM_MINUS: Grenzwertindikator<'static> = Grenzwertindikator("EXTREM_MINUS");
    pub const A: Grenzwertindikator<'static> = Grenzwertindikator("A");
    pub const AA: Grenzwertindikator<'static> = Grenzwertindikator("AA");
}
