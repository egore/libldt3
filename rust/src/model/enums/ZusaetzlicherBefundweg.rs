pub struct ZusaetzlicherBefundweg<'a>(&'a str);

impl ZusaetzlicherBefundweg<'static> {
    pub const PAPIER: ZusaetzlicherBefundweg<'static> = ZusaetzlicherBefundweg("Papier");
    pub const TELEFON: ZusaetzlicherBefundweg<'static> = ZusaetzlicherBefundweg("Telefon");
    pub const FAX: ZusaetzlicherBefundweg<'static> = ZusaetzlicherBefundweg("Fax");
    pub const EMAIL: ZusaetzlicherBefundweg<'static> = ZusaetzlicherBefundweg("EMail");
    pub const DFUE: ZusaetzlicherBefundweg<'static> = ZusaetzlicherBefundweg("DFUE");
    pub const TOURPOST: ZusaetzlicherBefundweg<'static> = ZusaetzlicherBefundweg("Tourpost");
    pub const KV_CONNECT: ZusaetzlicherBefundweg<'static> = ZusaetzlicherBefundweg("kvConnect");
}
