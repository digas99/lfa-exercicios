grammar NumbersFormater;

prog    :   stat+ EOF;

stat    :   word+ NEWLINE ;

word    :   ID
        |   '-' ID
        |   ID ':'
        ;

ID  :   [a-zA-Z]+ ;
WS  :   [ \t]+ -> skip ;
NEWLINE :   '\r'? '\n' ;