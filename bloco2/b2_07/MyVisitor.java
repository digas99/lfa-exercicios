public class MyVisitor extends CalculatorBaseVisitor<String> {

   @Override public String visitProgram(CalculatorParser.ProgramContext ctx) {
      for (CalculatorParser.StatContext s : ctx.stat()) {
         if (visit(s) != null)
            System.out.println(visit(s));
      }
      return null;
   }

   @Override public String visitStatExpr(CalculatorParser.StatExprContext ctx) {
      return visit(ctx.expr());
   }
   
   @Override public String visitStatAssign(CalculatorParser.StatAssignContext ctx) {
      return visit(ctx.assign());
   }

   @Override public String visitAssign(CalculatorParser.AssignContext ctx) {
      return ctx.ID().getText() + " = " + visit(ctx.expr());
   }

   @Override public String visitExprAddSub(CalculatorParser.ExprAddSubContext ctx) {
      return visit(ctx.expr(0)) + " " + visit(ctx.expr(1)) + " " + ctx.op.getText();
   }

   @Override public String visitExprParens(CalculatorParser.ExprParensContext ctx) {
      return visit(ctx.expr());
   }

   @Override public String visitExprInteger(CalculatorParser.ExprIntegerContext ctx) {
      return ctx.Integer().getText();
   }

   @Override public String visitExprUnary(CalculatorParser.ExprUnaryContext ctx) {
      return "0 " + visit(ctx.expr()) + " " + ctx.op.getText();
   }

   @Override public String visitExprMultDivMod(CalculatorParser.ExprMultDivModContext ctx) {
      return visit(ctx.expr(0)) + " " + visit(ctx.expr(1)) + " " + ctx.op.getText();
   }

   @Override public String visitExprId(CalculatorParser.ExprIdContext ctx) {
      return ctx.ID().getText();
   }
}
