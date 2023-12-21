package libldt3.model.regel.erlaubt;

import libldt3.model.regel.Regel;

/**
 * Der Prüfwert wird mit dem Algorithmus SHA-1 berechnet. Der SHA-1 Wert wird aus allen Zeichen vor der Zeile der Feldkennung 9300 generiert.
 *
 * Dient der Sicherstellung der Integrität der Daten in der Datei.
 */
public class E157 implements Regel {

    @Override
    public boolean isValid(String value) {
        return true;
    }

}
