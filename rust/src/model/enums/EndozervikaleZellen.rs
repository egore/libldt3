pub struct EndozervikaleZellen<'a>(&'a str);

impl EndozervikaleZellen<'static> {
    pub const VORHANDEN: EndozervikaleZellen<'static> = EndozervikaleZellen("vorhanden");
    pub const NICHT_VORHANDEN: EndozervikaleZellen<'static> = EndozervikaleZellen("nicht_vorhanden");
}
