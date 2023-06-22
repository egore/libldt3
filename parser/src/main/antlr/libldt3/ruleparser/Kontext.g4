grammar Kontext;

fragment DIGIT: '0'..'9';
INTEGER: DIGIT+;
WS : (' '|'\t')+ -> skip;

ENTWEDER: 'Entweder';
ES_KANN_ENTWEDER : 'Es kann entweder' ;
FK: 'FK';
FELDINHALT_VON: 'Feldinhalt von';
INHALT_VON: 'Inhalt von';
IST_VORHADEN: 'ist vorhanden';
KOMMA: ',';
ODER: 'oder';
PUNKT: '.';
UND: 'und';
WENN: 'Wenn';

// Field identifier
fk:
    FK INTEGER;

fkInitialized:
    fk values?;

// Field with one or multiple values
fkAssignment:
    fk '=' values?;

// Fragement: one and/or multiple values
values:
    INTEGER (KOMMA INTEGER)* ((UND|ODER) INTEGER)*;

// Fragment: one and/or multiple fields exist
exists:
    'die'? fk ((UND|ODER) fk)+ existsAlternatives;

// Fragment: one and/or multiple fields have a given content
inhalt:
    (FELDINHALT_VON|INHALT_VON|'der Inhalt') fkAssignment 'ist'?;

// Fragment: Alternative was of specifying a field exists
existsAlternatives:
    (IST_VORHADEN|'vorkommen'|'vorhanden'|'vorhanden sein'|'vorhanden ist') ;

// Rule: Either one of the 'fields' exists
eitherExists:
    (ENTWEDER|ES_KANN_ENTWEDER) exists PUNKT exclusion?;

// Rule: Fields exclude each other
exclusion:
    'Beide Feldkennungen dürfen nicht gleichzeitig vorhanden sein' PUNKT;

// Fragment: Either fields exist or have a specific content
inhaltExists:
    (inhalt|exists) ((UND|ODER) (inhalt|exists))*;

ifRuleMust:
    WENN inhaltExists KOMMA? 'dann'? 'muss' fk 'auch'? existsAlternatives PUNKT?;

ifRuleMay:
    WENN inhaltExists KOMMA? 'dann'? 'kann' fk 'auch'? existsAlternatives PUNKT?;

ifRuleMustNot:
    WENN inhaltExists KOMMA? 'dann'? 'darf' fk ('nicht vorkommen'|'nicht vorhanden'|'nicht vorhanden sein') PUNKT?;

// Top level rule
regel:
    (eitherExists | ifRuleMust | ifRuleMay | ifRuleMustNot)+;

