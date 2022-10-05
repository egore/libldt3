pub struct OrganischesMaterial<'a>(&'a str);

impl OrganischesMaterial<'static> {
    pub const TIERISCH: OrganischesMaterial<'static> = OrganischesMaterial("tierisch");
    pub const PFLANZLICH: OrganischesMaterial<'static> = OrganischesMaterial("pflanzlich");
    pub const NICHT_BESTIMMBAR: OrganischesMaterial<'static> = OrganischesMaterial("nicht_bestimmbar");
}
