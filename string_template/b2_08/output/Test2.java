import java.util.HashMap;

public class Test2 {
    static HashMap<String, String> vars = new HashMap<>();

    public static void main(String[] args) {
        String var1 = "1/4";
        vars.put("a", var1);
        String var2 = "3";
        vars.put("b", var2);
        String var3 = vars.containsKey("a") ? vars.get("a") : "Variable a might have not been initialized!";
        System.out.println(var3);
        String var4 = vars.containsKey("b") ? vars.get("b") : "Variable b might have not been initialized!";
        System.out.println(var4);
        String var5 = "2/5";
        System.out.println(var5);
    }
}