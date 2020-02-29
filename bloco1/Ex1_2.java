import java.util.*;
import static java.lang.System.*;

public class Ex1_2 {

	public static void main(String[] args) {
		boolean allNumbers=false;
		double currentN, last, beforeLast;
		Stack<Double> stack = new Stack<Double>();
		Scanner rd = new Scanner(System.in);

		out.print("Insira uma expressão em notação pós-fixa\n('1 2 3 * +' = '1 + 3 * 2')\nExpressão: ");
		String s = rd.nextLine();
		String[] vals = s.split(" ");
		if (isValid(vals)) {
			for (int i = 0; i < vals.length; i++) {
				String current = vals[i];
				if (isNumber(current)) {
					currentN = Double.parseDouble(current); 
					stack.push(currentN);
				}
				else {
					last = stack.pop();
					beforeLast = stack.pop();
					stack.push(calc(vals[i], last, beforeLast));
				}
				printStack(stack);
			}	
		}
	}

	public static boolean isValid(String[] arr) {
		for (String s:arr) {
			if (!isNumber(s) && !isOperator(s))
				return false;
		}
		return true;
	}

	public static boolean isNumber(String s) {
		try {
			Double.parseDouble(s);
		} catch (NumberFormatException e) {
			return false;	
		}
		return true;
	}

	public static void printStack(Stack stack) {
		Stack<Double> newStack = (Stack<Double>)stack.clone();
		Double[] aux = new Double[stack.size()];
		int ct = 0;
		while (newStack.size() > 0) {
			aux[ct] = newStack.pop();
			ct++;
		}
		out.print("Stack: [");
		for (int i=aux.length-1; i > 0 ; i--) {
			out.print(aux[i]+", ");
		}
		out.println(aux[0]+"]");
	}

	public static double calc(String op, double a, double b) {
		switch (op) {
			case "+":
				return a+b;
			case "-":
				return a-b;
			case "*":
				return a*b;
			case "/":
				return a/b;
		}
		return a+b;
	}

	public static boolean isOperator(String s) {
		if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) 
			return true;
		return false;
	}
}