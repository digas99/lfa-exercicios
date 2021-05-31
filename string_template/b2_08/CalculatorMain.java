import java.io.FileWriter;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.stringtemplate.v4.*;

public class CalculatorMain {
   public static void main(String[] args) throws Exception {
      // check for file name
      if (args.length < 1) {
         System.out.println("RUN: java CalculatorMain filename");
         System.exit(1);
      }

      // create a CharStream that reads from standard input:
      CharStream input = CharStreams.fromStream(System.in);
      // create a lexer that feeds off of input CharStream:
      CalculatorLexer lexer = new CalculatorLexer(input);
      // create a buffer of tokens pulled from the lexer:
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      // create a parser that feeds off the tokens buffer:
      CalculatorParser parser = new CalculatorParser(tokens);
      // replace error listener:
      //parser.removeErrorListeners(); // remove ConsoleErrorListener
      //parser.addErrorListener(new ErrorHandlingListener());
      // begin parsing at program rule:
      ParseTree tree = parser.program();
      if (parser.getNumberOfSyntaxErrors() == 0) {
         // print LISP-style tree:
         // System.out.println(tree.toStringTree(parser));
         String fileName = args[0];
         CalculatorCompiler compiler = new CalculatorCompiler(fileName);
         ST res = compiler.visit(tree);
         FileWriter writer = new FileWriter("output/"+fileName+".java");
         writer.write(res.render());
         writer.close();
      }
   }
}
