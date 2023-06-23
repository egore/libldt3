grammar Kontext;

fragment DIGIT: '0'..'9';
INTEGER: DIGIT+;
WS: (' '|'\t'|'„'|'”'|'“')+ -> skip;
FILLWORDS: ('der'|'die'|'das'|'Der'|'Die'|'Das'|'ist'|'sind'|'sein') -> skip;

DARF: 'darf';
KANN: 'kann';
MUSS: 'muss';
KOMMA: ',';
PUNKT: '.';

// ----------------------------------------------------------------------------
// Identifiers
// ----------------------------------------------------------------------------

// Fragment: Object identifier
objekt:
    'Obj_' INTEGER ('(' ('Obj_Untersuchungsabrechnung'|'Obj_Laborergebnisbericht'|'Obj_Untersuchungsanforderung'|'Obj_Untersuchungsergebnis Mikrobiologie'|'Obj_Einsenderidentifikation'|'Obj_Betriebsstätte'|'Obj_Abrechnung GKV'|'Obj_Tier/Sonstiges'|'Obj_Veranlassungsgrund'|'Patient'|'Obj_Untersuchungsergebnis Klinische Chemie'|'Obj_Material') ')')?;
// Fragment: Field identifier
fk:
    'FK' INTEGER;

// ----------------------------------------------------------------------------
// Checks for existence
// ----------------------------------------------------------------------------

// Fragment: Alternative spellings of a field exists
existsAlternatives:
    ('vorhanden'|'mindestens einmal vorkommen'|'vorkommt'|'vorkommen'|'im Auftrag übermittelt wurde');
// Fragment: Alternative spellings of a field does not exist
notExistsAlternatives:
    'nicht' existsAlternatives;
// Fragment: Alternative spellings of a field does only exist
onlyExists:
    'nur' 'dann'? existsAlternatives;

// ----------------------------------------------------------------------------
// Programming logic
// ----------------------------------------------------------------------------

// Logical: Boolean operators
undOder:
    'und'|'oder'|'und/oder'|KOMMA;

wenn:
    'Wenn'|'Nur wenn'|'wenn'|'Falls';

// Fragment: "if" condition
ifCondition:
    (imObjekt|inSatzart)? fieldExistsOrHasSpecificValue imObjekt?;

// ----------------------------------------------------------------------------
// Containers
// ----------------------------------------------------------------------------

// Fragment: Container for Objekt
imObjekt:
    ('im'|'in') objekt;
// Fragment: Container for Satzart
inSatzart:
    'in' 'jeweiliger'? 'Satzart' INTEGER ('oder' INTEGER)?;

// ----------------------------------------------------------------------------
// Field content rules
// ----------------------------------------------------------------------------

// Logical: Field with one or multiple values
fieldAssignment:
    fk imObjekt? (('='|'≠'|'ungleich'|'nur Wert'|'mit den Werten'|'Wert') (objekt|INTEGER) (undOder INTEGER)* inSatzart?|('mit den Inhalten'|'mit dem Inhalt') (objekt|INTEGER) (undOder INTEGER)*? 'vorkommt');
// Logical: one and/or multiple fields have a given content
fieldContent:
    ('Feldinhalt von'|'Inhalt von'|'Inhalt')? fieldAssignment;

// Logical: one and/or multiple fields exist
fieldExists:
    fk (undOder fk)+ imObjekt? 'muss jeweils'? existsAlternatives;
fieldNotExists:
    fk (undOder fk)+ imObjekt? notExistsAlternatives;
fieldOnlyExists:
    fk (undOder fk)+ imObjekt? onlyExists;

fieldRule:
    'Regel' 'F' INTEGER;

// Fragment: Either fields exist or have a specific content
fieldExistsOrHasSpecificValue:
    (fieldContent|fieldExists|fieldNotExists|fieldOnlyExists|fieldRule) (undOder (fieldContent|fieldExists|fieldNotExists|fieldOnlyExists|fieldRule))*;

// ----------------------------------------------------------------------------
// Rules
// ----------------------------------------------------------------------------

// Rule: Either one of the 'fields' exists, and might exclude each other
eitherExists:
    ('Entweder'|'Es kann entweder') fieldExists PUNKT ('Beide Feldkennungen dürfen nicht gleichzeitig' existsAlternatives PUNKT)?;

ifThenExists:
    wenn ifCondition (undOder ifCondition)* KOMMA? 'dann'? (MUSS|KANN|DARF|'müssen') 'entweder'? fk 'mindestens einmal'? imObjekt? 'auch'? (onlyExists|existsAlternatives|notExistsAlternatives) PUNKT?;

ifThenValue:
    wenn ifCondition (undOder ifCondition)* KOMMA? 'dann'? (MUSS|KANN|DARF|'müssen'|'gilt für den') 'auch'? fieldExistsOrHasSpecificValue PUNKT?;

ifThenExistsInverted:
    fk (undOder fk)* imObjekt? (MUSS|KANN|DARF) imObjekt? 'mindestens einmal'? (onlyExists|existsAlternatives|notExistsAlternatives) (KOMMA? wenn ifCondition)? PUNKT?;

// Top level rule
regel:
    (eitherExists | ifThenExists | ifThenValue | ifThenExistsInverted)+;
