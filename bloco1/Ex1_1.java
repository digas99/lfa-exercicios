import java.util.Scanner;

public class Ex1_1 {

	public static void main(String[] args) {
		Scanner rd = new Scanner(System.in);
		String[] expr;
		Double result;
		do {
			do {
				expr = rd.nextLine().split(" ");
				result = operate(Integer.parseInt(expr[0]), expr[1], Integer.parseInt(expr[2]));
			} while (result == null);
			System.out.println(result);
		} while (true);
	}

	public static Double operate(double op1, String operand, double op2) {
		Double result = null;
		switch (operand) {
			case "+":
				result = op1+op2;
				break;
			case "-":
				result = op1-op2;
				break;		
			case "*":
				result = op1*op2;
				break;
			case "/":
				result = op1/op2;
				break;				
			default:
				System.err.println("Invalid operator!");
		}
		return result;
	}
}