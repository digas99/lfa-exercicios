import java.util.HashMap;

public class Test5 {
    static HashMap<String, String> vars = new HashMap<>();

    public static void main(String[] args) {
        String var2 = "2";
        String var3 = "2";
        String var1 = operateFracs(var2, var3, "+");
        System.out.println(var1);
        String var5 = "4";
        String var6 = "1/2";
        String var4 = operateFracs(var5, var6, "-");
        System.out.println(var4);
        String var7 = "10/3";
        System.out.println(var7);
        String var9 = "2/14";
        String var10 = "3/4";
        String var8 = operateFracs(var9, var10, "*");
        System.out.println(var8);
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