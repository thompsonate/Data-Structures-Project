import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
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

                        constructDataStructuresWithRandomNums(size, bound, value, index);
                    } else {
                        constructDataStructuresWithRandomNums(size, bound);
                    }
                    break;
                default:
                    continueImport = false;
            }
        }
    }

    private void constructDataStructuresWithRandomNums(int size, int bound) {
        Random rand = new Random();
        arrayManager = new ArrayManager(size);
        linkedListManager = new LinkedListManager();

        for (int i = 0; i < size; i++) {
            int value = rand.nextInt(bound);

            arrayManager.array.add(value, i);
            linkedListManager.list.insert(value, i);
        }
    }

    private void constructDataStructuresWithRandomNums(int size, int bound, int includeValue, int atIndex) {
        arrayManager = new ArrayManager(size);
        linkedListManager = new LinkedListManager();

        for (int i = 0; i < size; i++) {
            int value = generateNewValue(i, bound, includeValue, atIndex);

            arrayManager.array.add(value, i);
            linkedListManager.list.insert(value, i);
        }
    }

    private int generateNewValue(int forIndex, int bound, int includeValue, int atIndex) {
        Random rand = new Random();

        if (forIndex == atIndex) {
            return includeValue;
        } else {
            int num;
            do {
                num = rand.nextInt(bound);
            } while (num == includeValue);
            return num;
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
