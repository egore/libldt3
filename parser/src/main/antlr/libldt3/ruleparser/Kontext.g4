grammar Kontext;

fragment DIGIT: '0'..'9';
INTEGER: DIGIT+;
WS : (' '|'\t'|'„'|'”'|'“')+ -> skip;

DARF: 'darf';
ENTWEDER: 'Entweder';
ES_KANN_ENTWEDER : 'Es kann entweder' ;
FK: 'FK';
FELDINHALT_VON: 'Feldinhalt von';
INHALT_VON: 'Inhalt von';
IST_VORHADEN: 'ist vorhanden';
KANN: 'kann';
KOMMA: ',';
MUSS: 'muss';
ODER: 'oder';
PUNKT: '.';
UND: 'und';
WENN: 'Wenn';

objekt:
    'Obj_' INTEGER ('(Obj_' ('Untersuchungsabrechnung'|'Laborergebnisbericht'|'Untersuchungsanforderung'|'Untersuchungsergebnis Mikrobiologie'|'Einsenderidentifikation'|'Betriebsstätte'|'Abrechnung GKV'|'Obj_Tier/Sonstiges') ')')?;

imObjekt:
    ('im'|'in') objekt;

// Field identifier
fk:
    FK INTEGER;

undOder:
    (UND|ODER|'und/oder'|',');

fkInitialized:
    fk values?;

// Field with one or multiple values
fkAssignment:
    fk ('='|'≠'|'ungleich') values?;

// Fragement: one and/or multiple values
values:
    (objekt|INTEGER) (KOMMA INTEGER)* (undOder INTEGER)*;

// Fragment: one and/or multiple fields exist
exists:
    'die'? fk (undOder fk)+ existsAlternatives;

// Fragment: one and/or multiple fields have a given content
inhalt:
    (FELDINHALT_VON|INHALT_VON|'der Inhalt')? fkAssignment 'ist'?;

// Fragment: Alternative was of specifying a field exists
existsAlternatives:
    (IST_VORHADEN|'vorkommen'|'vorhanden'|'vorhanden sein'|'vorhanden ist'|'mindestens einmal vorkommen'|'vorhanden sind') ;

// Rule: Either one of the 'fields' exists
eitherExists:
    (ENTWEDER|ES_KANN_ENTWEDER) exists PUNKT exclusion?;

// Rule: Fields exclude each other
exclusion:
    'Beide Feldkennungen dürfen nicht gleichzeitig vorhanden sein' PUNKT;

// Fragment: Either fields exist or have a specific content
inhaltExists:
    (inhalt|exists) (undOder (inhalt|exists))*;

ifContent:
    (WENN|'Nur wenn'|'wenn') imObjekt? inhaltExists;

ifRuleMust:
    ifContent KOMMA? 'dann'? MUSS fk 'mindestens einmal'? imObjekt? 'auch'? existsAlternatives PUNKT?;

ifRuleMay:
    ifContent KOMMA? 'dann'? KANN fk imObjekt? 'auch'? existsAlternatives PUNKT?;

ifRuleMustNot:
    ifContent KOMMA? 'dann'? DARF fk imObjekt? ('nicht vorkommen'|'nicht vorhanden'|'nicht vorhanden sein'|'nicht vorhanden ist') PUNKT?;

ifRuleMayInverted:
    fk DARF imObjekt? ('nur vorhanden sein'|'nur vorkommen') KOMMA? 'wenn' inhalt imObjekt existsAlternatives PUNKT?;

ifRuleCanInverted:
    fk imObjekt? KANN imObjekt? ('nur vorhanden sein'|'nur vorkommen') KOMMA? ifContent PUNKT?;

ifRuleCanNotInverted:
    fk imObjekt? KANN imObjekt? ('nur vorhanden sein'|'nur vorkommen') KOMMA? fk imObjekt? ('nicht vorkommen'|'nicht vorhanden'|'nicht vorhanden sein'|'nicht vorhanden ist') PUNKT?;

// Top level rule
regel:
    (eitherExists | ifRuleMust | ifRuleMay | ifRuleMustNot | ifRuleMayInverted | ifRuleCanInverted | ifRuleCanNotInverted)+;

