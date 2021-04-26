import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class MyListener extends NumbersFormaterBaseListener {

   List<String> lines;

   public MyListener() throws IOException {
      Path path = Paths.get("numbers.txt");
      lines = Files.readAllLines(path);
   }

   static HashMap<String, String> numbers = new HashMap<>();

   @Override public void enterProg(NumbersFormaterParser.ProgContext ctx) {
      for (String line : lines) {
         String[] splitted = line.split(" - ");
         numbers.put(splitted[1], splitted[0]);
      }
   }

   @Override public void enterWord(NumbersFormaterParser.WordContext ctx) {
      String n = ctx.ID().getText();

      if (ctx.getText().charAt(ctx.getText().length()-1) == ':')
         n = ctx.getText();

      if (numbers.containsKey(n))
         System.out.print(numbers.get(n));
      else
         System.out.print(n);
   }

   @Override public void exitStat(NumbersFormaterParser.StatContext ctx) {
      System.out.println();
   }

   @Override public void exitWord(NumbersFormaterParser.WordContext ctx) {
      System.out.print(" ");
   }
}
