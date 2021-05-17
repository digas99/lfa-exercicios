import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.misc.Pair;

public class MyVisitor extends QuestionsBaseVisitor<String> {

   HashMap<String, HashMap<String, List<Pair<String, Integer>>>> struct = new HashMap<>();

   public HashMap<String, HashMap<String, List<Pair<String, Integer>>>> getData() {
      return struct;
   }

   public HashMap<String, List<Pair<String, Integer>>> getQuestions(String groupID) {
      return struct.get(groupID);
   }
   
   @Override public String visitProgram(QuestionsParser.ProgramContext ctx) {
      ctx.question().forEach(question -> System.out.println("Loading "+visit(question)+"..."));
      System.out.println();
      return null;
   }

   @Override public String visitQuestion(QuestionsParser.QuestionContext ctx) {
      List<Pair<String, Integer>> questionData = ctx.answer().stream()
            .map(ans -> pair(visit(ans), "\""))
            .collect(Collectors.toList());
      // put the question in the beginning of the list
      questionData.add(0, new Pair<>(ctx.TEXT().getText(), -1));

      String groupID = ctx.ID(1).getText();
      if (!struct.containsKey(groupID))
         struct.put(groupID, new HashMap<String, List<Pair<String, Integer>>>());
        
      String questionID = ctx.ID(0).getText();
      struct.get(groupID).put(questionID, questionData); 
      return questionID+"."+groupID;
   }

   @Override public String visitAnswer(QuestionsParser.AnswerContext ctx) {
      return ctx.TEXT().getText().substring(1) + ctx.Integer().getText();
   }

   private Pair<String, Integer> pair(String pair, String divider) {
      String[] split = pair.split(divider);
      return new Pair<String,Integer>(split[0], Integer.parseInt(split[1]));
   }
}
