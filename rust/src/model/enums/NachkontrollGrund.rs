pub struct NachkontrollGrund<'a>(&'a str);

impl NachkontrollGrund<'static> {
    pub const NACH_ENTZUENDUNGSBEHANDLUNG: NachkontrollGrund<'static> = NachkontrollGrund("NachEntzuendungsbehandlung");
    pub const NACH_OESTROGENBEHANDLUNG: NachkontrollGrund<'static> = NachkontrollGrund("NachOestrogenbehandlung");
    pub const SONSTIGES: NachkontrollGrund<'static> = NachkontrollGrund("Sonstiges");
}
