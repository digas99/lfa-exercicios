grammar Calculator;

program     :   stat* EOF ;

stat        :   expr NEWLINE                # StatExpr
            |   assign NEWLINE              # StatAssign
            |   NEWLINE                     # StatNewLine
            ;

assign      :   ID '=' expr ;

expr        :   expr op=('*'|'/'|'%') expr  # ExprMultDivMod
            |   expr op=('+'|'-') expr      # ExprAddSub
            |   Integer                     # ExprInteger
            |   '(' expr ')'                # ExprParens
            |   '-' Integer                 # ExprBeginsWithMinus
            |   '+' Integer                 # ExprBeginsWithPlus
            |   ID                          # ExprId
            ;
        
Integer :   [0-9]+ ;
ID      :   [a-zA-Z_]+ ;
NEWLINE :   '\r'? '\n' ;
WS  :   [ \t]+ -> skip ;
COMMENT :   '#' .*? '\n' -> skip ;