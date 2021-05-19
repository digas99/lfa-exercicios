import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.Pair;
import org.antlr.v4.runtime.tree.*;

public class QuestionsMain {
   public static void main(String[] args) {

      try {
         CharStream input = CharStreams.fromStream(new FileInputStream(new File(args[0])));
         // create a lexer that feeds off of input CharStream:
         QuestionsLexer lexer = new QuestionsLexer(input);
         // create a buffer of tokens pulled from the lexer:
         CommonTokenStream tokens = new CommonTokenStream(lexer);
         // create a parser that feeds off the tokens buffer:
         QuestionsParser parser = new QuestionsParser(tokens);
         // replace error listener:
         //parser.removeErrorListeners(); // remove ConsoleErrorListener
         //parser.addErrorListener(new ErrorHandlingListener());
         // begin parsing at program rule:
         ParseTree tree = parser.program();
         if (parser.getNumberOfSyntaxErrors() == 0) {

            MyVisitor visitor = new MyVisitor();
            visitor.visit(tree);

            HashMap<String, List<Pair<String, Integer>>> questions = visitor.getQuestions(args[1]);
            Object[] questionsIDs = questions.keySet().toArray();
            List<Pair<String, Integer>> question = questions.get(questionsIDs[rand(0, questionsIDs.length)]);
            // show question title
            System.out.println("- "+question.get(0).a.replaceAll("\"", ""));

            List<Pair<String, Integer>> correctAnswers = question.stream().filter(ans -> ans.b == 100).collect(Collectors.toList());
            List<Pair<String, Integer>> wrongAnswers = question.stream().filter(ans -> ans.b == 0).collect(Collectors.toList());
            List<Pair<String,Integer>> finalAnswers = new ArrayList<>();
            finalAnswers.add(correctAnswers.get(rand(0, correctAnswers.size())));
            Collections.shuffle(wrongAnswers);

            for (int i = 0; i < Integer.parseInt(args[2])-1; i++) {
               finalAnswers.add(wrongAnswers.get(i));
            }
            
            Collections.shuffle(finalAnswers);

            char index = 97;
            for (Pair<String,Integer> ans : finalAnswers) {
               System.out.println((index++)+") "+ans.a);   
            }
         }
      }
      catch(IOException e) {
         e.printStackTrace();
         System.exit(1);
      }
      catch(RecognitionException e) {
         e.printStackTrace();
         System.exit(1);
      }
   }

   static private int rand(int min, int max) {
      return (int) (Math.random() * max) + min;
   }
}
