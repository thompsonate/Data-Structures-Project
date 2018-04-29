import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class FileManager {
    public static Optional<ArrayList<Integer>> importFromFile(String path) {
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(path));
            String stringFromFile = new String(encoded, Charset.forName("UTF-8"));

            if (stringFromFile.contains("\n")) {
                System.out.println("Only one row of values is supported");
                return null;
            }

            for (char c : stringFromFile.toCharArray()) {
                if (!Character.isDigit(c) && c != ',') {
                    System.out.println("Invalid input. File should contain only digits and commas.");
                    return null;
                }
            }

            ArrayList<Integer> list = new ArrayList<>();

            Scanner scanner = new Scanner(new File(path));
            scanner.useDelimiter(",");
            while(scanner.hasNext()){
                int num = Integer.parseInt(scanner.next());
                list.add(num);
            }
            scanner.close();

            return Optional.of(list);

        } catch (IOException e) {
            System.out.println("Invalid file path.");
            return null;
        }
    }
}
