grammar Questions;

program     :   question* EOF ;

question    :   identifier '(' TEXT ')' '{' answers+ '}' ;

identifier  :   ID '.' NUMBER ;

answers     :   TEXT ':' NUMBER ';' ;

TEXT :   '"' .*? '"';
NUMBER  :   [0-9]+;
ID  :   [a-zA-Z0-9]+ ;
COMMENT :   '#' .*? '\n' -> skip ;
WS  :   [ \t\n\r]+ -> skip ;