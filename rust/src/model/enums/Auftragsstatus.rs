pub struct Auftragsstatus<'a>(&'a str);

impl Auftragsstatus<'static> {
    pub const AUFTRAG_NICHT_ABGESCHLOSSEN: Auftragsstatus<'static> = Auftragsstatus("Auftrag_nicht_abgeschlossen");
    pub const AUFTRAG_ABGESCHLOSSEN: Auftragsstatus<'static> = Auftragsstatus("Auftrag_abgeschlossen");
}
