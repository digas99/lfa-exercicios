import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class NumbersFormaterMain {
   public static void main(String[] args) throws Exception {
      // create a CharStream that reads from standard input:
      CharStream input = CharStreams.fromStream(System.in);
      // create a lexer that feeds off of input CharStream:
      NumbersFormaterLexer lexer = new NumbersFormaterLexer(input);
      // create a buffer of tokens pulled from the lexer:
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      // create a parser that feeds off the tokens buffer:
      NumbersFormaterParser parser = new NumbersFormaterParser(tokens);
      // replace error listener:
      //parser.removeErrorListeners(); // remove ConsoleErrorListener
      //parser.addErrorListener(new ErrorHandlingListener());
      // begin parsing at prog rule:
      ParseTree tree = parser.prog();
      if (parser.getNumberOfSyntaxErrors() == 0) {
         // print LISP-style tree:
         // System.out.println(tree.toStringTree(parser));
         ParseTreeWalker walker = new ParseTreeWalker();
         MyListener listener0 = new MyListener();
         walker.walk(listener0, tree);
      }
   }
}
