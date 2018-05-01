import java.util.*;

public class BigOTest {

    private ArrayManager arrayManager;
    private LinkedListManager linkedListManager;

    private ArrayManager originalArrayManager;
    private LinkedListManager originalLinkedListManager;

    private TimeManager timeManager = new TimeManager();

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

                    System.out.println("Set a known value at specific index? (y/n) ");
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
            boolean isArray = false;

            switch (scanner.nextInt()) {
                case 1:
                    System.out.println("Array:");
                    isArray = true;
                    chooseSearchSort(true);
                    break;
                case 2:
                    System.out.println("Linked list:");
                    isArray = false;
                    chooseSearchSort(false);
                    break;
                default:
                    continueChoose = true;
            }

            System.out.println();
            System.out.println("Perform another operation? (y/n)");
            String response = scanner.next();
            continueChoose = (response.equals("y") || response.equals("Y"));

            if (continueChoose) {
                System.out.println();
                System.out.println("Reset data structures to original state? (y/n)");
                String reset = scanner.next();
                if (reset.equals("y") || reset.equals("Y")) {
                    arrayManager = new ArrayManager(originalArrayManager);
                    linkedListManager = new LinkedListManager(originalLinkedListManager);
                    if (isArray) {
                        System.out.println(arrayManager.array);
                    } else {
                        System.out.println(linkedListManager.list);
                    }
                }
            }
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
                        timeManager.startTimer();
                        int found = arrayManager.linearSearch(val1);
                        long time = timeManager.endTimer();
                        if (found == -1) {
                            System.out.println("Value not found in array.");
                        } else {
                            System.out.println("Value found at index " + found);
                        }
                        System.out.println("Completed operation on " + arrayManager.array.length() + " value array in " + time + "ms");
                    } else {
                        timeManager.startTimer();
                        boolean found = linkedListManager.linearSearch(val1);
                        long time = timeManager.endTimer();
                        if (found) {
                            System.out.println("Value found in linked list.");
                        } else {
                            System.out.println("Value not found in linked list");
                        }
                        System.out.println("Completed operation on " + linkedListManager.list.size() + " value linked list in " + time + "ms");
                    }
                    break;
                case 2:
                    System.out.println("Enter value to search for: ");
                    int val2 = scanner.nextInt();

                    if (isArray) {
                        timeManager.startTimer();
                        System.out.println("Warning: this will not work correctly unless array is already sorted.");
                        int found = arrayManager.binarySearch(val2);
                        long time = timeManager.endTimer();
                        if (found == -1) {
                            System.out.println("Value not found in array.");
                        } else {
                            System.out.println("Value found at index " + found);
                        }
                        System.out.println("Completed operation on " + arrayManager.array.length() + " value array in " + time + "ms");
                    } else {
                        timeManager.startTimer();
                        int found = linkedListManager.binarySearch(val2);
                        long time = timeManager.endTimer();
                        if (found == -1) {
                            System.out.println("Value not found in linked list.");
                        } else {
                            System.out.println("Value found at position " + found);
                        }
                        System.out.println("Completed operation on " + linkedListManager.list.size() + " value linked list in " + time + "ms");
                    }
                    break;
                case 3:
                    if (isArray) {
                        timeManager.startTimer();
                        arrayManager.insertionSort();
                        long time = timeManager.endTimer();
                        System.out.println(arrayManager.array);
                        System.out.println("Completed operation on " + arrayManager.array.length() + " values in " + time + "ms");
                    } else {
                        timeManager.startTimer();
                        linkedListManager.insertionSort();
                        long time = timeManager.endTimer();
                        System.out.println(linkedListManager.list);
                        System.out.println("Completed operation on " + linkedListManager.list.size() + " values in " + time + "ms");
                    }
                    break;
                case 4:
                    if (isArray) {
                        timeManager.startTimer();
                        arrayManager.mergeSort();
                        long time = timeManager.endTimer();
                        System.out.println(arrayManager.array);
                        System.out.println("Completed operation on " + arrayManager.array.length() + " values in " + time + "ms");
                    } else {
                        timeManager.startTimer();
                        linkedListManager.mergeSort();
                        long time = timeManager.endTimer();
                        System.out.println(linkedListManager.list);
                        System.out.println("Completed operation on " + linkedListManager.list.size() + " values in " + time + "ms");
                    }
                    break;
                case 5:
                    if (isArray) {
                        timeManager.startTimer();
                        arrayManager.bubbleSort();
                        long time = timeManager.endTimer();
                        System.out.println(arrayManager.array);
                        System.out.println("Completed operation on " + arrayManager.array.length() + " values in " + time + "ms");
                    } else {
                        timeManager.startTimer();
                        linkedListManager.bubbleSort();
                        long time = timeManager.endTimer();
                        System.out.println(linkedListManager.list);
                        System.out.println("Completed operation on " + linkedListManager.list.size() + " values in " + time + "ms");
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

        if (originalArrayManager == null || originalLinkedListManager == null) {
            originalArrayManager = new ArrayManager(arrayManager);
            originalLinkedListManager = new LinkedListManager(linkedListManager);
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

        if (originalArrayManager == null || originalLinkedListManager == null) {
            originalArrayManager = new ArrayManager(arrayManager);
            originalLinkedListManager = new LinkedListManager(linkedListManager);
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

        if (originalArrayManager == null || originalLinkedListManager == null) {
            originalArrayManager = new ArrayManager(arrayManager);
            originalLinkedListManager = new LinkedListManager(linkedListManager);
        }

        return true;
    }
}
