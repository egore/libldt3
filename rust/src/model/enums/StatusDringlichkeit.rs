pub struct StatusDringlichkeit<'a>(&'a str);

impl StatusDringlichkeit<'static> {
    pub const STROKE: StatusDringlichkeit<'static> = StatusDringlichkeit("Stroke");
    pub const BEKANNT: StatusDringlichkeit<'static> = StatusDringlichkeit("bekannt");
}
