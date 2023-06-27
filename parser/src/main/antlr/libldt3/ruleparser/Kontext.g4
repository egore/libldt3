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
    ('Obj_Tier/Sonstiges'|'Obj_' INTEGER ('(' ('Obj_Untersuchungsabrechnung'|'Obj_Laborergebnisbericht'|'Obj_Untersuchungsanforderung'|'Obj_Untersuchungsergebnis Mikrobiologie'|'Obj_Einsenderidentifikation'|'Obj_Betriebsstätte'|'Obj_Abrechnung GKV'|'Obj_Tier/Sonstiges'|'Obj_Veranlassungsgrund'|'Patient'|'Obj_Untersuchungsergebnis Klinische Chemie'|'Obj_Material') ')')?);
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
    ('Im'|'im'|'in') objekt;
// Fragment: Container for Satzart
inSatzart:
    'in' 'jeweiliger'? 'Satzart' INTEGER ('oder' INTEGER)?;

// ----------------------------------------------------------------------------
// Field content rules
// ----------------------------------------------------------------------------

// Logical: Field with one or multiple values
fieldAssignment:
    fk imObjekt? (fieldAssignmentOperator (objekt|INTEGER) (undOder INTEGER)* inSatzart?|('mit den Inhalten'|'mit dem Inhalt') (objekt|INTEGER) (undOder INTEGER)*? 'vorkommt');
// Logical: one and/or multiple fields have a given content
fieldContent:
    ('Feldinhalt von'|'Inhalt von'|'Inhalt')? fieldAssignment;
fieldAssignmentOperator:
    (fieldAssignmentOperatorEquals|fieldAssignmentOperatorNotEquals);
fieldAssignmentOperatorEquals:
    '='|'gleich'|'nur Wert'|'mit den Werten'|'Wert';
fieldAssignmentOperatorNotEquals:
    '≠'|'ungleich';


// Logical: one and/or multiple fields exist
fieldExists:
    fk 'mindestens einmal'? (undOder fk)* imObjekt? 'auch'? 'muss jeweils'? (onlyExists|existsAlternatives|notExistsAlternatives);

fieldRule:
    'Regel' 'F' INTEGER;

// Fragment: Either fields exist or have a specific content
fieldExistsOrHasSpecificValue:
    (fieldContent|fieldExists|fieldRule) (undOder (fieldContent|fieldExists|fieldRule))*;

// ----------------------------------------------------------------------------
// Rules
// ----------------------------------------------------------------------------

// Rule: Either one of the 'fields' exists, and might exclude each other
eitherExists:
    ('Entweder'|'Es kann entweder') fieldExists PUNKT ('Beide Feldkennungen dürfen nicht gleichzeitig' existsAlternatives PUNKT)?;

ifThenValue:
    wenn ifCondition (undOder wenn? ifCondition)* KOMMA? 'dann'? (MUSS|KANN|DARF|'müssen'|'gilt für den') ('entweder'|'auch')? fieldExistsOrHasSpecificValue PUNKT?;

ifThenExistsInverted:
    fk (undOder fk)* imObjekt? (MUSS|KANN|DARF) imObjekt? 'mindestens einmal'? (onlyExists|existsAlternatives|notExistsAlternatives) (KOMMA? wenn ifCondition)? PUNKT?;

eitherExistsInverted:
    imObjekt (MUSS|KANN|DARF) 'entweder'? fieldExists;

// Top level rule
regel:
    (eitherExists | ifThenValue | ifThenExistsInverted | eitherExistsInverted)+;
