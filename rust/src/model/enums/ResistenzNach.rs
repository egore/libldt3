pub struct ResistenzNach<'a>(&'a str);

impl ResistenzNach<'static> {
    pub const CLSI: ResistenzNach<'static> = ResistenzNach("CLSI");
    pub const EUCAST: ResistenzNach<'static> = ResistenzNach("EUCAST");
    pub const CA_FMS: ResistenzNach<'static> = ResistenzNach("CA_FMS");
}
