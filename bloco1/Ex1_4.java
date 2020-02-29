import java.util.*;
import static java.lang.System.*;
import java.io.*;
import java.nio.file.*;

public class Ex1_4 {

	static HashMap<String, Integer> numbers;
   static List<String> lines;
	static List<Integer> numValues = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		String[] words;
		String frase;
      Scanner rd = new Scanner(System.in);
      numbers = loadNumbers("numbers.txt");
      if (args.length > 0) {
         if (args.length > 1) {
            err.println("ERRO: Inicialize o programa da seguinte forma:\n$ java Ex1_3 'frase a converter' ou:\n$ java Ex1_3");
            exit(1);
         }
         words = args[0].split(" ");
      }
      else {
         out.print("Frase que contenha números por extenso: ");
         frase = rd.nextLine();
         words = frase.split(" ");
      }

		// converter as palavras em valores e adicona-los a uma lista
		for (String s:words) {
			// verificar casos como o do 'twenty-four'
			String aux[] = s.split("-");
			if (aux.length>1) {
				if (numbers.containsKey(aux[0]) && numbers.containsKey(aux[1])) {
					numValues.add(numbers.get(aux[0]));
					numValues.add(numbers.get(aux[1]));
				}
			}
			else {
				if (numbers.containsKey(s)) {
					numValues.add(numbers.get(s));
				}
			}
		}

		// print do resultado final
		out.println(buildNumber(numValues));
	}

	public static HashMap<String, Integer> loadNumbers(String fileName) throws IOException {
      HashMap<String, Integer> aux = new HashMap<>();
      Path path = Paths.get(fileName);
      lines = Files.readAllLines(path);
      String[] splitted;
      for (String line:lines) {
         splitted = line.split(" - ");
         aux.put(splitted[1], Integer.parseInt(splitted[0]));
      }
      return aux;
   }

	// um valor ser do tipo log10Int significa que o log10 desse valor dá um número inteiro, o que indica que esse valor é obrigatoriamente do tipo 10, 100 ,1000, 10000, etc...
	public static boolean islog10Int(int val) {
		Double n = Math.log10(val);
		if ((Math.floor(n) == n) && !Double.isInfinite(n) && val != 1) 
			return true;
		return false;
	}

	public static long buildNumber(List<Integer> nmrs) {
		int current, next=0;
		long finalN=0, mult=1;
		boolean lastVal, inMult = false;
		//out.print("[");
		for (int i = 0; i < nmrs.size(); i++) {
			// atribuição de valores às variáveis
			if (i < nmrs.size()-1)
				next = nmrs.get(i+1);
			else
				// um valor que não seja do tipo log10Int
				next = 2;
			current = nmrs.get(i);
			lastVal = (i == nmrs.size()-1);
			
			// print do valor atual
			if (!lastVal)
				out.print(current+" ");
			else
				out.print(current);
			out.println(current);
			// sinal entre valores
			if (!lastVal) {
				if (islog10Int(next)) 
					out.print("* ");
				else
					out.print("+ ");
			}

			// se o valor atual não for do tipo log10Int
			if (!islog10Int(current)) {
				out.println("current is not");
				// caso tenham havido já multiplicações anteriormente
				if (mult > 1) {
					finalN += mult;
					// resetar o mult
					mult = 1;
				}
				
				inMult = false;

				// se o valor seguinte for do tipo log10Int
				if (islog10Int(next)) {
						// aplicar a multiplicação e indicar que estamos a fazer multplicações
						mult *= current;
						out.println("inMult is true");
						inMult = true;
				}
				// se o seguinte não for do tipo lo10Int
				else
					// então não haverão multiplicações a seguir e pode ser somado o valor atual ao final
					finalN += current;
			}

			// se estivermos em multiplicações e o valor atual for do tipo log10Int
			if (inMult && islog10Int(current)) {
				// efetuar a multiplicação
				mult *= current;

			// if (lastVal)
				//	finalN += mult;
			}			
		}	
		//out.println("]");
		return finalN;
	}
}
