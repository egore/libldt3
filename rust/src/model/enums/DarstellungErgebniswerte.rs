pub struct DarstellungErgebniswerte<'a>(&'a str);

impl DarstellungErgebniswerte<'static> {
    pub const NUMERISCH: DarstellungErgebniswerte<'static> = DarstellungErgebniswerte("Numerisch");
    pub const NUMERISCH_UNTERGRENZE: DarstellungErgebniswerte<'static> = DarstellungErgebniswerte("Numerisch_Untergrenze");
    pub const NUMERISCH_OBERGRENZE: DarstellungErgebniswerte<'static> = DarstellungErgebniswerte("Numerisch_Obergrenze");
    pub const ALPHANUMERISCH: DarstellungErgebniswerte<'static> = DarstellungErgebniswerte("Alphanumerisch");
    pub const TITER: DarstellungErgebniswerte<'static> = DarstellungErgebniswerte("Titer");
    pub const TITER_UNTERGRENZE: DarstellungErgebniswerte<'static> = DarstellungErgebniswerte("Titer_Untergrenze");
    pub const TITER_OBERGRENZE: DarstellungErgebniswerte<'static> = DarstellungErgebniswerte("Titer_Obergrenze");
    pub const SONSTIGE: DarstellungErgebniswerte<'static> = DarstellungErgebniswerte("Sonstige");
}
