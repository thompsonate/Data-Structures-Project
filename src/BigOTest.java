import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class BigOTest {

    ArrayManager arrayManager;
    LinkedListManager linkedListManager;

    public static void main(String[] args) {
        BigOTest project = new BigOTest();
        project.constructDataStructuresFromFile("/Users/Nate/Desktop/import.csv");
    }


    public void constructDataStructuresFromFile(String path) {
        ArrayList<Integer> list = FileManager.importFromFile(path).get();

        arrayManager = new ArrayManager(list);
        linkedListManager = new LinkedListManager(list);
    }
}
