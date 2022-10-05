pub struct Materialart<'a>(&'a str);

impl Materialart<'static> {
    pub const ORGANISCH: Materialart<'static> = Materialart("organisch");
    pub const ANORGANISCH: Materialart<'static> = Materialart("anorganisch");
}
