grammar Calculator;

program	: stat* EOF ;

stat	: (assignment | expr)? NEWLINE ;

assignment: ID '=' expr ;

expr	: expr op=('*'|'/'|'%') expr	#ExprMultDivMod
		| expr op=('+'|'-') expr		#ExprAddSub
		| Integer						#ExprInteger
		| '(' expr ')'					#ExprParent
		| value=('+'|'-') Integer		#ExprUnary
		| ID							#ExprID
		;
	
ID: [a-zA-Z]+;	
Integer	: [0-9]+;
NEWLINE : '\r'? '\n';
WS	: [ \t]+ -> skip;
COMMENT: '#' .*? '\n' -> skip;