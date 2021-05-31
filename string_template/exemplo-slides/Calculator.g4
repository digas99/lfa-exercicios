grammar Calculator;

main: stat* ;

stat: expr;

expr returns[String var = null] : 
       e1=expr op=('*' | '/') e2=expr   #ExprMultDiv
     | e1=expr op=('+' | '-') e2=expr   #ExprAddSub
     | '(' expr ')'                     #ExprParens
     | NUMBER                           #ExprNumber
     ;
    
NUMBER: [0-9]+;
WS: [ \t\r\n]+ -> skip;
ERROR: .;