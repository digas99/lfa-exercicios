import java.util.HashMap;
import java.util.Scanner;

public class MyVisitor extends FractionsCalculatorBaseVisitor<String> {

   static HashMap<String, String> variables = new HashMap<>();

   @Override public String visitPrint(FractionsCalculatorParser.PrintContext ctx) {
      System.out.println(visit(ctx.expr()));
      return null;
   }

   @Override public String visitAssignment(FractionsCalculatorParser.AssignmentContext ctx) {
      variables.put(ctx.ID().getText(), visit(ctx.expr()));
      return null;
   }

   @Override public String visitExprFrac(FractionsCalculatorParser.ExprFracContext ctx) {
      return visit(ctx.fraction());
   }

   @Override public String visitExprAddSub(FractionsCalculatorParser.ExprAddSubContext ctx) {
      return calcFrac(visit(ctx.expr(0)), ctx.op.getText(), visit(ctx.expr(1)));
   }

   @Override public String visitExprParent(FractionsCalculatorParser.ExprParentContext ctx) {
      return visit(ctx.expr());
   }

   @Override public String visitExprUnary(FractionsCalculatorParser.ExprUnaryContext ctx) {
      String num = "";
      String op = ctx.value.getText();
      if (ctx.Integer() != null) 
         num = ctx.Integer().getText();
      else
         num = visit(ctx.fraction());
      return op.equals("-") ? "-"+num : num;
   }

   @Override public String visitExprInteger(FractionsCalculatorParser.ExprIntegerContext ctx) {
      return ctx.Integer().getText();
   }

   @Override public String visitExprID(FractionsCalculatorParser.ExprIDContext ctx) {
      String id = ctx.ID().getText();
      return variables.containsKey(id) ? variables.get(id) : null;
   }

   @Override public String visitExprMultDivMod(FractionsCalculatorParser.ExprMultDivModContext ctx) {
      return calcFrac(visit(ctx.expr(0)), ctx.op.getText(), visit(ctx.expr(1)));
   }

   @Override public String visitExprRead(FractionsCalculatorParser.ExprReadContext ctx) {
      System.out.print(ctx.TEXT().getText()+": ");
      return (new Scanner(System.in)).nextLine();
   }

   @Override public String visitExprPower(FractionsCalculatorParser.ExprPowerContext ctx) {
      String[] num = visit(ctx.expr(0)).split("/");
      int exp = Integer.parseInt(ctx.value != null ? ctx.value.getText() + visit(ctx.expr(1)) : visit(ctx.expr(1)));
      if (num.length == 1)
         return Double.toString(Math.pow(Integer.parseInt(num[0]), exp));
      return exp < 0 ? Double.toString(Math.pow(Integer.parseInt(num[0]), exp)/Math.pow(Integer.parseInt(num[1]), exp)) : ((int) Math.pow(Integer.parseInt(num[0]), exp)) + "/" + ((int) Math.pow(Integer.parseInt(num[1]), exp));
   }

   @Override public String visitExprReduce(FractionsCalculatorParser.ExprReduceContext ctx) {
      String[] frac = frac(visit(ctx.expr())).split("/");
      int num = Integer.parseInt(frac[0]);
      int den = Integer.parseInt(frac[1]);
      int gcd = gcd(num, den);
      return num/gcd + "/" + den/gcd;
   }

   @Override public String visitFraction(FractionsCalculatorParser.FractionContext ctx) {
      return ctx.Integer(0).getText() + "/" + ctx.Integer(1).getText();
   }
   
   private String calcFrac(String f1, String op, String f2) {
      String[] x = frac(f1).split("/");
      String[] y = frac(f2).split("/");
      int xNum = Integer.parseInt(x[0]);
      int xDen = Integer.parseInt(x[1]);
      int yNum = Integer.parseInt(y[0]);
      int yDen = Integer.parseInt(y[1]);

      int[] result = new int[2];
      switch(op) {
         case "*":
            result[0] = xNum*yNum;
            result[1] = xDen*yDen;
            break;
         case ":":
            result[0] = xNum*yDen;
            result[1] = xDen*yNum;  
            break;
         case "+":
            if (xDen == yDen) {
               result[0] = xNum+yNum;
               result[1] = xDen; 
            }
            else {
               result[0] = xNum*yDen+yNum*xDen;
               result[1] = xDen*yDen;
            }
            break;
         case "-":
            if (xDen == yDen) {
               result[0] = xNum-yNum;
               result[1] = xDen; 
            }
            else {
               result[0] = xNum*yDen-yNum*xDen;
               result[1] = xDen*yDen;
            }
            break;
      }
  
      // specific cases
      if (result[0] == 0)
         return "0";
        
      if (result[1] == 1)
         return Integer.toString(result[0]);

      return result[0] + "/" + result[1];
   }

   private int gcd(String frac) {
      String[] digits = frac(frac).split("/");
      return gcd(Integer.parseInt(digits[0]), Integer.parseInt(digits[1]));
   }

   private int gcd(int a, int b) {
      while (b != 0) {
         int tmp = b;
         b = a % b;
         a = tmp;
      }
      return a;
   }
   private String frac(String value) {
      String[] valueSplit = value.split("/");
      if (valueSplit.length == 2)
         return value;
      
      return value+"/1";
   }
}
