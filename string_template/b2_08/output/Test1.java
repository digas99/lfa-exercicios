import java.util.HashMap;

public class Test1 {
    static HashMap<String, Double> vars = new HashMap<>();

    public static void main(String[] args) {
        double var1 = 32;
        vars.put("a", var1);
        double var2 = 0;
        if (vars.containsKey("a")) {
            var2 = vars.get("a");
        }
        System.out.println(var2);
    }
}