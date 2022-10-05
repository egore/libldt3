pub struct AnforderungNothilfepass<'a>(&'a str);

impl AnforderungNothilfepass<'static> {
    pub const NUR_BEI_ERYTHROZYTENANTIKOERPER_NACHWEIS: AnforderungNothilfepass<'static> = AnforderungNothilfepass("NurBeiErythrozytenantikoerperNachweis");
    pub const AUSSTELLEN: AnforderungNothilfepass<'static> = AnforderungNothilfepass("Ausstellen");
}
