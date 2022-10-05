pub struct DatensatzAbsender<'a>(&'a str);

impl DatensatzAbsender<'static> {
    pub const PRIMAERSYSTEM: DatensatzAbsender<'static> = DatensatzAbsender("Primaersystem");
    pub const ORDER_ENTRY: DatensatzAbsender<'static> = DatensatzAbsender("OrderEntry");
    pub const SCANSYSTEM: DatensatzAbsender<'static> = DatensatzAbsender("Scansystem");
}
