import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

public class BigOTest {

    private ArrayManager arrayManager;
    private LinkedListManager linkedListManager;

    public static void main(String[] args) {
        BigOTest project = new BigOTest();
        System.out.println();
        project.setUpDataStructures();
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
                        if (success) {
                            System.out.println(arrayManager.array);
                            chooseDataStructure();
                        } else {
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
                        System.out.println(arrayManager.array);
                        chooseDataStructure();
                    } else {
                        constructDataStructuresWithRandomNums(size, bound);
                        System.out.println(arrayManager.array);
                        chooseDataStructure();
                    }
                    break;
                default:
                    continueImport = true;
            }
        }
    }


    private void chooseDataStructure() {
        Scanner scanner = new Scanner(System.in);

        boolean continueChoose = true;

        while (continueChoose) {
            continueChoose = false;
            System.out.println();
            System.out.println("Choose a data structure to search or sort");
            System.out.println("1 - Array\n2 - Linked list");

            switch (scanner.nextInt()) {
                case 1:
                    System.out.println("Array:");
                    chooseSearchSort(true);
                    break;
                case 2:
                    System.out.println("Linked list:");
                    chooseSearchSort(false);
                    break;
                default:
                    continueChoose = true;
            }

            System.out.println();
            System.out.println("Perform another operation? (y/n)");
            String response = scanner.next();
            continueChoose = (response.equals("y") || response.equals("Y"));
        }
    }

    private void chooseSearchSort(boolean isArray) {
        Scanner scanner = new Scanner(System.in);

        boolean continueChoose = true;

        while (continueChoose) {
            continueChoose = false;
            System.out.println("1 - Linear search\n2 - Binary search\n3 - Insertion sort\n4 - Merge sort\n5 - Bubble sort");

            switch (scanner.nextInt()) {
                case 1:
                    System.out.println("Enter value to search for: ");
                    int val1 = scanner.nextInt();

                    if (isArray) {
                        int found = arrayManager.linearSearch(val1);
                        if (found == -1) {
                            System.out.println("Value not found in array.");
                        } else {
                            System.out.println("Value found at index " + found);
                        }
                    } else {
                        boolean found = linkedListManager.linearSearch(val1);
                        if (found) {
                            System.out.println("Value found in linked list.");
                        } else {
                            System.out.println("Value not found in linked list");
                        }
                    }
                    break;
                case 2:
                    System.out.println("Enter value to search for: ");
                    int val2 = scanner.nextInt();

                    if (isArray) {
                        System.out.println("Warning: this will not work correctly unless array is already sorted.");
                        int found = arrayManager.binarySearch(val2);
                        if (found == -1) {
                            System.out.println("Value not found in array.");
                        } else {
                            System.out.println("Value found at index " + found);
                        }
                    } else {
                        int found = linkedListManager.binarySearch(val2);
                        if (found == -1) {
                            System.out.println("Value not found in linked list.");
                        } else {
                            System.out.println("Value found at position " + found);
                        }
                    }
                    break;
                case 3:
                    if (isArray) {
                        arrayManager.insertionSort();
                        System.out.println(arrayManager.array);
                    } else {
                        linkedListManager.insertionSort();
                        System.out.println(linkedListManager.list);
                    }
                    break;
                case 4:
                    if (isArray) {
                        arrayManager.mergeSort();
                        System.out.println(arrayManager.array);
                    } else {
                        linkedListManager.mergeSort();
                        System.out.println(linkedListManager.list);
                    }
                    break;
                case 5:
                    if (isArray) {
                        arrayManager.bubbleSort();
                        System.out.println(arrayManager.array);
                    } else {
                        linkedListManager.bubbleSort();
                        System.out.println(linkedListManager.list);
                    }
                    break;
                default:
                    continueChoose = true;
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
