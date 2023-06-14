package libldt3.model.regel.erlaubt;

import libldt3.model.regel.Regel;

/**
 * S = Sensibel bei Standardexposition
 * I = Sensibel bei erhöhter Exposition
 * R = Resistent
 * N = IE (keine Interpretation)
 * siehe {@see http://www.eucast.org/clinical_breakpoints/} und {@see http://www.nak-deutschland.org}
 */
public class E025 implements Regel {
    @Override
    public boolean isValid(String value) {
        return "S".equals(value) || "I".equals(value) || "R".equals(value) || "N".equals(value);
    }
}
