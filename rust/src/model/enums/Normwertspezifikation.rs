pub struct Normwertspezifikation<'a>(&'a str);

impl Normwertspezifikation<'static> {
    pub const METHODENSPEZIFISCHE_STANDARDS_WHO: Normwertspezifikation<'static> = Normwertspezifikation("MethodenspezifischeStandardsWHO");
    pub const METHODENSPEZIFISCHE_STANDARDS_IFCC: Normwertspezifikation<'static> = Normwertspezifikation("MethodenspezifischeStandardsIFCC");
    pub const METHODENSPEZIFISCHE_STANDARDS_DGKL: Normwertspezifikation<'static> = Normwertspezifikation("MethodenspezifischeStandardsDGKL");
    pub const SONSTIGE_STANDARDS: Normwertspezifikation<'static> = Normwertspezifikation("SonstigeStandards");
    pub const PATIENTENSPEZIFISCHE_EINFLUSSGROESSE_ALTER: Normwertspezifikation<'static> = Normwertspezifikation("PatientenspezifischeEinflussgroesseAlter");
    pub const PATIENTENSPEZIFISCHE_EINFLUSSGROESSE_GESCHLECHT: Normwertspezifikation<'static> = Normwertspezifikation("PatientenspezifischeEinflussgroesseGeschlecht");
    pub const PATIENTENSPEZIFISCHE_EINFLUSSGROESSE_ALTER_GESCHLECHT: Normwertspezifikation<'static> = Normwertspezifikation("PatientenspezifischeEinflussgroesseAlterGeschlecht");
    pub const PATIENTENSPEZIFISCHE_EINFLUSSGROESSE_SSW: Normwertspezifikation<'static> = Normwertspezifikation("PatientenspezifischeEinflussgroesseSSW");
    pub const PATIENTENSPEZIFISCHE_EINFLUSSGROESSE_ALTER_SSW: Normwertspezifikation<'static> = Normwertspezifikation("PatientenspezifischeEinflussgroesseAlterSSW");
    pub const WEITERE_PATIENTENSPEZIFISCHE_EINFLUSSGROESSEN: Normwertspezifikation<'static> = Normwertspezifikation("WeiterePatientenspezifischeEinflussgroessen");
    pub const INFORMATION_PATIENTENSPEZIFISCHER_EINFLUSSGROESSE_ALTER_FEHLTE: Normwertspezifikation<'static> = Normwertspezifikation("InformationPatientenspezifischerEinflussgroesseAlterFehlte");
    pub const INFORMATION_PATIENTENSPEZIFISCHER_EINFLUSSGROESSE_GESCHLECHT_FEHLTE: Normwertspezifikation<'static> = Normwertspezifikation("InformationPatientenspezifischerEinflussgroesseGeschlechtFehlte");
    pub const INFORMATION_PATIENTENSPEZIFISCHER_EINFLUSSGROESSE_ALTER_GESCHLECHT_FEHLTE: Normwertspezifikation<'static> = Normwertspezifikation("InformationPatientenspezifischerEinflussgroesseAlterGeschlechtFehlte");
    pub const FUNKTIONSPROFILE: Normwertspezifikation<'static> = Normwertspezifikation("Funktionsprofile");
}
