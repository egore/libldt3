pub struct ResistenzInterpretation<'a>(&'a str);

impl ResistenzInterpretation<'static> {
    pub const NICHT_GETESTET: ResistenzInterpretation<'static> = ResistenzInterpretation("nichtGetestet");
    pub const SENSIBEL_WIRKSAM: ResistenzInterpretation<'static> = ResistenzInterpretation("sensibelWirksam");
    pub const MAESSIG_SENSIBEL_SCHWACH_WIRKSAM: ResistenzInterpretation<'static> = ResistenzInterpretation("maessigSensibelSchwachWirksam");
    pub const RESISTENT_UNWIRKSAM: ResistenzInterpretation<'static> = ResistenzInterpretation("resistentUnwirksam");
    pub const WIRKSAM_IN_HOHEN_KONZENTRATIONEN: ResistenzInterpretation<'static> = ResistenzInterpretation("wirksamInHohenKonzentrationen");
}
