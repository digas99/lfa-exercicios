import java.util.*;

public class Ex1_1 {

	public static void main(String[] args) {
		double a, b, res=0;
		int count=0;
		String[] vals = new String[3];
		if (args.length == 3) {
			vals[0] = args[0];
			vals[1] = args[1];
			vals[2] = args[2];
		}
		if (args.length != 0 && args.length != 3) {
			System.err.println("ERRO: Inicie o programa da seguinte forma:\n$ java Ex1_1 a + b");
			System.exit(1);
		}	

		do {
			// caso o programa não tenha tido agumentos inicialmente, ou teve,
			// mas já vai na segunda iteração
			if (args.length == 0 || (args.length > 0 && count > 0)) {
				Scanner rd = new Scanner(System.in);
				System.out.print("Escreva uma operação (Ex.: 2 + 2): ");
				String input = rd.nextLine();
				vals = input.split(" ");	
			}

			// caso o programa tenha tido argumentos inicialmente, incrementar o
			// contador para que agora o utilizador dê input de uma operação
			if (args.length>0 && args.length < 4 && count<1) 
				count++;

			if (vals.length == 3) {
				if (validValues(vals)) {
					a = Double.parseDouble(vals[0]);
					b = Double.parseDouble(vals[2]);
					switch(vals[1]) {
						case "+":
							res = a + b;
							System.out.print(a+" + "+b);	
							break;
						case "-":
							res = a - b;
							System.out.print(a+" - "+b);	
							break;
						case "*":
							res = a * b;
							System.out.print(a+" * "+b);
							break;
						case "/":
							res = a / b;
							System.out.print(a+" / "+b);
							break;
					}
					System.out.printf(" = %.2f\n",res);
				}
				else {
					System.out.println("ERRO: Insira um número ou um símbolo válido (+, -, *, /]!");
				}
			}
			else {
				System.err.println("ERRO: Tem de fazer uma operação do tipo 'a / b'!"); 

			}
		}
		while(true);	

	}

	public static boolean validValues(String[] array) {
		String[] symbols = {"+", "-", "*", "/"};
		for (int i=0; i<array.length; i++) {
			if (i == 0 || i == 2) {
				try {
					double d = Double.parseDouble(array[i]);	
				} catch(NumberFormatException e) {
					return false;
				}

			}
			if (i == 1) {
				if (!inArray(array[i], symbols)) 
					return false;
			}
		}
		
		return true;
	}

	public static boolean inArray(String s, String[] array) {
		for (String arr:array) {
			if (s.equals(arr))
				return true;
		}
		return false;
	}
}
