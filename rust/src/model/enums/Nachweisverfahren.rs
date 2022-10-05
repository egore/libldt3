pub struct Nachweisverfahren<'a>(&'a str);

impl Nachweisverfahren<'static> {
    pub const SONSTIGE_WENN_ERREGER_UND_RESISTENZ_ANGEFORDERT: Nachweisverfahren<'static> = Nachweisverfahren("sonstige_wenn_Erreger_und_Resistenz_angefordert");
    pub const ANTIGEN_NACHWEIS: Nachweisverfahren<'static> = Nachweisverfahren("Antigen_Nachweis");
    pub const PCR: Nachweisverfahren<'static> = Nachweisverfahren("PCR");
    pub const MIKROSKOPIE: Nachweisverfahren<'static> = Nachweisverfahren("Mikroskopie");
    pub const AGLUTINATION: Nachweisverfahren<'static> = Nachweisverfahren("Aglutination");
    pub const KULTUR: Nachweisverfahren<'static> = Nachweisverfahren("Kultur");
    pub const BIOCHEMISCHE_IDENTIFIKATION: Nachweisverfahren<'static> = Nachweisverfahren("Biochemische_Identifikation");
    pub const MALDI_TOF: Nachweisverfahren<'static> = Nachweisverfahren("Maldi_Tof");
}
