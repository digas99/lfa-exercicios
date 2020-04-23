grammar Calculator;

program     :   stat* EOF ;

stat        :   expr NEWLINE                # StatExpr
            |   assign NEWLINE              # StatAssign
            |   NEWLINE                     # StatNewLine
            ;

assign      :   ID '=' expr ;

expr        :   op=('-'|'+') expr           # ExprUnary
            |   expr op=('*'|'/'|'%') expr  # ExprMultDivMod
            |   expr op=('+'|'-') expr      # ExprAddSub
            |   Integer                     # ExprInteger
            |   '(' expr ')'                # ExprParens
            |   ID                          # ExprId
            ;
        
Integer :   [0-9]+ ;
ID      :   [a-zA-Z_]+ ;
NEWLINE :   '\r'? '\n' ;
COMMENT :   '#' .*? '\n' -> skip ;
WS  :   [ \t]+ -> skip ;
