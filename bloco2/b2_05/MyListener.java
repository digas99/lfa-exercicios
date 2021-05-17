import java.util.HashMap;

public class MyListener extends NumbersBaseListener {

   private HashMap<String, String> numberAssoc = new HashMap<>();

   public HashMap<String, String> getMap() {
      return numberAssoc;
   }

   @Override public void enterLine(NumbersParser.LineContext ctx) {
      numberAssoc.put(ctx.WORD().getText(), ctx.NUMBER().getText());
   }
}
