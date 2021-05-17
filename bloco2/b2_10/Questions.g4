grammar Questions;

program		: question* EOF ;

question	: ID '.' ID '(' TEXT ')' '{' answer+ '}' ;

answer		: TEXT ':' Integer ';' ;

Integer	: [0-9]+;
ID: [a-zA-Z0-9]+;	
TEXT: '"'.*? '"';
WS	: [ \t\n\r]+ -> skip;
COMMENT: '#' .*? '\n' -> skip;