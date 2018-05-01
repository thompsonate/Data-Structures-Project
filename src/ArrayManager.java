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

    public void insertionSort() {
        int n = array.length();
        for (int i = 1; i < n; ++i) {
            int key = array.get(i);
            int j = i-1;

            while (j>=0 && array.get(j) > key)
            {
                array.set(array.get(j), j+1);
                j = j-1;
            }
            array.set(key, j+1);
        }
    }



    public void mergeSort() {
        mergeSortRecursive(array.getArray(), 0, array.length()-1);
    }

    private void mergeSortRecursive(int arr[], int left, int right) {
        if (left < right) {
            // Find the middle point
            int m = (left+right)/2;

            // Sort first and second halves
            mergeSortRecursive(arr, left, m);
            mergeSortRecursive(arr , m+1, right);

            // Merge the sorted halves
            merge(arr, left, m, right);
        }
    }

    private void merge(int arr[], int left, int middle, int right) {
        // Find sizes of two subarrays to be merged
        int n1 = middle - left + 1;
        int n2 = right - middle;

        //Create temp arrays
        int L[] = new int [n1];
        int R[] = new int [n2];

        //Copy data to temp arrays
        for (int i=0; i<n1; ++i) L[i] = arr[left + i];
        for (int j=0; j<n2; ++j) R[j] = arr[middle + 1+ j];

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        int k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of L[] if any
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Copy remaining elements of R[] if any
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }



    public void bubbleSort() {
        bubbleSortRecursive(array.length());
    }

    private void bubbleSortRecursive(int length) {
        int i, j, temp;
        boolean swapped;
        for (i = 0; i < length - 1; i++) {
            swapped = false;
            for (j = 0; j < length - i - 1; j++) {
                if (array.get(j) > array.get(j + 1)) {
                    // swap array[j] and array[j+1]
                    temp = array.get(j);
                    array.set(array.get(j + 1), j);
                    array.set(temp, j + 1);
                    swapped = true;
                }
            }
            if (swapped == false) break;
        }
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

    public int[] getArray() {
        return array;
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

    public void set(int value, int index) {
        array[index] = value;
    }

    public String toString() {
        return Arrays.toString(array);
    }
}
