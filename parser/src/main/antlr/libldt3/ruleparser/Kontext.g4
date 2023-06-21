grammar Kontext;

fragment DIGIT: '0'..'9';
INTEGER: DIGIT+;
WS : (' '|'\t')+ -> skip;

ENTWEDER: 'Entweder';
FK: 'FK';
FELDINHALT_VON: 'Feldinhalt von';
INHALT_VON: 'Inhalt von';
IST_VORHADEN: 'ist vorhanden.';
KOMMA: ',';
ODER: 'oder';
PUNKT: '.';
WENN: 'Wenn';

fk:
    FK INTEGER;

fkInitialized:
    fk values?;

mustExistRule:
    ENTWEDER fk (ODER fk)+ IST_VORHADEN;

exclusionRule:
  'Beide Feldkennungen dürfen nicht gleichzeitig vorhanden sein.';

canExistRule:
  'Es kann entweder die' fk (ODER fk)+ 'vorhanden sein' PUNKT exclusionRule?;

values:
    '=' INTEGER (ODER INTEGER)*;

ifRuleMust:
    WENN (FELDINHALT_VON|INHALT_VON) fkInitialized ('und der Inhalt' fkInitialized)? KOMMA? 'muss' fk ('vorkommen'|'vorhanden sein') PUNKT;

ifRuleMustNot:
    WENN (FELDINHALT_VON|INHALT_VON) fkInitialized ('und der Inhalt' fkInitialized)? KOMMA? 'darf' fk ('nicht vorkommen'|'nicht vorhanden sein') PUNKT;

regel:
    (mustExistRule | canExistRule | ifRuleMust | ifRuleMustNot)+;

