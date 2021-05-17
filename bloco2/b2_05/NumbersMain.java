import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.stream.Stream;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class NumbersMain {
   public static void main(String[] args) throws IOException {
      // create a CharStream that reads from standard input:
      CharStream input = CharStreams.fromStream(System.in);
      // create a lexer that feeds off of input CharStream:
      NumbersLexer lexer = new NumbersLexer(input);
      // create a buffer of tokens pulled from the lexer:
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      // create a parser that feeds off the tokens buffer:
      NumbersParser parser = new NumbersParser(tokens);
      // replace error listener:
      //parser.removeErrorListeners(); // remove ConsoleErrorListener
      //parser.addErrorListener(new ErrorHandlingListener());
      // begin parsing at prog rule:
      ParseTree tree = parser.file();
      if (parser.getNumberOfSyntaxErrors() == 0) {
         // print LISP-style tree:
         // Systejam.out.println(tree.toStringTree(parser));
         ParseTreeWalker walker = new ParseTreeWalker();
         MyListener listener = new MyListener();
         walker.walk(listener, tree);

         HashMap<String, String> numberAssoc = listener.getMap();
         
         Files.readAllLines(Paths.get(args[0])).stream()
            .forEach(line -> {
               Stream.of(line.split("\\s|-"))
            .forEach(value -> System.out.print(numberAssoc.containsKey(value) ? numberAssoc.get(value)+" " : value+" "));
               System.out.println();
         });
      }
   }
}
