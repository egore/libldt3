grammar Kontext;

fragment DIGIT: '0'..'9';
INTEGER: DIGIT+;
WS: (' '|'\t'|'„'|'”'|'“')+ -> skip;
FILLWORDS: ('der'|'die'|'das'|'Der'|'Die'|'Das'|'ist'|'sind'|'sein') -> skip;
DATE: DIGIT DIGIT PUNKT DIGIT DIGIT PUNKT DIGIT DIGIT DIGIT DIGIT;

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
    ('Obj_Tier/Sonstiges'|'Obj_' INTEGER ('(' ('Obj_Untersuchungsabrechnung'|'Obj_Laborergebnisbericht'|'Obj_Untersuchungsanforderung'|'Obj_Untersuchungsergebnis Mikrobiologie'|'Obj_Einsenderidentifikation'|'Obj_Betriebsstätte'|'Obj_Abrechnung GKV'|'Obj_Tier/Sonstiges'|'Obj_Veranlassungsgrund'|'Patient'|'Obj_Untersuchungsergebnis Klinische Chemie'|'Obj_Material'|'Obj_Abrechnungsinformationen'|'Obj_RgEmpfaenger'|'Obj_Normalwert'|'Obj_Adressat') ')')?);
// Fragment: Field identifier
fk:
    'FK' INTEGER;

// ----------------------------------------------------------------------------
// Checks for existence
// ----------------------------------------------------------------------------

// Fragment: Alternative spellings of a field exists
existsAlternatives:
    ('vorhanden'|'mindestens einmal vorkommen'|'vorkommt'|'vorkommen'|'im Auftrag übermittelt wurde'|'bekannt');
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
    'und'|'oder'|'und/oder'|KOMMA|'sowie';

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
    (('Im'|'im'|'In'|'in') objekt|'innerhalb des entsprechenden Objektes');
// Fragment: Container for Satzart
inSatzart:
    'in' 'jeweiliger'? 'Satzart' INTEGER ('oder' INTEGER)?;

// ----------------------------------------------------------------------------
// Field content rules
// ----------------------------------------------------------------------------

// Logical: Field with one or multiple values
fieldAssignment:
    'Feldkennung'? fk? imObjekt? (fieldAssignmentOperator (objekt|INTEGER|'D'|DATE) (undOder INTEGER)* inSatzart?|fieldAssignmentVerbal);
fieldAssignmentVerbal:
    ('mit den Inhalten'|'mit dem Inhalt'|'nur'|'einmal mit') (objekt|INTEGER) (undOder 'einmal mit'? INTEGER)*? ('vorkommt'|'erlaubt'|'gefüllt');
// Logical: one and/or multiple fields have a given content
fieldContent:
    ('Feldinhalt von'|'Feldinhalt'|'Inhalt von'|'Inhalt')? fieldAssignment;
fieldAssignmentOperator:
    (fieldAssignmentOperatorEquals|fieldAssignmentOperatorNotEquals|fieldAssignmentOperatorGreaterThanOrEqualTo);
fieldAssignmentOperatorEquals:
    '='|'gleich'|'nur Wert'|'mit den Werten'|'Wert'|'Werte';
fieldAssignmentOperatorNotEquals:
    '≠'|'ungleich';
fieldAssignmentOperatorGreaterThanOrEqualTo:
    '>=';

// Logical: one and/or multiple fields exist
fieldExists:
    fk ('maximal zweimal'|'mindestens einmal')? (undOder fk)* (imObjekt|inSatzart)? 'auch'? 'muss jeweils'? ('nur einmal'|'zweimal')? (onlyExists|existsAlternatives|notExistsAlternatives);
fieldExistsAlternative1:
    imObjekt? 'mindestens einmal'? 'eine Feldkennung aus nachfolgender Liste vorhanden sein:' INTEGER (undOder INTEGER)*;
fieldExistsAlternative2:
    'Werte' 'Feldkennungen' fk (undOder fk)* 'bekannt';
fieldExistsAlternative3:
    imObjekt 'min.' fk (undOder fk)* existsAlternatives;

fieldRule:
    'Regel' 'F' INTEGER;

objektExists:
    objekt (onlyExists|existsAlternatives|notExistsAlternatives);

// Fragment: Either fields exist or have a specific content
fieldExistsOrHasSpecificValue:
    (fieldContent|fieldExists|fieldExistsAlternative1|fieldExistsAlternative2|fieldExistsAlternative3|fieldRule|objektExists) (undOder (fieldContent|fieldExists|fieldExistsAlternative1|fieldExistsAlternative2|fieldExistsAlternative3|fieldRule|objektExists))*;

// ----------------------------------------------------------------------------
// Rules
// ----------------------------------------------------------------------------

// Rule: Either one of the 'fields' exists, and might exclude each other
eitherFieldExists:
    ('Entweder'|'Es kann entweder') fieldExists PUNKT ('Beide Feldkennungen dürfen nicht gleichzeitig' existsAlternatives PUNKT)?;
eitherFieldExistsInverted:
    imObjekt (MUSS|KANN|DARF) 'entweder'? fieldExists;

ifThenFieldExistsOrValue:
    wenn ifCondition (undOder wenn? ifCondition)* KOMMA? 'dann'? 'gilt:'? (MUSS|KANN|DARF|'müssen'|'gilt für den'|'als Inhalte'|'Ist'|'können') ('entweder'|'auch')? fieldExistsOrHasSpecificValue PUNKT?;
ifThenFieldExistsOrValueInverted:
    fk (undOder fk)* imObjekt? (MUSS|KANN|DARF|'müssen') imObjekt? 'mindestens einmal'? (onlyExists|existsAlternatives|notExistsAlternatives) (KOMMA? wenn ifCondition)? PUNKT? ('Ausnahmen:' (eitherFieldExists | eitherFieldExistsInverted | ifThenFieldExistsOrValue | ifThenFieldExistsOrValueInverted))?;

ifThenIfThen:
    wenn ifCondition (undOder wenn? ifCondition)* KOMMA? 'dann gilt:' ifThenFieldExistsOrValue;

anyCombinationAllowed:
    'Es kann eine beliebige Kombination der zwei Feldkennungen vorhanden' PUNKT?;

// Top level rule
regel:
    (eitherFieldExists | eitherFieldExistsInverted | ifThenFieldExistsOrValue | ifThenFieldExistsOrValueInverted | ifThenIfThen | anyCombinationAllowed)+;
