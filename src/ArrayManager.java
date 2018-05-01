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

    public int linearSearch(int valToFind) {
        for (int i = 0; i < array.length(); i++) {
            if (array.get(i) == valToFind)
                return i;
        }
        return -1;
    }

    public int binarySearch(int value) {
        return binarySearchRecursive(0, array.length(), value);
    }

    private int binarySearchRecursive(int leftSide, int rightSide, int value) {
        if (rightSide>=leftSide) {
            int mid = leftSide + (rightSide - leftSide)/2;

            if (array.get(mid) == value)
                return mid;
            if (array.get(mid) > value)
                return binarySearchRecursive(leftSide, mid-1, value);

            return binarySearchRecursive(mid+1, rightSide, value);
        }
        return -1;
    }
}

class ArrayImplementation {
    private int[] array;
    private int length;

    ArrayImplementation(int size) {
        array = new int[size];
    }

    ArrayImplementation(int[] array) {
        this.array = array;
    }

    public int length() {
        return array.length;
    }

    public void add(int value, int toIndex) {
        array[toIndex] = value;
    }

    public int get(int index) {
        return array[index];
    }

    public String toString() {
        return Arrays.toString(array);
    }
}
