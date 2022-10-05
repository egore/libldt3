pub struct LdtVersion<'a>(&'a str);

impl LdtVersion<'static> {
    pub const LDT3_1_0: LdtVersion<'static> = LdtVersion("LDT3_1_0");
}
