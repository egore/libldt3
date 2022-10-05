pub struct Dokumentenquelle<'a>(&'a str);

impl Dokumentenquelle<'static> {
    pub const EIGEN: Dokumentenquelle<'static> = Dokumentenquelle("eigen");
    pub const FREMD: Dokumentenquelle<'static> = Dokumentenquelle("fremd");
}
