import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.antlr.v4.runtime.tree.TerminalNode;

public class MyVisitor extends CalculatorBaseVisitor<String> {

   private static HashMap<String, String> vars = new HashMap<>();

   @Override public String visitPrint(CalculatorParser.PrintContext ctx) {
      String digits[] = visit(ctx.expr()).split("/");
      if (digits.length > 1) {
         if (digits[1].equals("1")) {
            System.out.println(digits[0]);
         }
         else if(digits[1].equals("0")) {
            System.out.println("ERROR");
         }
         else if (Integer.parseInt(digits[1]) < 0) {
            System.out.println("-"+digits[0]+"/"+digits[1].split("-")[1]);
         }
         else if (digits[0].equals("0")) {
            System.out.println("0");
         }
         else {
            System.out.println(digits[0]+"/"+digits[1]);
         }
      }
      else {
         System.out.println(digits[0]);
      }
      return null;
   }

   @Override public String visitAtrib(CalculatorParser.AtribContext ctx) {
      String id = ctx.ID().getText();
      String val = visit(ctx.expr());

      vars.put(id, val);

      return null;
   }

   @Override public String visitExprAddSub(CalculatorParser.ExprAddSubContext ctx) {
      String[] x =  makeFrac(visit(ctx.expr(0))).split("/");
      String[] y =  makeFrac(visit(ctx.expr(1))).split("/");
      int numerator, denominator;
      switch(ctx.op.getText()) {
         case "+":
            numerator = Integer.parseInt(x[0])*Integer.parseInt(y[1]) + Integer.parseInt(y[0])*Integer.parseInt(x[1]);
            denominator = Integer.parseInt(x[1]) * Integer.parseInt(y[1]);
            return numerator + "/" + denominator;
         case "-":
            numerator = Integer.parseInt(x[0])*Integer.parseInt(y[1]) - Integer.parseInt(y[0])*Integer.parseInt(x[1]);
            denominator = Integer.parseInt(x[1]) * Integer.parseInt(y[1]);
            return numerator + "/" + denominator;
      }

      return null;
   }

   @Override public String visitExprRead(CalculatorParser.ExprReadContext ctx) {
      Scanner rd = new Scanner(System.in);
      String val="", label="";
      for (TerminalNode id : ctx.ID()) {
         label += id.getText() + " ";
      }
      System.out.print(label+": ");
      try {
         val = Integer.toString(rd.nextInt());
      }
      catch(InputMismatchException e) {
         System.out.println("Expected number on read!");
      }
      return val;
   }

   @Override public String visitExprParens(CalculatorParser.ExprParensContext ctx) {
      return visit(ctx.expr());
   }

   @Override public String visitExprUnary(CalculatorParser.ExprUnaryContext ctx) {
      switch(ctx.op.getText()) {
         case "+": return visit(ctx.expr());
         case "-": return "-"+visit(ctx.expr());
      }
      return null;
   }

   @Override public String visitExprNumber(CalculatorParser.ExprNumberContext ctx) {
      return ctx.NUMBER().getText();
   }

   @Override public String visitExprPower(CalculatorParser.ExprPowerContext ctx) {
      String powString = visit(ctx.expr(1));
      String digits[] = makeFrac(visit(ctx.expr(0))).split("/");
      int pow;
      if (isDigit(powString)) {
         switch(powString.charAt(0)) {
            case '-':
               pow = Integer.parseInt(powString.substring(1));
               return ((int) Math.pow(Integer.parseInt(digits[1]), pow)) + "/" + ((int) Math.pow(Integer.parseInt(digits[0]), pow));
            default:
               pow = Integer.parseInt(powString);
               return ((int) Math.pow(Integer.parseInt(digits[0]), pow)) + "/" + ((int) Math.pow(Integer.parseInt(digits[1]), pow));  
         }
      }
      return null;
   }

   @Override public String visitExprId(CalculatorParser.ExprIdContext ctx) {
      String id = ctx.ID().getText();
      if (vars.containsKey(id)) {
         return vars.get(id);
      }
      System.out.println("The variable " + id + " might have not been initialized!");
      return "";
   }

   @Override public String visitExprReduce(CalculatorParser.ExprReduceContext ctx) {
      String frac = makeFrac(visit(ctx.expr()));
      int gcd = gcd(frac);
      String[] digits = frac.split("/");
      return Integer.parseInt(digits[0])/gcd + "/" + Integer.parseInt(digits[1])/gcd;
   }

   @Override public String visitExprMultDivMod(CalculatorParser.ExprMultDivModContext ctx) {
      String[] x =  makeFrac(visit(ctx.expr(0))).split("/");
      String[] y =  makeFrac(visit(ctx.expr(1))).split("/");
      switch(ctx.op.getText()) {
         case "*":
            return (Integer.parseInt(x[0]) * Integer.parseInt(y[0])) + "/" + (Integer.parseInt(x[1]) * Integer.parseInt(y[1]));
         case ":":
            return (Integer.parseInt(x[0]) * Integer.parseInt(y[1])) + "/" + (Integer.parseInt(x[1]) * Integer.parseInt(y[0]));
         }

      return null;
   }

   @Override public String visitFrac(CalculatorParser.FracContext ctx) {
      return ctx.NUMBER(0).getText() + '/' + ctx.NUMBER(1).getText(); 
   }

   private String makeFrac(String num) {
      String[] digits = num.split("/");
      if (digits.length == 1) {
         return digits[0] + "/1";
      }
      return num;
   }

   private int gcd(String frac) {
      String[] digits = makeFrac(frac).split("/");
      int a, b;
      try {
         a = Integer.parseInt(digits[0]);
         b = Integer.parseInt(digits[1]);
      }
      catch(NumberFormatException e) {
         System.out.println("Expected number to reduce!");
         return 0;
      }
      return gcd(a, b);
   }

   private int gcd(int a, int b) {
      while (b != 0) {
         int tmp = b;
         b = a % b;
         a = tmp;
      }
      return a;
   }

   private boolean isDigit(String n) {
      try {
         Integer.parseInt(n);
      }
      catch(NumberFormatException e) {
         return false;
      }
      return true;
   }
}
