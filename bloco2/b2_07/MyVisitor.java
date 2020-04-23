public class MyVisitor extends CalculatorBaseVisitor<String> {

   static Stack<String> ops = new Stack<>();
   static HashMap<String, Integer> variables = new HashMap<>();

   @Override public String visitProgram(CalculatorParser.ProgramContext ctx) {
      for (CalculatorParser.StatContext s : ctx.stat()) {
         if (visit(s) != null)
            System.out.println(visit(s));
      }
      return null;
   }

   @Override public String visitStatExpr(CalculatorParser.StatExprContext ctx) {
      String exp = visit(ctx.expr());
      // System.out.println(exp);
      return exp;
   }
   
   @Override public String visitStatAssign(CalculatorParser.StatAssignContext ctx) {
      return visit(ctx.assign());
   }

   @Override public String visitAssign(CalculatorParser.AssignContext ctx) {
      return ctx.ID().getText() + " = " + visit(ctx.expr());
   }

   @Override public String visitExprAddSub(CalculatorParser.ExprAddSubContext ctx) {
      //ops.push(ctx.op.getText());
      String exp = visit(ctx.expr(0)) + " " + visit(ctx.expr(1)) + " " + ctx.op.getText();
      // System.out.print(exp);
      return exp;
   }

   @Override public String visitExprParens(CalculatorParser.ExprParensContext ctx) {
      return visit(ctx.expr());
   }

   @Override public String visitExprInteger(CalculatorParser.ExprIntegerContext ctx) {
      return ctx.Integer().getText();
   }

   @Override public String visitExprBeginsWith(CalculatorParser.ExprBeginsWithContext ctx) {
      return "0 " + visit(ctx.expr()) + " " + ctx.op.getText();
   }

   @Override public String visitExprMultDivMod(CalculatorParser.ExprMultDivModContext ctx) {
      return visit(ctx.expr(0)) + " " + visit(ctx.expr(1)) + " " + ctx.op.getText();
   }

   @Override public String visitExprId(CalculatorParser.ExprIdContext ctx) {
      return ctx.ID().getText();
   }
}
