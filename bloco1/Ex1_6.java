import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

public class Ex1_6 {
	
	public static void main(String[] args) throws IOException {
		Map<String, String> dict = new HashMap<>();
		Files.readAllLines(Paths.get("data/dic1.txt")).stream().forEach(line -> {
			String[] split = line.split(" ");
			dict.put(split[0], split[1]);
		});

		Stream.of(new Scanner(System.in).nextLine().split("\\s")).forEach(value -> System.out.print(dict.containsKey(value) ? dict.get(value)+" " : value+" "));
	}
}
