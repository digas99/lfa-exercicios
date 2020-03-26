public class MyVisitor extends CalculatorBaseVisitor<Integer> {

   @Override public Integer visitStat(CalculatorParser.StatContext ctx) {
      System.out.println(visit(ctx.expr()));
      return null;
   }

   @Override public Integer visitExprAddSub(CalculatorParser.ExprAddSubContext ctx) {
      int x = visit(ctx.expr(0));
      int y = visit(ctx.expr(1));
      
      switch(ctx.op.getText()) {
         case "+":
            return x + y;
         case "-":
            return x - y;
      }

      return null;
   }

   @Override public Integer visitExprParens(CalculatorParser.ExprParensContext ctx) {
      return visit(ctx.expr());
   }

   @Override public Integer visitExprInteger(CalculatorParser.ExprIntegerContext ctx) {
      return Integer.parseInt(ctx.Integer().getText());
   }

   @Override public Integer visitExprMultDivMod(CalculatorParser.ExprMultDivModContext ctx) {
      int x = visit(ctx.expr(0));
      int y = visit(ctx.expr(1));
      
      switch(ctx.op.getText()) {
         case "*":
            return x * y;
         case "/":
            return x / y;
         case "%":
            return x % y;
      }

      return null;
   }

   @Override public Integer visitExprBeginsWithMinus(CalculatorParser.ExprBeginsWithMinusContext ctx) {
      return -(Integer.parseInt(ctx.Integer().getText()));
   }

   @Override public Integer visitExprBeginsWithPlus(CalculatorParser.ExprBeginsWithPlusContext ctx) {
      return Integer.parseInt(ctx.Integer().getText());
   }
}
