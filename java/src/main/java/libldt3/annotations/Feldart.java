/*
 * Copyright 2016-2022  Christoph Brill <opensource@christophbrill.de>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package libldt3.annotations;

/**
 * Auswahl der Feldart (Pflicht oder Kann)
 */
public enum Feldart {

    /**
     * M = unbedingtes Mussfeld/Objekt
     * Ein unbedingtes Muss-Feld muss in einem Satz bzw. Objekt vorhanden sein. Sollte in der Spalte Vorkommen ein
     * mehrfaches bzw. n-faches Vorkommen zugelassen sein, so muss dieses Feld mindestens einmal in dem Satz bzw. Objekt
     * vorkommen.
     */
    muss,
    /**
     * m = bedingtes Mussfeld/Objekt
     * Bei einem bedingten Muss-Feld ist die Existenz an eine bestimmte Regel (siehe Spalte "Regel") oder an das
     * Auftreten eines referenzierten Feldes auf einer übergeordneten Hierachiestufe (siehe Spalte "Vorkommen")
     * gebunden. Ein bedingtes Muss-Feld muss in einem Satz bzw. Objekt vorhanden sein, wenn entweder in der Spalte
     * "Regel" ein Eintrag vorhanden und erfüllt ist oder das auf der übergeordneten Hierarchiestufe referenzierte
     * Feld existiert.
     */
    bedingt_muss,
    /**
     * K = Kannfeld/Objekt
     * Ein Kann-Feld kann in einem Satz bzw. Objekt auftreten. Das Vorkommen ist an keinerlei Bedingungen geknüpft.
     * Sollten jedoch die entsprechenden Daten vorliegen, müssen sie in dem dazugehörigen Feld dargestellt werden, wobei
     * der Nachweis über das Vorhandensein der Daten – im Gegensatz zu bedingten Muss-Feldern – nicht programmtechnisch
     * geprüft werden kann.
     */
    kann,
    /**
     * k = bedingtes Kann-Feld/Objekt
     * Bei einem bedingten Kann-Feld ist die Existenz an eine bestimmte Regel (siehe Spalte "Regel") oder an das
     * Auftreten eines referenzierten Feldes auf einer übergeordneten Hierachiestufe (siehe Spalte "Vorkommen")
     * gebunden. Ein bedingtes Kann-Feld darf in einem Satz bzw. Objekt vorhanden sein, wenn entweder in der Spalte
     * "Regel" ein Eintrag vorhanden und erfüllt ist oder das auf der übergeordneten Hierarchiestufe referenzierte Feld
     * existiert.
     */
    bedingt_kann

}
