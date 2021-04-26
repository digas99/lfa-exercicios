import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

public class Ex1_5 {

	public static void main(String[] args) throws IOException {
		Map<String, String> numberAssoc = new HashMap<>();
		Files.readAllLines(Paths.get("data/numbers.txt")).stream().forEach(line -> {
			String[] split = line.split(" - ");
			numberAssoc.put(split[1], split[0]);
		});

		int digit, lastDigit = 0, mult=1, result = 0;
		for (String value : Stream.of(new Scanner(System.in).nextLine().split("\\s|-")).filter(value -> numberAssoc.containsKey(value)).toArray(String[]::new)) {
			System.out.print(value+" ");
			digit = Integer.parseInt(numberAssoc.get(value));	
			if (lastDigit < digit)
				mult *= digit;
			else {
				result += mult;
				mult = digit;
			}
			lastDigit = digit;
		}
		result += mult;
		System.out.println("-> "+result);
	}
}