import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.antlr.v4.runtime.misc.Pair; 

public class MyVisitor extends QuestionsBaseVisitor<String> {

   private static HashMap<String, List<Pair<String, Integer>>> data = new HashMap<>();

   public HashMap<String, List<Pair<String, Integer>>> getData() {
      return data;
   }

   @Override public String visitQuestion(QuestionsParser.QuestionContext ctx) {
      String id = visit(ctx.identifier());
      String question = ctx.TEXT().getText();
      // remove first and last "
      List<Pair<String, Integer>> content = new ArrayList<>();
      question = question.substring(1, question.length()-2);
      content.add(new Pair<>(question, null));
      for (QuestionsParser.AnswersContext ans : ctx.answers()) {
         String ansWithVal = visit(ans);
         String[] splitted = ansWithVal.split("\"");
         content.add(new Pair<>(splitted[0], Integer.parseInt(splitted[1])));
      }
      data.put(id, content);
      return null;
   }

   @Override public String visitIdentifier(QuestionsParser.IdentifierContext ctx) {
      return ctx.ID().getText() + "/" + ctx.NUMBER().getText();
   }

   @Override public String visitAnswers(QuestionsParser.AnswersContext ctx) {
      String ans = ctx.TEXT().getText();
      String points = ctx.NUMBER().getText();
      // remove first "
      ans = ans.substring(1);
      return ans + points;     
   }
}
