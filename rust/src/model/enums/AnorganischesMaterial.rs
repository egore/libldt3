pub struct AnorganischesMaterial<'a>(&'a str);

impl AnorganischesMaterial<'static> {
    pub const WASSER: AnorganischesMaterial<'static> = AnorganischesMaterial("Wasser");
    pub const LUFT: AnorganischesMaterial<'static> = AnorganischesMaterial("Luft");
    pub const NICHT_BESTIMMBAR: AnorganischesMaterial<'static> = AnorganischesMaterial("nicht_bestimmbar");
    pub const SONSTIGES: AnorganischesMaterial<'static> = AnorganischesMaterial("sonstiges");
}
