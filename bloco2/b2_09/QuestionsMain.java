import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import org.antlr.v4.runtime.misc.Pair; 
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class QuestionsMain {

   public static String identifier;
   public static int nmrAnswers;
   public static String[] alphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
   
   public static void main(String[] args) throws Exception {
      // if not sepcified a number of answers
      if (args.length == 1) {
         identifier = null;
         nmrAnswers = -1;
      }
      else if (args.length == 3) {
         identifier = args[1];
         if (!isNumber(args[2])) {
            System.out.println("Expected a number in the third argument!");
            System.out.println("How to run the program [Optional field]:\njava QuestionsMain DataBase [Identifier NumberOfAnswers]");
            System.exit(1);
         }
         nmrAnswers = Integer.parseInt(args[2]);
      }
      else {
         System.out.println("How to run the program [Optional field]:\njava QuestionsMain DataBase [Identifier NumberOfAnswers]");
         System.exit(2);
      }
      
      File file = new File(args[0]);
      try {
         InputStream stream = new FileInputStream(file);
         // create a CharStream that reads from standard input:
         CharStream input = CharStreams.fromStream(stream);
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
            // print LISP-style tree:
            // System.out.println(tree.toStringTree(parser));
            MyVisitor visitor0 = new MyVisitor();
            visitor0.visit(tree);

            if (identifier == null)
               displayAllQuestions(visitor0.getData());
            else {
               // get the questions with the identifier chosen by the user
               List<List<Pair<String, Integer>>> filteredLists = filterList(visitor0.getData(), identifier);
               // get random question from the list of questions with given identifier
               List<Pair<String, Integer>> randomQuestion = filteredLists.get(randomNumber(0, filteredLists.size()-1));
               // manage answers
               List<Pair<String, Integer>> rightAnswers = new ArrayList<>();
               List<Pair<String, Integer>> wrongAnswers = new ArrayList<>();

               // separate answers between right or wrong
               for (int i = 1; i < randomQuestion.size(); i++) {
                  if (randomQuestion.get(i).b == 100) {
                     rightAnswers.add(randomQuestion.get(i));
                  }
                  else {
                     wrongAnswers.add(randomQuestion.get(i));
                  }
               }

               // displayable answers are all the wrong answers plus one correct answer
               int displayableAnswers = wrongAnswers.size() + 1;
               if (displayableAnswers < nmrAnswers) {
                  System.out.println("The question picked only has " + displayableAnswers + " displayable answers, which is less than the " + nmrAnswers + " chosen by the user!\n(Displayable answers are all the wrong answers plus one correct answer)");
                  System.exit(4);
               }
               
               // print question
               System.out.println("- " + randomQuestion.get(0).a);

               // choose answers randomly according to the number os answers given by the user
               List<Pair<String, Integer>> allChosenAnswers = new ArrayList<>();
               allChosenAnswers.add(rightAnswers.get(randomNumber(0, rightAnswers.size()-1)));
               for (int i = 0; i < nmrAnswers-1; i++) {
                  int pos = randomNumber(0, wrongAnswers.size()-1);
                  allChosenAnswers.add(wrongAnswers.get(pos));
                  wrongAnswers.remove(pos);
               }

               // display all chosen answers
               for (int j = 0; j < nmrAnswers; j++) {
                  int pos = randomNumber(0, allChosenAnswers.size()-1);
                  printAnswer(allChosenAnswers.get(pos), alphabet[j], false);
                  allChosenAnswers.remove(pos);
               }
            }
         }
      }
      catch (FileNotFoundException e) {
         System.out.println("First argument must be a valid file! Ex.: test.question");
         System.exit(3);
      }
   }

   public static void printAnswer(Pair<String, Integer> answer, String letter, boolean showPoints) {
      String pointsString = showPoints ? " [" + answer.b + "]" : "";
      System.out.println("\t" + letter + ") " + answer.a + pointsString + ";");
   }

   public static List<List<Pair<String, Integer>>> filterList(HashMap<String, List<Pair<String, Integer>>> data, String identifier) {
      List<List<Pair<String, Integer>>> filtered = new ArrayList<>();
      for (Entry<String, List<Pair<String, Integer>>> entry : data.entrySet()) {
         if (entry.getKey().split("/")[0].equals(identifier)) {
            filtered.add(entry.getValue());
         }
      }
      return filtered;
   } 

   public static void displayAllQuestions(HashMap<String, List<Pair<String, Integer>>> data) {
      for (Entry<String, List<Pair<String, Integer>>> entry : data.entrySet()) {
         List<Pair<String, Integer>> answers = entry.getValue();
         // print question
         System.out.println("- " + answers.get(0).a); 
         // print answers
         for (int i = 1; i < answers.size(); i++) {
            System.out.println("\t" + i + ") " + answers.get(i).a + " [" + answers.get(i).b + "]");
         }
         System.out.println();
      }
   }

   public static boolean isNumber(String s) {
      try {
         Integer.parseInt(s);
      }
      catch(NumberFormatException e) {
         return false;
      }
      return true;
   }

   public static int randomNumber(int min, int max) {
      return (int)(Math.random()*((max-min)+1))+min;
   }
}
