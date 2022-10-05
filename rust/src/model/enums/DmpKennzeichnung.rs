pub struct DmpKennzeichnung<'a>(&'a str);

impl DmpKennzeichnung<'static> {
    pub const KEINE_ANGABE: DmpKennzeichnung<'static> = DmpKennzeichnung("keine_Angabe");
    pub const DIABETES_MELLITUS_TYP2: DmpKennzeichnung<'static> = DmpKennzeichnung("DiabetesMellitusTyp2");
    pub const BRUSTKREBS: DmpKennzeichnung<'static> = DmpKennzeichnung("Brustkrebs");
    pub const KORONARE_HERZKRANKHEIT: DmpKennzeichnung<'static> = DmpKennzeichnung("KoronareHerzkrankheit");
    pub const DIABETES_MELLITUS_TYP1: DmpKennzeichnung<'static> = DmpKennzeichnung("DiabetesMellitusTyp1");
    pub const ASTHMA_BRONCHIALE: DmpKennzeichnung<'static> = DmpKennzeichnung("AsthmaBronchiale");
    pub const CHRONIC_OBSTRUCTIVE_PULMONARY_DISEASE: DmpKennzeichnung<'static> = DmpKennzeichnung("ChronicObstructivePulmonaryDisease");
}
