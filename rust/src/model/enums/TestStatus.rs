pub struct TestStatus<'a>(&'a str);

impl TestStatus<'static> {
    pub const KEINE_GESICHERTE_INFORMATION: TestStatus<'static> = TestStatus("keine_gesicherte_Information");
    pub const ERGEBNIS_FOLGT: TestStatus<'static> = TestStatus("Ergebnis_folgt");
    pub const ERGEBNIS: TestStatus<'static> = TestStatus("Ergebnis");
    pub const ERGEBNIS_KORRIGIERT: TestStatus<'static> = TestStatus("Ergebnis_korrigiert");
    pub const ERGEBNIS_ERMITTELT: TestStatus<'static> = TestStatus("Ergebnis_ermittelt");
    pub const BEFUNDERGEBNIS: TestStatus<'static> = TestStatus("Befundergebnis");
    pub const BEFUNDERGEBNIS_BEREITS_BERICHTET: TestStatus<'static> = TestStatus("Befundergebnis_bereits_berichtet");
    pub const BEFUNDERGEBNIS_KORRIGIERT: TestStatus<'static> = TestStatus("Befundergebnis_korrigiert");
    pub const ERGEBNIS_FEHLT: TestStatus<'static> = TestStatus("Ergebnis_fehlt");
    pub const ERWEITERTE_ANALYTIK_ERFORDERLICH: TestStatus<'static> = TestStatus("Erweiterte_Analytik_erforderlich");
    pub const MATERIAL_FEHLT: TestStatus<'static> = TestStatus("Material_fehlt");
    pub const STORNIERT: TestStatus<'static> = TestStatus("Storniert");
}
