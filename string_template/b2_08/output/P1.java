import java.util.HashMap;
import java.util.Scanner;
import java.util.InputMismatchException;

public class P1 {
    static HashMap<String, String> vars = new HashMap<>();

    public static void main(String[] args) {
        String var1 = "1/4";
        System.out.println(var1);
        String var2 = "3";
        System.out.println(var2);
        String var3 = "3/4";
        vars.put("x", var3);
        String var4 = vars.containsKey("x") ? vars.get("x") : "Variable x might have not been initialized!";
        System.out.println(var4);
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