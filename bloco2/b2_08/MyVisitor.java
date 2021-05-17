import java.util.HashMap;

public class MyVisitor extends CalculatorBaseVisitor<String> {

   @Override public String visitProgram(CalculatorParser.ProgramContext ctx) {
      if (ctx.stat() != null)
         ctx.stat().forEach(stat -> System.out.println(visit(stat)));
      return null;
   }

   @Override public String visitStat(CalculatorParser.StatContext ctx) {
      return ctx.assignment() != null ? visit(ctx.assignment()) : visit(ctx.expr());
   }

   @Override public String visitAssignment(CalculatorParser.AssignmentContext ctx) {
      return ctx.ID().getText() + " = " + visit(ctx.expr());
   }

   @Override public String visitExprAddSub(CalculatorParser.ExprAddSubContext ctx) {
      return visit(ctx.expr(0)) + " " + visit(ctx.expr(1)) + " " + ctx.op.getText();
   }

   @Override public String visitExprParent(CalculatorParser.ExprParentContext ctx) {
      return visit(ctx.expr());
   }

   @Override public String visitExprUnary(CalculatorParser.ExprUnaryContext ctx) {
      return "!"+ctx.value.getText()+" "+ctx.Integer().getText();
   }

   @Override public String visitExprInteger(CalculatorParser.ExprIntegerContext ctx) {
      return ctx.Integer().getText();
   }

   @Override public String visitExprID(CalculatorParser.ExprIDContext ctx) {
      return ctx.ID().getText();
   }

   @Override public String visitExprMultDivMod(CalculatorParser.ExprMultDivModContext ctx) {
      return visit(ctx.expr(0)) + " " + visit(ctx.expr(1)) + " " + ctx.op.getText();
   }
}
