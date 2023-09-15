grammar Kontext;

fragment DIGIT: '0'..'9';
INTEGER: DIGIT+;
WS: (' '|'\t'|'„'|'”'|'“')+ -> skip;
FILLWORDS: ('der'|'die'|'das'|'Der'|'Die'|'Das'|'ist'|'sind'|'sein'|'werden'|'wurde') -> skip;
DATE: DIGIT DIGIT PUNKT DIGIT DIGIT PUNKT DIGIT DIGIT DIGIT DIGIT;
THRESHOLD_SPECIALS: '!L'|'!-'|'!H'|'!+';

KOMMA: ',';
PUNKT: '.';

// ----------------------------------------------------------------------------
// Identifiers
// ----------------------------------------------------------------------------

// Fragment: Object identifier
objekt:
    ('Obj_Tier/Sonstiges'|'Obj_' INTEGER ('(' ('Obj_Untersuchungsabrechnung'|'Obj_Laborergebnisbericht'|'Obj_Untersuchungsanforderung'|'Obj_Untersuchungsergebnis Mikrobiologie'|'Obj_Einsenderidentifikation'|'Obj_Betriebsstätte'|'Obj_Abrechnung GKV'|'Obj_Abrechnung PKV'|'Obj_Tier/Sonstiges'|'Obj_Veranlassungsgrund'|'Patient'|'Obj_Untersuchungsergebnis Klinische Chemie'|'Obj_Material'|'Obj_Abrechnungsinformationen'|'Obj_RgEmpfaenger'|'Obj_Normalwert'|'Obj_Adressat') ')')?);
// Fragment: Field identifier
fk:
    'FK' INTEGER (undOder INTEGER)*;
// Fragment: Format rule identifier
format:
    'F' INTEGER;

// ----------------------------------------------------------------------------
// Checks for existence
// ----------------------------------------------------------------------------

// Fragment: Alternative spellings of a field exists
existsAlternatives:
    occurrence? existWords;
existWords:
    'vorhanden'|'vorkommen'|'vorkommt'|'im Auftrag übermittelt'|'bekannt'|'verwendet'|'erlaubt'|'gefüllt'|'folgt';
// Fragment: Alternative spellings of a field does not exist
notExistsAlternatives:
    'nicht' existsAlternatives;
// Fragment: Alternative spellings of a field does only exist
onlyExists:
    'nur' dann? existsAlternatives;

// ----------------------------------------------------------------------------
// Programming logic
// ----------------------------------------------------------------------------

// Logical: Boolean operators
undOder:
    'und'|'oder'|'und/oder'|KOMMA|'sowie'|'bzw.'|'/'|'in Kombination mit';

wenn:
    'Wenn'|'Nur wenn'|'wenn'|'Falls';

dann:
    'dann' 'gilt'? ':'?;

occurrence:
    ('Mindestens'|'mindestens'|'maximal') occurrenceCount?;

occurrenceCount:
    'einmal'|'zweimal'|'eine'|'einem'|'zwei'|'mehr- fach'|'mehrfach'|'mehrere';

requirement:
    'muss'|'kann'|'darf'|'müssen'|'können'|'dürfen';

either:
    'Entweder'|'Es kann entweder'|'entweder';

substring:
    'Stellen' INTEGER '–' INTEGER;

// Fragment: "if" condition
ifCondition:
    (imObjekt|inSatzart)? fieldExistsOrHasSpecificValue imObjekt?;

// ----------------------------------------------------------------------------
// Containers
// ----------------------------------------------------------------------------

// Fragment: Container for Objekt
imObjekt:
    (('Im'|'im'|'In'|'in') ('folgenden')? occurrence? objekt (undOder objekt)*|'innerhalb des entsprechenden Objektes'|'in diesem Objekt');
// Fragment: Container for Satzart
inSatzart:
    ('in'|'In') 'jeweiliger'? 'Satzart' INTEGER ('oder' INTEGER)?;

fuerSatzart:
    'Für Satzart' INTEGER ('oder' INTEGER)? 'gilt';

// ----------------------------------------------------------------------------
// Field content rules
// ----------------------------------------------------------------------------

// Logical: Field with one or multiple values
fieldAssignmentValue:
    objekt|INTEGER|'D'|DATE|THRESHOLD_SPECIALS;
fieldAssignment:
    'Feldkennung'? fk? imObjekt? (fieldAssignmentOperator fieldAssignmentValue (undOder fieldAssignmentValue)* inSatzart?|fieldAssignmentVerbal);
fieldAssignmentVerbal:
    ('mit den Inhalten'|'mit dem Inhalt'|'nur'|'einmal mit'|'Wert') fieldAssignmentValue (undOder 'einmal mit'? fieldAssignmentValue)* ('in' fk occurrenceCount)? existWords;
// Logical: one and/or multiple fields have a given content
fieldContent:
    imObjekt? ('Feldinhalt von'|'Feldinhalt'|'Inhalt von'|'Inhalt'|'Inhalte')? substring? ('des' objekt 'von denen' inSatzart|fieldAssignment|fk 'in' occurrence objekt fieldAssignmentOperatorEquals fieldAssignmentValue) ('vorhanden'|'abweichen'|'vorkommen'|'vorkommt')?;
fieldAssignmentOperator:
    (fieldAssignmentOperatorEquals|fieldAssignmentOperatorNotEquals|fieldAssignmentOperatorGreaterThanOrEqualTo|fieldAssignmentOperatorLessThan);
fieldAssignmentOperatorEquals:
    '='|'gleich'|'mit den Werten'|'mit dem Wert'|('nur'? 'Wert')|'Werte';
