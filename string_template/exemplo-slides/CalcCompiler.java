import org.stringtemplate.v4.*;

public class CalcCompiler extends CalculatorBaseVisitor<ST> {

   @Override public ST visitMain(CalculatorParser.MainContext ctx) {
      ST res = templates.getInstanceOf("module");
      for (CalculatorParser.StatContext stat : ctx.stat()) {
         res.add("stat", visit(stat).render());
      }
      return res;
   }

   @Override public ST visitStat(CalculatorParser.StatContext ctx) {
      ST res = templates.getInstanceOf("print");
      res.add("stat", visit(ctx.expr()).render());
      res.add("value", ctx.expr().var);
      return res;
   }

   @Override public ST visitExprAddSub(CalculatorParser.ExprAddSubContext ctx) {
      ST res = templates.getInstanceOf("binaryOperation");
      ctx.var = newVar();
      res.add("stat", visit(ctx.e1).render());
      res.add("stat", visit(ctx.e2).render());
      res.add("type", "double");
      res.add("var", ctx.var);
      res.add("e1", ctx.e1.var);
      res.add("op", ctx.op.getText());
      res.add("e2", ctx.e2.var);
      return res;
   }

   @Override public ST visitExprParens(CalculatorParser.ExprParensContext ctx) {
      ST res = visit(ctx.expr());
      ctx.var = ctx.expr().var;
      return res;
   }

   @Override public ST visitExprNumber(CalculatorParser.ExprNumberContext ctx) {
      ST res = templates.getInstanceOf("declaration");
      ctx.var = newVar();
      res.add("type", "double");
      res.add("var", ctx.var);
      res.add("value", ctx.NUMBER().getText());
      return res;
   }

   @Override public ST visitExprMultDiv(CalculatorParser.ExprMultDivContext ctx) {
      ST res = templates.getInstanceOf("binaryOperation");
      ctx.var = newVar();
      res.add("stat", visit(ctx.e1).render());
      res.add("stat", visit(ctx.e2).render());
      res.add("type", "double");
      res.add("var", ctx.var);
      res.add("e1", ctx.e1.var);
      res.add("op", ctx.op.getText());
      res.add("e2", ctx.e2.var);
      return res;
   }

   private String newVar() {
      numVars++;
      return "v"+numVars;
   }
   private int numVars = 0;
   private STGroup templates = new STGroupFile("java.stg");
}
