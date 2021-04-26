grammar Calculator;

program  :   stat* EOF ;    

stat    :   (print | atrib) ';' ;

print   :   'print' expr ;

atrib   :   expr '->' ID  ;

expr    :   op=('-'|'+') expr           # ExprUnary
        |   frac                        # ExprFrac
        |   expr op=('*'|':') expr      # ExprMultDivMod
        |   expr op=('+'|'-') expr      # ExprAddSub
        |   NUMBER                      # ExprNumber
        |   '(' expr ')' '^' expr       # ExprPower
        |   '(' expr ')'                # ExprParens
        |   ID                          # ExprId
        |   'reduce' expr               # ExprReduce
        |   'read' STRING               # ExprRead
        ;  

frac    :   NUMBER '/' NUMBER;

STRING :   '"' .*? '"';
NUMBER  :   [0-9]+;
ID  :   [a-zA-Z0-9]+;
COMMENT :   '//' .*? '\n' -> skip ;
WS  :   [ \t\n\r]+ -> skip ;