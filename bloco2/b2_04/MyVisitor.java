public class MyVisitor extends PrefixCalculatorBaseVisitor<Integer> {

   @Override public Integer visitStat(PrefixCalculatorParser.StatContext ctx) {
      if (ctx.expr() != null)
         System.out.println(visit(ctx.expr()));
      return null;
   }

   @Override public Integer visitExpPrefix(PrefixCalculatorParser.ExpPrefixContext ctx) {
      int op1 = visit(ctx.expr(0));
      int op2 = visit(ctx.expr(1));
      int result = 0;
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

   @Override public Integer visitExprNumber(PrefixCalculatorParser.ExprNumberContext ctx) {
      return Integer.parseInt(ctx.Number().getText());
   }
}
