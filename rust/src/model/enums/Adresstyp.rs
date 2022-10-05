pub struct Adresstyp<'a>(&'a str);

impl Adresstyp<'static> {
    pub const PHYSISCHER_ORT: Adresstyp<'static> = Adresstyp("PhysischerOrt");
    pub const POSTANSCHRIFT: Adresstyp<'static> = Adresstyp("Postanschrift");
}
