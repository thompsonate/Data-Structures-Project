import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

public class ArrayManager {
    public ArrayImplementation array;

    public ArrayManager(int size, int bound) {
        array = new ArrayImplementation(size);

        Random rand = new Random();

        for (int i = 0; i < size; i++) {
            array.add(rand.nextInt(bound), i);
        }
    }

    public ArrayManager(int size, int bound, int includeValue, int atIndex) {
        this(size, bound, Optional.of(includeValue), Optional.of(atIndex));
    }

    private ArrayManager(int size, int bound, Optional<Integer> includeValue, Optional<Integer> atIndex) {
        array = new ArrayImplementation(size);

        for (int i = 0; i < size; i++) {
            array.add(generateNewValue(i, bound, includeValue.get(), atIndex.get()), i);
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
}

class ArrayImplementation {
    private int[] array;

    ArrayImplementation(int size) {
        array = new int[size];
    }

    public void add(int value, int toIndex) {
        array[toIndex] = value;
    }

    public String toString() {
        return Arrays.toString(array);
    }
}
