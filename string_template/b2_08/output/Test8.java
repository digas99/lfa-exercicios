import java.util.HashMap;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Test8 {
    static HashMap<String, String> vars = new HashMap<>();

    public static void main(String[] args) {
        String var5 = "2";
        System.out.println(var1);
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

    public static String powFrac(String f1, String pow) {
        String digits[] = makeFrac(f1).split("/");
        int res;
        if (isDigit(pow)) {
            switch(pow.charAt(0)) {
                case '-':
                res = Integer.parseInt(pow.substring(1));
                return ((int) Math.pow(Integer.parseInt(digits[1]), res)) + "/" + ((int) Math.pow(Integer.parseInt(digits[0]), res));
                default:
                res = Integer.parseInt(pow);
                return ((int) Math.pow(Integer.parseInt(digits[0]), res)) + "/" + ((int) Math.pow(Integer.parseInt(digits[1]), res));  
            }
        }
        return null;
    }

    private static String makeFrac(String num) {
        String[] digits = num.split("/");
        if (digits.length == 1) {
            return digits[0] + "/1";
        }
        return num;
    }

    private static boolean isDigit(String n) {
        try {
            Integer.parseInt(n);
        }
        catch(NumberFormatException e) {
            return false;
        }
        return true;
    }

    private static int gcd(String frac) {
        String[] digits = makeFrac(frac).split("/");
        int a, b;
        try {
            a = Integer.parseInt(digits[0]);
            b = Integer.parseInt(digits[1]);
        }
        catch(NumberFormatException e) {
            System.out.println("Expected number to reduce!");
            return 0;
        }
        return gcd(a, b);
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int tmp = b;
            b = a % b;
            a = tmp;
        }
        return a;
    }
}