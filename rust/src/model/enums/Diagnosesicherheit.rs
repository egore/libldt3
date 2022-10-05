pub struct Diagnosesicherheit<'a>(&'a str);

impl Diagnosesicherheit<'static> {
    pub const GESICHERTE_DIAGNOSE: Diagnosesicherheit<'static> = Diagnosesicherheit("GesicherteDiagnose");
    pub const AUSSCHLUSS: Diagnosesicherheit<'static> = Diagnosesicherheit("Ausschluss");
    pub const VERDACHT_AUF: Diagnosesicherheit<'static> = Diagnosesicherheit("VerdachtAuf");
    pub const ZUSTAND_NACH: Diagnosesicherheit<'static> = Diagnosesicherheit("ZustandNach");
}
