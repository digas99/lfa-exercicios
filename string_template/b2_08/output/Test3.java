import java.util.HashMap;

public class Test3 {
    static HashMap<String, String> vars = new HashMap<>();

    public static void main(String[] args) {
        String var3 = "2";
        String var4 = "3";
        double var2 = var3 + var4;
        String var5 = "1";
        double var1 = var2 - var5;
        System.out.println(var1);
    }
}