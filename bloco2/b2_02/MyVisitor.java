public class MyVisitor extends SuffixCalculatorBaseVisitor<Double> {

   @Override public Double visitStat(SuffixCalculatorParser.StatContext ctx) {
      if (ctx.expr() != null)
         System.out.println(visit(ctx.expr()));

      return null;
   }

   @Override public Double visitExprNumber(SuffixCalculatorParser.ExprNumberContext ctx) {
      return Double.parseDouble(ctx.Number().getText());
   }

   @Override public Double visitExprSuffix(SuffixCalculatorParser.ExprSuffixContext ctx) {
      double x = visit(ctx.expr(0));
      double y = visit(ctx.expr(1));

      switch (ctx.op.getText()) {
         case "*":
            return x * y;
         case "/":
            return x / y;
         case "+":
            return x + y;
         case "-":
            return x - y;
      }

      System.exit(1);
      return null;
   }
}
