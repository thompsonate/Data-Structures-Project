import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class BigOTest {

    private ArrayManager arrayManager;
    private LinkedListManager linkedListManager;

    public static void main(String[] args) {
        BigOTest project = new BigOTest();

        project.setUpDataStructures();

        System.out.println(project.arrayManager.array);
        System.out.println(project.linkedListManager.list);
    }

    private void setUpDataStructures() {
        Scanner scanner = new Scanner(System.in);

        boolean continueImport = true;

        while (continueImport) {
            continueImport = false;

            System.out.println("1 - Import values from file\n2 - Randomly generate values");
            switch (scanner.nextInt()) {
                case 1:
                    boolean continueFile = true;

                    while (continueFile) {
                        continueFile = false;
                        System.out.println("Enter CSV file path:");
                        String path = scanner.next();
                        boolean success = constructDataStructuresFromFile(path);
                        if (!success) {
                            continueFile = true;
                        }
                    }
                    break;
                case 2:
                    System.out.print("Enter amount of numbers to generate: ");
                    int size = scanner.nextInt();
                    System.out.print("Enter upper bound of randomly generated numbers: ");
                    int bound = scanner.nextInt();

                    System.out.println("Would you like to set a value at index? (y/n) ");
                    String response = scanner.next();

                    if (response.equals("y") || response.equals("Y")) {
                        System.out.print("Enter value: ");
                        int value = scanner.nextInt();

                        int index = 0;
                        do {
                            System.out.print("Enter index (0 - " + (size - 1) + "): ");
                            index = scanner.nextInt();
                            if (index >= size) {
                                System.out.println("Index must not exceed the amount of numbers generated (" + size + ")");
                            }
                        } while (index >= size);

                        arrayManager = new ArrayManager(size, bound, value, index);
                        linkedListManager = new LinkedListManager(size, bound, value, index);
                    } else {
                        arrayManager = new ArrayManager(size, bound);
                        linkedListManager = new LinkedListManager(size, bound);
                    }
                    break;
                default:
                    continueImport = false;
            }
        }
    }


    private boolean constructDataStructuresFromFile(String path) {
        Optional<ArrayList<Integer>> optionalList = FileManager.importFromFile(path);
        ArrayList<Integer> list;
        if (optionalList == null) {
            return false;
        }

        if (optionalList.isPresent()) {
            list = optionalList.get();
        } else {
            return false;
        }

        arrayManager = new ArrayManager(list);
        linkedListManager = new LinkedListManager(list);

        return true;
    }
}
