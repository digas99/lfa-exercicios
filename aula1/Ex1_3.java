import java.util.*;
import static java.lang.System.*;
import java.io.*;
import java.nio.file.*;

public class Ex1_3 {

	static HashMap<String, Integer> numbers;
	static List<String> lines;

	public static void main(String[] args) throws IOException {
		String frase, newFrase="";
		String[] words;
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

		for (String s:words) {
			// check for cases as 'twenty-four'
			String aux[] = s.split("-");
			if (aux.length > 1) {
				if (numbers.containsKey(aux[0]) && numbers.containsKey(aux[1])) {
					newFrase += numbers.get(aux[0]) + " " + numbers.get(aux[1]);
				}
				else
					newFrase += aux[0]+"-"+aux[1];
			}
			// normal cases as 'twenty'
			else {
				if (numbers.containsKey(s)) {
					newFrase += numbers.get(s);
				}
				else
					newFrase += s;
			}
			newFrase += " ";
		}
		out.println(newFrase);
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
}	
