import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

public class Ex1_4 {
	
	public static void main(String[] args) throws IOException {
		Map<String, String> numberAssoc = new HashMap<>();
		Files.readAllLines(Paths.get("data/numbers.txt")).stream().forEach(line -> {
			String[] split = line.split(" - ");
			numberAssoc.put(split[1], split[0]);
		});

		Stream.of(new Scanner(System.in).nextLine().split("\\s|-")).forEach(value -> System.out.print(numberAssoc.containsKey(value) ? numberAssoc.get(value)+" " : value+" "));
	}
}