fieldAssignmentOperatorNotEquals:
    'mit Inhalt'? '≠'|'ungleich'|'nicht';
fieldAssignmentOperatorGreaterThanOrEqualTo:
    '>=';
fieldAssignmentOperatorLessThan:
    '<';

// Logical: one and/or multiple fields exist
fieldExists:
    'ein'? fk occurrence? (undOder fk)* (imObjekt|inSatzart)? 'auch'? 'muss jeweils'? ('nur'? occurrenceCount)? (onlyExists|existsAlternatives|notExistsAlternatives);
fieldExistsAlternative1:
    imObjekt? occurrence? 'eine Feldkennung aus nachfolgender Liste vorhanden sein:' INTEGER (undOder INTEGER)*;
fieldExistsAlternative2:
    'Werte' 'Feldkennungen' fk (undOder fk)* 'bekannt';
fieldExistsAlternative3:
    (imObjekt|inSatzart) ('min.'|'nur')? fk (undOder fk)* existsAlternatives;
fieldExistsAlternative4:
    'Inhalt' fk 'im Auftrag übermittelt';

fieldRule:
    'Regel' 'F' INTEGER;

objektExists:
    objekt (onlyExists|existsAlternatives|notExistsAlternatives);

fieldFollows:
    fk fk 'folgen';

fieldAssignmentFollows:
    fieldAssignment fk 'folgt';

fieldTransmitted:
    'Felder übertragen';

fieldDiffers:
    'Inhalt von' fk 'jeweils einen anderen Wert aufweisen';

// Fragment: Either fields exist or have a specific content
fieldExistsOrHasSpecificValueElement:
    fieldContent|fieldExists|fieldExistsAlternative1|fieldExistsAlternative2|fieldExistsAlternative3|fieldExistsAlternative4|fieldRule|objektExists|fieldFollows|fieldTransmitted|fieldDiffers|fieldAssignmentFollows;
fieldExistsOrHasSpecificValue:
    fieldExistsOrHasSpecificValueElement (undOder fieldExistsOrHasSpecificValueElement)*;

// ----------------------------------------------------------------------------
// Rules
// ----------------------------------------------------------------------------

// Rule: Either one of the 'fields' exists, and might exclude each other
eitherFieldExists:
    either fieldExists PUNKT?;
eitherFieldExistsExclusion:
    'Beide Feldkennungen dürfen' 'nicht'? 'gleichzeitig' existsAlternatives PUNKT?;
eitherFieldExistsInverted:
    (imObjekt|inSatzart) requirement (either|occurrenceCount|'auch')? fieldExistsOrHasSpecificValue (KOMMA eitherFieldExistsInverted)?;
anyFieldExists:
    occurrence fk (undOder fk)* requirement existsAlternatives PUNKT?;
fieldOnlyIn:
    fieldExistsOrHasSpecificValue requirement 'nur' imObjekt existsAlternatives PUNKT?;

ifThenFieldExistsOrValue:
    (fuerSatzart ':')? wenn ifCondition (undOder wenn? ifCondition)* KOMMA? dann? (requirement|'gilt für den'|'als Inhalte'|'Ist') (either|'auch'|occurrence)? fieldExistsOrHasSpecificValue PUNKT?;
ifThenFieldExistsOrValueInverted:
    'Eine'? fk (undOder fk)* imObjekt? requirement (imObjekt (undOder objekt)*)? occurrence? (onlyExists|existsAlternatives|notExistsAlternatives) (KOMMA? wenn ifCondition)? PUNKT? ('Ausnahmen:' (eitherFieldExists|eitherFieldExistsInverted|ifThenFieldExistsOrValue|ifThenFieldExistsOrValueInverted))?;

ifThenCombinations:
    wenn ifCondition (undOder wenn? ifCondition)* KOMMA? dann? requirement occurrenceCount? 'folgenden Kombinationen' existsAlternatives ':' ('-' fk (undOder fk)+ undOder?)+ PUNKT;

ifThenIfThen:
    wenn ifCondition (undOder wenn? ifCondition)* KOMMA? dann ifThenFieldExistsOrValue;

ifThenRule:
    wenn ifCondition (undOder wenn? ifCondition)* KOMMA? dann 'gilt für den Inhalt' fk 'Regel' format PUNKT?;

anyCombinationAllowed:
    'Es kann eine beliebige Kombination der zwei Feldkennungen vorhanden' PUNKT?;

objektExistsRule:
    objekt inSatzart? requirement 'nur'? 'dann'? existsAlternatives KOMMA? wenn ifCondition PUNKT?;

insuranceCard:
    wenn 'eine Versichertenkarte eingelesen' KOMMA? 'dann'? requirement fieldExistsOrHasSpecificValue PUNKT?;

satzartOccurrence:
    wenn inSatzart occurrenceCount 'Objekte mit' fieldAssignment existsAlternatives KOMMA? dann? (requirement 'sich diese in der Kombination der Inhalte' fk (undOder fk)* 'unterscheiden'|requirement fieldExistsOrHasSpecificValue) PUNKT?;

objektOccurrence:
    wenn objekt occurrenceCount imObjekt (undOder imObjekt)* existsAlternatives KOMMA requirement fieldAssignment KOMMA 'alle anderen Werte dürfen nur jeweils einmal vorkommen' PUNKT?;


// Top level rule
regel:
    (eitherFieldExists|eitherFieldExistsExclusion|eitherFieldExistsInverted|anyFieldExists|fieldOnlyIn|ifThenFieldExistsOrValue|ifThenFieldExistsOrValueInverted|ifThenIfThen|ifThenRule|anyCombinationAllowed|ifThenCombinations|objektExistsRule|insuranceCard|satzartOccurrence|objektOccurrence)+;
