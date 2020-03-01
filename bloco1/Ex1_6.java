import java.util.*;
import static java.lang.System.*;
import java.nio.file.*;
import java.io.*;

public class Ex1_6 {
		static HashMap<String, String> dic = new HashMap<>();

	public static void main(String[] args) throws IOException {
		String dicFile, sentence;
		String[] files = new String[0];
		List<String> dicFileContent = new ArrayList<>();
		List<String> fileContent = new ArrayList<>();
		Scanner rd = new Scanner(System.in);

		// if there is only one argument, then it must be the dictionary
		if (args.length == 1) {
			dicFile = args[0];
		}

		// if there is more than one argument, then it is the dictionary and the files to be translated
		else if (args.length > 1) {
			files = new String[args.length-1];
			dicFile = args[0];
			for (int i = 1; i < args.length; i++) {
				files[i-1] = args[i];
			}
		}

		// if there are no arguments, then the use wants to write the file name
		else {
			out.print("Dictionary file: ");
			dicFile = rd.nextLine();
		}

		// save content from dictionary file into a hashmap
		dicFileContent = loadFileContent(dicFile);
		String[] splitted;
		for (String l:dicFileContent) {
			splitted = l.split(" ");
			dic.put(splitted[0], splitted[1]);
		}

		if (args.length > 1) {
			// iterate through all files to be translated
			for (String f:files) {
				out.println("Translation of: "+f);
				// iterate through all the lines of the file
				for (String l:loadFileContent(f)) {
					translateLine(l);
					out.println("");
				}
				out.println("");
		 	}
		}
		else {
			out.println("WARNING: If you want to translate files, pass them as argument when running this program.\n$ java Ex1_6 dicFileName.txt file1.txt file2.txt ...\n");
			while(true) {
				out.print("Sentence to translate: ");
				sentence = rd.nextLine();
				translateLine(sentence);
				out.println("\n");
			}
		}
	}


	public static List<String> loadFileContent(String fName) throws IOException {
		Path path = Paths.get(fName);
		return Files.readAllLines(path);
	}

	public static void translateLine(String s) {
		String splitted[] = s.split(" ");
		// iterate through all the words of each line
		for (String w:splitted) {
			if (dic.containsKey(w))
				out.print(dic.get(w)+" ");
			else
				out.print(w+" ");
		}
	}
}
