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
}

class LinkedListImplementation {
    private Node front;
    private int size;

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