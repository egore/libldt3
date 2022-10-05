pub struct MedikationsStatus<'a>(&'a str);

impl MedikationsStatus<'static> {
    pub const AKUTMEDIKATION: MedikationsStatus<'static> = MedikationsStatus("Akutmedikation");
    pub const BEDARFSMEDIKATION: MedikationsStatus<'static> = MedikationsStatus("Bedarfsmedikation");
    pub const DAUERMEDIKATION: MedikationsStatus<'static> = MedikationsStatus("Dauermedikation");
    pub const SELBSTMEDIKATION: MedikationsStatus<'static> = MedikationsStatus("Selbstmedikation");
}
