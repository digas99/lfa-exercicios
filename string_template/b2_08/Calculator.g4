grammar Calculator;

program  :   stat* EOF ;    

stat    :   (print | atrib) ';' ;

print   :   'print' expr ;

atrib   :   expr '->' ID  ;

expr returns[String var = null]:       
            op=('-'|'+') expr           # ExprUnary
        |   NUMBER '/' NUMBER           # ExprFrac
        |   e1=expr op=('*'|':') e2=expr    # ExprMultDivMod
        |   e1=expr op=('+'|'-') e2=expr    # ExprAddSub
        |   NUMBER                      # ExprNumber
        |   '(' e=expr ')' '^' pow=expr    # ExprPower
        |   '(' expr ')'                # ExprParens
        |   ID                          # ExprId
        |   'reduce' expr               # ExprReduce
        |   'read' STRING               # ExprRead
        ;  

STRING :   '"' .*? '"';
NUMBER  :   [0-9]+;
ID  :   [a-zA-Z0-9]+;
COMMENT :   '//' .*? '\n' -> skip ;
WS  :   [ \t\n\r]+ -> skip ;