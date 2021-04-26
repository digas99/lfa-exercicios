import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

public class Ex1_3 {
	
	public static void main(String[] args) {
		Stack<Double> stack;
		Scanner rd = new Scanner(System.in);
		Double result;
		do {
			stack = new Stack<>();
			result = null;
			do {
				for (String val : rd.nextLine().split(" ")) {
					if (Ex1_2.isNumber(val))
						stack.add(Double.parseDouble(val));
					else {
						try {
							result = Ex1_1.operate(stack.pop(), val, stack.pop());
						} catch (EmptyStackException e) {
							System.err.println("Bad format! Please use Reverse Polish Notation.");
							break;
						}
						if (result != null)
							stack.push(result);
						else
							break;
					}
					System.out.println(stack);
				}
			} while (result == null);
		} while (true);
	}
}
