import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class FractionsCalculatorMain {
   public static void main(String[] args) {
      try {
         CharStream input = args.length > 0 ? CharStreams.fromStream(new FileInputStream(new File(args[0]))) : CharStreams.fromStream(System.in);
         // create a lexer that feeds off of input CharStream:
         FractionsCalculatorLexer lexer = new FractionsCalculatorLexer(input);
         // create a buffer of tokens pulled from the lexer:
         CommonTokenStream tokens = new CommonTokenStream(lexer);
         // create a parser that feeds off the tokens buffer:
         FractionsCalculatorParser parser = new FractionsCalculatorParser(tokens);
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
}
