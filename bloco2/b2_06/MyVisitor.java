import java.util.HashMap;

public class MyVisitor extends CalculatorBaseVisitor<Integer> {

   static HashMap<String, Integer> variables = new HashMap<>();

   @Override public Integer visitStat(CalculatorParser.StatContext ctx) {
      if (ctx.expr() != null)
         System.out.println(visit(ctx.expr()));
      else
         visit(ctx.assignment());
      
      return null;
   }

   @Override public Integer visitAssignment(CalculatorParser.AssignmentContext ctx) {
      variables.put(ctx.ID().getText(), visit(ctx.expr()));
      return null;
   }

   @Override public Integer visitExprAddSub(CalculatorParser.ExprAddSubContext ctx) {
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
      }
      return result;
   }

   @Override public Integer visitExprParent(CalculatorParser.ExprParentContext ctx) {
      return visit(ctx.expr());
   }

   @Override public Integer visitExprUnary(CalculatorParser.ExprUnaryContext ctx) {
      int number = Integer.parseInt(ctx.Integer().getText());
      return ctx.value.getText().equals("-") ? -number : number;
   }

   @Override public Integer visitExprInteger(CalculatorParser.ExprIntegerContext ctx) {
      return Integer.parseInt(ctx.Integer().getText());
   }

   @Override public Integer visitExprMultDivMod(CalculatorParser.ExprMultDivModContext ctx) {
      int op1 = visit(ctx.expr(0));
      int op2 = visit(ctx.expr(1));
      int result = 0;
      switch(ctx.op.getText()) {
         case "*":
            result = op1*op2;
            break;
         case "/":
            result = op1/op2;
            break;
         case "%":
            result = op1%op2;
            break;
      }
      return result;
   }

   @Override public Integer visitExprID(CalculatorParser.ExprIDContext ctx) {
      String id = ctx.ID().getText();
      return variables.containsKey(id) ? variables.get(id) : null;
   }
}
