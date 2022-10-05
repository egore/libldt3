pub struct Benachrichtigungsgrund<'a>(&'a str);

impl Benachrichtigungsgrund<'static> {
    pub const PATHOLOGISCH_AUFFAELLIGER_BEFUND: Benachrichtigungsgrund<'static> = Benachrichtigungsgrund("PathologischAuffaelligerBefund");
    pub const LEBENSBEDROHLICHER_ZUSTAND: Benachrichtigungsgrund<'static> = Benachrichtigungsgrund("LebensbedrohlicherZustand");
    pub const WIEDERVORSTELLUNG_EMPFOHLEN: Benachrichtigungsgrund<'static> = Benachrichtigungsgrund("WiedervorstellungEmpfohlen");
    pub const PROBENMATERIAL_NICHT_VERWENDBAR: Benachrichtigungsgrund<'static> = Benachrichtigungsgrund("ProbenmaterialNichtVerwendbar");
    pub const PROBENMATERIAL_UNVOLLSTAENDIG: Benachrichtigungsgrund<'static> = Benachrichtigungsgrund("ProbenmaterialUnvollstaendig");
}
