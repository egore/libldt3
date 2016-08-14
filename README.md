# About

LDT is short for "Labor Daten Transfer", a data format to exchange data with laboratories in Germany. As this is a format only used in Germany, the rest of this file will be in German.

Dieser Parser wurde auf Basis der LDT 3.0.3 Spezifikation entwickelt. Die Spezifikation wurde von <ftp://ftp.kbv.de/ita-update/Labor/LDT3.0/EXT_ITA_VGEX_LDT%203_0_3_Gesamtdokument.pdf> bezogen.

# Verwendung

Um einen LDT-Datensatz aus einer Datei einzulesen, wird der LdtReader verwendet. Dieser bietet mit der Methode read() die Möglichkeit, LDT 3.0 einzulesen.

	Path src = ...
	LdtReader reader = new LdtReader(Mode.STRICT);
	List<Satz> data = reader.read(src);

Um einen LDT-Datensatz zu erzeugen kann der LdtWriter verwendet werden. Dessen Methode write() schreibt LDT 3.0 in eine Datei.

	LdtWriter writer = new LdtWriter(Mode.STRICT);
	writer.write(data, target);

Sowohl LdtReader als auch LdtWriter bieten die Möglichkeit, einen STRICT- oder RELAXED-Modus zu verwenden. Der STRICT-Modus bricht im Fehlerfall sofort ab, der RELAXED-Modus versucht auch dann noch weiterzumachen. Der RELAXED-Modus ist als "letzte Hoffnung" gedacht, um aus kaputten LDT 3.0 Datensätzen noch möglichst viel Informationen auszulesen. Für die alltägliche Arbeit sollte der STRICT-Modus verwendet werden.

# Status

Der Parser wurde erfolgreich an verschiedenen Beispielen aus dem von der KVB bereitgestellten Testpaket getestet. Er kann die Daten vollständig einlesen und wieder ausgeben. Es gibt dabei lediglich Unterschiede im Umgang mit Fließkomma-Zahlen, so dass der ausgegebene Datensatz nicht 1:1 dem eingelesenen entspricht.

Folgende Punkte sind anzumerken:
- Feldarten (Muss, Kann, Bedingt muss, Bedingt kann) sind an den vermerkt, werden aber nicht ausgewertet
- Ein Großteil der Feld-Regeln werden nicht ausgewertet
- Die Hierarchie im Java-Datenmodell nutzt nicht die volle Tiefe von 5 Ebenen in einem Objekt, wie von LDT 3.0 spezifiziert. Hintergrund ist, dass diese nicht als zwingend notwendig angenommen wird

Folende "erlaubte Inhalte" sind nicht umgesetzt
- Nicht unterstützt: E003, E005, E010, E011, E012, E014, E028, E036, E048, E066, E071-E145, E149-E155, E157
- Nicht in LDT 3.0 verwendet: E031

# Verwendung in kommerziellen Projekten

Die Lizenz von libldt3 erlaubt es Ihnen, diese Bibliothek kostenfrei auch in kommerziellen Projekten zu nutzen. Sie können den Quellcode beliebig verändern und Änderungen müssen nicht veröffentlich werden. Wichtig: Die Verwendung geschieht auf eigene Gefahr und Verantwortung. Für die Entwicklung dieser Bibliothek kommen **keine** Qualitätsmanagementsysteme entsprechend der ISO 9001 oder ISO 13485 zum Einsatz. Wird Ihre Software unter Berücksichtigung dieser Normen entwickelt, obliegt Ihnen die Pflicht der Qualitätssicherung.

# Nächste Schritte für libldt3

- Das Datenmodell wurde noch keiner Prüfung auf Inkonsistenzen unterzogen (z.B. ob @Feld innerhalb eines Objekts eindeutig ist)
- Es gibt potentiell Redundanzen von wiederholenden @Feld-Kombinationen
- String wurde als default Datentyp verwendet. Daher gibt es Felder, die eigentlich andere Typen wären (z.B. Integer)

# Offene Fragen im LDT Datenmodell

Warum ist der Status der Dringlichkeit (7262) in der Untersuchungsanforderung (Obj\_0059) n mal vorgesehen? Dies widerspricht sowohl der Logik als auch der Auftragsinformation (Obj\_0013).