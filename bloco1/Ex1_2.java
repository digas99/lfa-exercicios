import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Ex1_2 {
	
	static Map<String, Double> vars = new HashMap<>();
	public static void main(String[] args) {
		Scanner rd = new Scanner(System.in);
		String[] expr;
		int exprSize;
		Double result, resultToSave;
		double op2;
		String operand, expr0;
		do {
			result = null;
			do {
				expr = rd.nextLine().split(" ");
				exprSize = expr.length;
				expr0 = expr[0];
				if (exprSize == 1) {
					if (!isNumber(expr0))
						result = vars.get(expr0);
				}
				else {
					operand = expr[1];
					op2 = valueDecision(expr[2]);
					if (!operand.equals("="))
						result = Ex1_1.operate(valueDecision(expr0), operand, op2);
					else {
						resultToSave = op2;
						if (exprSize == 5) {
							resultToSave = Ex1_1.operate(resultToSave, expr[3], valueDecision(expr[4]));
						}
						vars.put(expr0, resultToSave);
					}
				}
			} while (result == null || exprSize > 5);
			System.out.println(result);
		} while(true);
	}

	private static double valueDecision(String value) {
		return isNumber(value) ? Double.parseDouble(value) : vars.get(value);
	}

	public static boolean isNumber(String value) {
		try {
			Double.parseDouble(value);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}
