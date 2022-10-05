pub struct ErgebnisStatus<'a>(&'a str);

impl ErgebnisStatus<'static> {
    pub const NICHT_NACHWEISBAR: ErgebnisStatus<'static> = ErgebnisStatus("nicht_nachweisbar");
    pub const ZWEIFELHAFT_UNSPEZIFISCH: ErgebnisStatus<'static> = ErgebnisStatus("zweifelhaft_unspezifisch");
    pub const NACHWEISBAR: ErgebnisStatus<'static> = ErgebnisStatus("nachweisbar");
}
