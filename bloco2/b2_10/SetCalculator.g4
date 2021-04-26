grammar SetCalculator;

program :   state* EOF ;

state   :   expr | atrib ;

atrib   :   UPPERCASE '=' expr ;

expr    :   expr '+' expr   # ExprUnion
        |   expr '&' expr   # ExprIntersection
        |   expr '\\' expr  # ExprComplement
        |   '(' expr ')'    # ExprParens
        |   set             # ExprSet
        |   UPPERCASE       # ExprID
        ;

set     :  '{' (WORD ','?)*  '}' ;

WORD   :   [a-z0-9];
UPPERCASE   :   [A-Z]+;
COMMENT :   '--' .*? '\n' -> skip ;
WS  :   [ \t\n\r]+ -> skip ;