import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class ArrayManager {
    public ArrayImplementation array;

    public ArrayManager(ArrayList<Integer> input) {
        int[] ret = new int[input.size()];
        Iterator<Integer> iterator = input.iterator();
        for (int i = 0; i < ret.length; i++) {
            ret[i] = iterator.next();
        }

        array = new ArrayImplementation(ret);
    }

    public ArrayManager(int size) {
        array = new ArrayImplementation(size);
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
