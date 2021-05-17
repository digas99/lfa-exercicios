import java.util.stream.Collectors;
import org.antlr.v4.runtime.tree.TerminalNode;

public class Execute extends HelloBaseVisitor<String> {

   @Override public String visitProg(HelloParser.ProgContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitGreetings(HelloParser.GreetingsContext ctx) {
      System.out.println("Olá "+visit(ctx.name()));
      return null;
   }

   @Override public String visitBye(HelloParser.ByeContext ctx) {
      System.out.println("Adeus "+visit(ctx.name()));
      return null;
   }

   @Override public String visitName(HelloParser.NameContext ctx) {
      return ctx.ID().stream()
               .map(id -> id.getText())
               .collect(Collectors.joining(" "));
   }
}
