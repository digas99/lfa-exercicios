import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.antlr.v4.runtime.tree.TerminalNode;

public class MyVisitor extends SetCalculatorBaseVisitor<String> {

   HashMap<String, String> sets = new HashMap<>();

   @Override public String visitProgram(SetCalculatorParser.ProgramContext ctx) {
      for (SetCalculatorParser.StateContext state : ctx.state()) {
         System.out.println("result: " + visit(state));
      }
      return null;
   }

   @Override public String visitAtrib(SetCalculatorParser.AtribContext ctx) {
      String id = ctx.UPPERCASE().getText();
      String set = visit(ctx.expr());
      sets.put(id, set);
      return set;
   }

   @Override public String visitExprComplement(SetCalculatorParser.ExprComplementContext ctx) {
      List<String> set1 = parseSet(visit(ctx.expr(0)));
      List<String> set2 = parseSet(visit(ctx.expr(1)));
      List<String> finalSet = new ArrayList<>(set1);
      for (String value : set2) {
         if (!finalSet.contains(value))
            finalSet.add(value);
      }

      for (String value1 : set1) {
         for (String value2 : set2) {
            if (value2.equals(value1)) {
               if (finalSet.contains(value2))
                  finalSet.remove(value2);
            }
         }
      }
      return stringifySet(finalSet);
   }

   @Override public String visitExprParens(SetCalculatorParser.ExprParensContext ctx) {
      return visit(ctx.expr());
   }

   @Override public String visitExprUnion(SetCalculatorParser.ExprUnionContext ctx) {
      List<String> set1 = parseSet(visit(ctx.expr(0)));
      List<String> set2 = parseSet(visit(ctx.expr(1)));
      for (String value : set2) {
         if (!set1.contains(value))
            set1.add(value);
      }
      return stringifySet(set1);
   }

   @Override public String visitExprIntersection(SetCalculatorParser.ExprIntersectionContext ctx) {
      List<String> set1 = parseSet(visit(ctx.expr(0)));
      List<String> set2 = parseSet(visit(ctx.expr(1)));
      List<String> finalSet = new ArrayList<>();
      for (String value1 : set1) {
         for (String value2 : set2) {
            if (value2.equals(value1)) {
               if (!finalSet.contains(value2))
                  finalSet.add(value2);
            }
         }
      }
      return stringifySet(finalSet);
   }

   @Override public String visitExprID(SetCalculatorParser.ExprIDContext ctx) {
      String id = ctx.UPPERCASE().getText();
      String set = sets.containsKey(id) ? sets.get(id) : null;
      return set;
   }

   @Override public String visitExprSet(SetCalculatorParser.ExprSetContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitSet(SetCalculatorParser.SetContext ctx) {
      List<String> wordsList = new ArrayList<>();
      List<TerminalNode> words = ctx.WORD();
      for (int i = 0; i < words.size(); i++) {
         if (!wordsList.contains(words.get(i).getText())) {
            wordsList.add(words.get(i).getText());
         }
      }
      return stringifySet(wordsList);
   }

   public List<String> parseSet(String set) {
      set = set.substring(1, set.length()-1);
      String[] values = set.split(",");
      List<String> tmp = new ArrayList<>();
      for (String value : values) {
         // remove all white spaces
         tmp.add(value.replaceAll("\\s+",""));
      }
      return tmp;
   }

   public String stringifySet(List<String> set) {
      String list = set.toString();
      return "{" + list.substring(1, list.length()-1) + "}";
   }
}
