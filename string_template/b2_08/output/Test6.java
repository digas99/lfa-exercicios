import java.util.HashMap;

public class Test6 {
    static HashMap<String, String> vars = new HashMap<>();

    public static void main(String[] args) {
        String var3 = "1";
        String var4 = "2/5";
        String var2 = operateFracs(var3, var4, "+");
        String var6 = "4";
        String var7 = "4/7";
        String var5 = operateFracs(var6, var7, "*");
        String var1 = operateFracs(var2, var5, "-");
        vars.put("abc", var1);
        String var9 = vars.containsKey("abc") ? vars.get("abc") : "Variable abc might have not been initialized!";
        String var10 = "2/4";
        String var8 = operateFracs(var9, var10, "+");
        vars.put("final", var8);
        String var11 = vars.containsKey("abc") ? vars.get("abc") : "Variable abc might have not been initialized!";
        System.out.println(var11);
        String var12 = vars.containsKey("final") ? vars.get("final") : "Variable final might have not been initialized!";
        System.out.println(var12);
    }

    public static String operateFracs(String f1, String f2, String op) {
        String[] x =  makeFrac(f1).split("/");
        String[] y =  makeFrac(f2).split("/");

        int numerator = 0;
        int denominator = 0;
        switch(op) {
            case "+":
                numerator = Integer.parseInt(x[0])*Integer.parseInt(y[1]) + Integer.parseInt(y[0])*Integer.parseInt(x[1]);
                denominator = Integer.parseInt(x[1]) * Integer.parseInt(y[1]);
                break;
            case "-":
                numerator = Integer.parseInt(x[0])*Integer.parseInt(y[1]) - Integer.parseInt(y[0])*Integer.parseInt(x[1]);
                denominator = Integer.parseInt(x[1]) * Integer.parseInt(y[1]);
                break;
            case "*":
                numerator = Integer.parseInt(x[0]) * Integer.parseInt(y[0]);
                denominator = Integer.parseInt(x[1]) * Integer.parseInt(y[1]);
                break;
            case ":":
                numerator = Integer.parseInt(x[0]) * Integer.parseInt(y[1]);
                denominator = Integer.parseInt(x[1]) * Integer.parseInt(y[0]);
                break;
        }
        return numerator + "/" + denominator;
    }

    private static String makeFrac(String num) {
        String[] digits = num.split("/");
        if (digits.length == 1) {
            return digits[0] + "/1";
        }
        return num;
    }
}