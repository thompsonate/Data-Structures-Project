import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

public class ArrayManager {
    public ArrayImplementation array;

    public ArrayManager(ArrayList<Integer> input) {
        int[] ret = new int[input.size()];
        Iterator<Integer> iterator = input.iterator();
        for (int i = 0; i < ret.length; i++) {
            ret[i] = iterator.next().intValue();
        }

        array = new ArrayImplementation(ret);
    }

    public ArrayManager(int size, int bound) {
        array = new ArrayImplementation(size);

        Random rand = new Random();

        for (int i = 0; i < size; i++) {
            array.add(rand.nextInt(bound), i);
        }
    }

    public ArrayManager(int size, int bound, int includeValue, int atIndex) {
        array = new ArrayImplementation(size);

        for (int i = 0; i < size; i++) {
            array.add(generateNewValue(i, bound, includeValue, atIndex), i);
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

    ArrayImplementation(int[] array) {
        this.array = array;
    }

    public void add(int value, int toIndex) {
        array[toIndex] = value;
    }

    public String toString() {
        return Arrays.toString(array);
    }
}
