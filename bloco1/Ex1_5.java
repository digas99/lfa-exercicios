import java.util.*;
import java.io.*;
import static java.lang.System.*;

public class Ex1_5 {
	static HashMap<String, Double> values = new HashMap<>();
	
	public static void main(String[] args) {
		Scanner rd = new Scanner(System.in);
		String exp;
		String[] vals, right;
		while (true) {
			exp = rd.nextLine();
			vals = exp.split(" ");

			// if only one value was written
			if (vals.length == 1) {
				// confirm if it is a variable
				if (!isNumber(vals[0])) {
					// check if the variable exists, and then print its value
					if (values.containsKey(vals[0])) {
						out.println(values.get(vals[0]));
					}
					else
						out.println("WARNING: Variable not defined!");
				}
				// if it is a number, then it is not valid
				else {
					err.println("ERROR: Invalid expression!");
				}
			}

			// nothing can be done with only two values (i.e.: a =, a a, 12 2, + a, ...)
			else if (vals.length==2) {
				err.println("ERROR: Invalid expression!");
			}

			// if has more than two values (i.e.: 5 + 2, a / 3, a = 2, a = 2 + 3 * 6, ...)
			else {
				// if the second value is = (i.e.: a = etc..)
				if (vals[1].equals("=")) {
					// check if on the left there is only variables
					if (!isNumber(vals[0])) {
						// the expression on the right side of the equals sign
						right = Arrays.copyOfRange(vals, 2, vals.length);
						// if the expression on the right of the equals is complex, then call the function calculate()
						if (right.length > 1)
							values.put(vals[0], calculate(right));
						// if it is just one value, as in n = 2, then there is no need for calculations
						else
							values.put(vals[0], checkIfVariable(right[0]));
					}
					else
						err.println("ERROR: Invalid expression!");
				}
				// if the second value is not an equals, than just make the normal calculation of the entire input
				else {
					if (isOperator(vals[1])) {
						out.println(calculate(vals));
					}
					else {
						err.println("ERROR: Invalid expression!");
					}
				}
			}
			out.println("");
		}
	}

	// This function calculates expressions, giving priority to '*' and '/' over '+' and '-'
	public static double calculate(String[] expr) {
		// priorityOpVal is the value that is being calculated while on priority (i.e.: in 2 + 6 * 3, priorityOpVal = 6 * 3 = 18)
		double number, firstNumber, priorityOpVal = 1, result = 0;
		String operator, nextOp, savedOp = "";
		boolean last, inPriorityOp = false;

		firstNumber = checkIfVariable(expr[0]);
		
		// check which variable should take the first number in the expression
		// if the next operator is '*' or '/', than it should be in priority
		if (expr[1].equals("*") || expr[1].equals("/")) {
			priorityOpVal = firstNumber;
			inPriorityOp = true;
		}
		else
			result = firstNumber;

		// iterates the expression by 2 steps, always landing on a number
		// this way, the i is the number and the i-1 is the operator that will affect it
		for (int i=2; i<expr.length; i+=2) {
			// checks for last position
			last = (i == expr.length-1);

			// initialization of variables
			number = checkIfVariable(expr[i]);
			operator = expr[i-1];
			if (!last)
				nextOp = expr[i+1];
			else
				nextOp = "";

			// if next operator is '*' or '/', then we're entering in priority calculations
			if (nextOp.equals("*") || nextOp.equals("/")) {
				// if we were not in priority already before than it is a situation like this: a + b * c
				if (!inPriorityOp) {
					// save the operator so that we will add everything so far with all the priority calculations after
					// in a + b * c, the saved operator is '+', and when we calculate b * c, then we will sum it to a
					savedOp = operator;
					priorityOpVal = number;
					inPriorityOp = true;
				}
				// if we are already in priority calculations
				// in: a + b * c * d, if we are currently at 'c', then we have already entered priority calculations
				else {
					// because we are in priority calculations, then we update the variable priorityOpVal, and not result
					priorityOpVal = calc(priorityOpVal, operator, number);
				}
			}

			// if next operator is '+' or '-' or '', then we now for sure that we are exiting priority calculations
			else if (nextOp.equals("+") || nextOp.equals("-") || nextOp.equals("")) {
				// if last calculation was made in priority calculations
				if (inPriorityOp) {
					// check if we have a saved operator
					if (savedOp != "")
						result = calc(result, savedOp, calc(priorityOpVal, operator, number));
					else
						result = calc(priorityOpVal, operator, number);
					inPriorityOp = false;
					priorityOpVal = 1;
				}
				else {
					result = calc(result, operator, number);
				}
			}
			
			else {
				err.println("ERROR: Invalid operator!");
				return Double.NaN;
			}
		}
		return result;
	}

	public static double calc(double a, String op, double b) {
		switch (op) {
			case "+":
				return a+b;
			case "-":
				return a-b;
			case "*":
				return a*b;
			case "/":
				return a/b;
			default:
				err.println("ERROR: Invalid Operator!");
				return Double.NaN;
		}
	}

	public static boolean isNumber(String n) {
		try {
			Double.parseDouble(n);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public static boolean isOperator(String s) {
		if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/"))
			return true;
		return false;
	}
	
	// transforms a string in a double, even if it is a variable
	public static double checkIfVariable(String s) {
		if (isNumber(s))
			return Double.parseDouble(s);
		// else it is a variable
		else {
			if (values.containsKey(s))
				return values.get(s);
			else {
				err.println("ERROR: Variable "+s+" might have not been initialized!\nThe variable is now NaN!");
				return Double.NaN;
			}
		}

	}
}
