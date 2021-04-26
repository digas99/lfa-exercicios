import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

public class Ex1_6_c {

	static Map<String, String> dict = new HashMap<>();

	public static void main(String[] args) throws IOException {
		Files.readAllLines(Paths.get("data/dic2.txt")).stream().forEach(line -> {
			String[] split = line.split("\\t");
			dict.put(split[0], split[1]);
		});

		Stream.of(new Scanner(System.in).nextLine().split("\\s")).forEach(value -> System.out.print(recursiveWordTranslation(value)));
	}

	public static String recursiveWordTranslation(String word) {
		if (!dict.containsKey(word))
			return word+" ";
		String result = "";
		for (String w : dict.get(word).split("\\s")) {
			result += recursiveWordTranslation(w);
		}
		return result;
	}	
}