import java.util.ArrayList;
import java.util.Iterator;

public class LinkedListManager {
    public LinkedListImplementation list = new LinkedListImplementation();

    public LinkedListManager(ArrayList<Integer> input) {
        Iterator<Integer> iterator = input.iterator();
        for (int i = 0; i < input.size(); i++) {
            list.insert(iterator.next(), i);
        }
    }

    public LinkedListManager() {
        list = new LinkedListImplementation();
    }



    public boolean linearSearch(int value)
    {
        Node current = list.front;
        while (current != null)
        {
            if (current.getValue() == value)
                return true;
            current = current.getLink();
        }
        return false;
    }

    public int binarySearch(int value) {
        return binarySearchRecursive(0, list.size(), value);
    }

    private int binarySearchRecursive(int leftSide, int rightSide, int value) {
        if (rightSide>=leftSide) {
            int mid = leftSide + (rightSide - leftSide)/2;

            if (list.get(mid) == value)
                return mid;
            if (list.get(mid) > value)
                return binarySearchRecursive(leftSide, mid-1, value);

            return binarySearchRecursive(mid+1, rightSide, value);
        }
        return -1;
    }
}

class LinkedListImplementation {
    public Node front;
    private int size;

    public int size() {
        return size;
    }

    public boolean insert(int value, int position) {
        Node newNode = new Node(value);
        boolean inserted = false;
        Node ptr = front;

        if (position == 0) {
            newNode.setLink(front);
            front = newNode;
            size++;
            return true;
        }

        for (int i = 0; i <= size; i++) {
            if (i == position) {
                Node tmp = ptr.getLink();
                ptr.setLink(newNode);
                newNode.setLink(tmp);
                inserted = true;
                break;
            }

            if (ptr.getLink() != null) {
                ptr = ptr.getLink();
            }
        }

        if (inserted) {
            size++;
        }

        return inserted;
    }

    //Very slow compared to array.
    public int get(int index) {
        if (front == null) {
            throw new IndexOutOfBoundsException();
        }

        Node current = front;
        int i = 0;

        while (current != null) {
            if (i == index) {
                return current.getValue();
            }
            if (current.getLink() != null) {
                current = current.getLink();
            } else {
                current = null;
            }
            i++;
        }
        throw new IndexOutOfBoundsException();
    }

    public String toString() {
        Node current = front;
        int outputSize = size + 2;
        String[] output = new String[outputSize];

        output[0] = "[";

        for (int i = 1; i < outputSize - 1; i++) {
            output[i] = String.valueOf(current.getValue());

            if (current.getLink() != null) {
                current = current.getLink();
            } else {
                current = null;
            }
        }

        output[outputSize - 1] = "]";
        String list = String.join(", ", output);
        StringBuilder sb = new StringBuilder(list);

        int firstIndex = sb.indexOf(", ");
        sb.delete(firstIndex, firstIndex+2);
        int lastIndex = sb.lastIndexOf(", ");
        sb.delete(lastIndex, lastIndex+2);

        return sb.toString();
    }

}


class Node {
    private int value;
    private Node link;

    public Node() {

    }

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLink() {
        return link;
    }

    public void setLink(Node link) {
        this.link = link;
    }
}