grammar Hello;

prog : ((greetings | bye) NEWLINE)+ ;

greetings	: 'hello' name ;
bye			: 'bye' name ;

name	: ID+ ;

ID	: [a-zA-Z]+ ;
NEWLINE	: '\r'? '\n' ;
WS	: [ \t\r\n]+ -> skip ;