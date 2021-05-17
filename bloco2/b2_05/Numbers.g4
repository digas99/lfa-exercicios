grammar Numbers;

file	: (line)+ EOF;

line	: NUMBER '-' WORD;

NUMBER	: [0-9]+ ;
WORD	: [a-zA-Z]+ ;
// NEWLINE	: '\r'? '\n' ;
WS	: [ \t\r\n]+ -> skip ;