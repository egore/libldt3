pub struct Privattarif<'a>(&'a str);

impl Privattarif<'static> {
    pub const PRIVAT: Privattarif<'static> = Privattarif("Privat");
    pub const POST_B: Privattarif<'static> = Privattarif("PostB");
    pub const KVB: Privattarif<'static> = Privattarif("KVB");
}
