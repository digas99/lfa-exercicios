public class Interpreter extends SuffixCalculatorBaseVisitor<Double> {

   @Override public Double visitStat(SuffixCalculatorParser.StatContext ctx) {
      if (ctx.expr() != null)
         System.out.println(visit(ctx.expr()));
      return null;
   }

   @Override public Double visitExpSuffix(SuffixCalculatorParser.ExpSuffixContext ctx) {
      double op1 = visit(ctx.expr(0));
      double op2 = visit(ctx.expr(1));
      double result = 0;
      switch(ctx.op.getText()) {
         case "+":
            result = op1+op2;
            break;
         case "-":
            result = op1-op2;
            break;		
         case "*":
            result = op1*op2;
            break;
         case "/":
            result = op1/op2;
            break;				
      }
      return result;
   }

   @Override public Double visitExprNumber(SuffixCalculatorParser.ExprNumberContext ctx) {
      return Double.parseDouble(ctx.getText());
   }
}
