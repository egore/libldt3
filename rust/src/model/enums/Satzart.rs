pub struct Satzart<'a>(&'a str);

impl Satzart<'static> {
    pub const LABOR_DATENPAKET_HEADER: Satzart<'static> = Satzart("LaborDatenpaketHeader");
    pub const LABOR_DATENPAKET_ABSCHLUSS: Satzart<'static> = Satzart("LaborDatenpaketAbschluss");
    pub const PRAXIS_DATENPAKET_HEADER: Satzart<'static> = Satzart("PraxisDatenpaketHeader");
    pub const PRAXIS_DATENPAKET_ABSCHLUSS: Satzart<'static> = Satzart("PraxisDatenpaketAbschluss");
    pub const BEFUND: Satzart<'static> = Satzart("Befund");
    pub const AUFTRAG: Satzart<'static> = Satzart("Auftrag");
}
