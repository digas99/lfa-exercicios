import org.stringtemplate.v4.*;

public class CalculatorCompiler extends CalculatorBaseVisitor<ST> {

   private STGroup templates = new STGroupFile("javaTemplates.stg");
   private int numVars = 0;
   private String newVar() {
      numVars++;
      return "var"+numVars;
   }
   private String file;

   public CalculatorCompiler(String file) {
      this.file = file;
   }

   @Override public ST visitStat(CalculatorParser.StatContext ctx) {
      if (ctx.print() != null) return visit(ctx.print());
      if (ctx.atrib() != null) return visit(ctx.atrib());
      return null;
   }

   @Override public ST visitProgram(CalculatorParser.ProgramContext ctx) {
      ST module = templates.getInstanceOf("module");
      module.add("name", file);
      for (CalculatorParser.StatContext stat : ctx.stat()) {
         module.add("stat", visit(stat).render());
      }
      return module;
   }

   @Override public ST visitPrint(CalculatorParser.PrintContext ctx) {
      ST print = templates.getInstanceOf("print");
      print.add("stat", visit(ctx.expr()).render());
      print.add("text", ctx.expr().var);
      return print;
   }

   @Override public ST visitAtrib(CalculatorParser.AtribContext ctx) {
      ST atrib = templates.getInstanceOf("declaration");
      atrib.add("id", ctx.ID().getText());
      atrib.add("stat", visit(ctx.expr()).render());
      atrib.add("value", ctx.expr().var);
      return atrib;
   }

   @Override public ST visitExprFrac(CalculatorParser.ExprFracContext ctx) {
      ST res = templates.getInstanceOf("string_temp_declaration_literal");
      ctx.var = newVar();
      res.add("var", ctx.var);
      res.add("value", ctx.NUMBER(0).getText() + "/" + ctx.NUMBER(1).getText());
      return res;
   }

   @Override public ST visitExprAddSub(CalculatorParser.ExprAddSubContext ctx) {
      ST res = templates.getInstanceOf("binary_operation");
      ctx.var = newVar();
      res.add("stat", visit(ctx.e1).render());
      res.add("stat", visit(ctx.e2).render());
      res.add("type", "String");
      res.add("var", ctx.var);
      res.add("e1", ctx.e1.var);
      res.add("op", ctx.op.getText());
      res.add("e2", ctx.e2.var);
      return res;
   }

   @Override public ST visitExprRead(CalculatorParser.ExprReadContext ctx) {
      ST read = templates.getInstanceOf("read");
      ctx.var = newVar();
      read.add("var", ctx.var);
      read.add("label", ctx.STRING().getText());
      read.add("source", "System.in");
      return read;
   }

   @Override public ST visitExprParens(CalculatorParser.ExprParensContext ctx) {
      ST tmp = visit(ctx.expr());
      ctx.var = ctx.expr().var;
      return tmp;
   }

   @Override public ST visitExprUnary(CalculatorParser.ExprUnaryContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitExprNumber(CalculatorParser.ExprNumberContext ctx) {
      ST res = templates.getInstanceOf("string_temp_declaration_literal");
      ctx.var = newVar();
      res.add("var", ctx.var);
      res.add("value", ctx.NUMBER().getText());
      return res;
   }

   @Override public ST visitExprPower(CalculatorParser.ExprPowerContext ctx) {
      ST pow = templates.getInstanceOf("pow_operation");
      ctx.var = newVar();
      pow.add("stat", visit(ctx.e).render());
      pow.add("stat", visit(ctx.pow).render());
      pow.add("type", "String");
      pow.add("var", ctx.var);
      pow.add("e", ctx.e.var);
      pow.add("pow", ctx.pow.var);
      return visitChildren(ctx);
   }

   @Override public ST visitExprId(CalculatorParser.ExprIdContext ctx) {
      ST res = templates.getInstanceOf("get_from_map");
      ctx.var = newVar();
      res.add("id", ctx.ID().getText());
      res.add("var", ctx.var);
      return res;
   }

   @Override public ST visitExprReduce(CalculatorParser.ExprReduceContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitExprMultDivMod(CalculatorParser.ExprMultDivModContext ctx) {
      ST res = templates.getInstanceOf("binary_operation");
      ctx.var = newVar();
      res.add("stat", visit(ctx.e1).render());
      res.add("stat", visit(ctx.e2).render());
      res.add("type", "String");
      res.add("var", ctx.var);
      res.add("e1", ctx.e1.var);
      res.add("op", ctx.op.getText());
      res.add("e2", ctx.e2.var);
      return res;
   }
}
