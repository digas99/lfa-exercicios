grammar FractionsCalculator;

program	: stat* EOF ;

stat	: (print | assignment) ';' ;

print	: 'print' expr ;

assignment	: expr '->' ID ;

expr	: expr op=('*'|':') expr					#ExprMultDivMod
		| expr op=('+'|'-') expr					#ExprAddSub
		| Integer									#ExprInteger
		| '(' expr ')'								#ExprParent
		| value=('+'|'-') (Integer|fraction)		#ExprUnary
		| ID										#ExprID
		| fraction									#ExprFrac
		| 'read' TEXT 								#ExprRead 
		| expr '^' value=('+'|'-')? expr			#ExprPower
		| 'reduce' expr								#ExprReduce
		;

fraction	: Integer '/' Integer ;

ID: [a-zA-Z]+;	
Integer	: [0-9]+;
TEXT: '"'.*? '"';
WS	: [ \t\n\r]+ -> skip;
COMMENT: '//' .*? '\n' -> skip;