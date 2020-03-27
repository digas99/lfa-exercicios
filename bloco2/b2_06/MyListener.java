public class MyListener extends JavaBaseListener {

   JavaParser parser;

   public MyListener(JavaParser parser) {
      this.parser = parser;
   }

   @Override public void enterImportDeclaration(JavaParser.ImportDeclarationContext ctx) {
      System.out.println(parser.getTokenStream().getText(ctx));
   }
   
   @Override public void enterClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
      System.out.println("\npublic interface I"+ctx.Identifier().getText()+" {");
   }

   @Override public void exitClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
      System.out.println("}");
   }

   @Override public void enterMethodDeclaration(JavaParser.MethodDeclarationContext ctx) {
      String type = "void";
      if (ctx.type() != null)
         type = ctx.type().getText();

      // Usage of getInputStream to get text formatation correct, not only the
      // parameters values (output will be i.e.:'String name' instead of 'Stringname')
      String params = parser.getTokenStream().getText(ctx.formalParameters());
      System.out.println("\t"+type+" "+ctx.Identifier()+params+";");
   }
}
