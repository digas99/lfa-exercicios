grammar Calculator;

program     :   stat* EOF ;

stat        :   expr? NEWLINE ;

expr        :   expr op=('*'|'/'|'%') expr  # ExprMultDivMod
            |   expr op=('+'|'-') expr      # ExprAddSub
            |   Integer                     # ExprInteger
            |   '(' expr ')'                # ExprParens
            |   '-' Integer                 # ExprBeginsWithMinus
            |   '+' Integer                 # ExprBeginsWithPlus
            ;
        
Integer :   [0-9]+ ;
NEWLINE :   '\r'? '\n' ;
WS  :   [ \t]+ -> skip ;
COMMENT :   '#' .*? '\n' -> skip ;