import java.util.Optional;
import java.util.Random;

public class LinkedListManager {
    public LinkedListImplementation list = new LinkedListImplementation();

    public LinkedListManager(int size, int bound) {
        Random rand = new Random();

        for (int i = 0; i < size; i++) {
            list.insert(rand.nextInt(bound), i);
        }
    }

    public LinkedListManager(int size, int bound, int includeValue, int atIndex) {
        this(size, bound, Optional.of(includeValue), Optional.of(atIndex));
    }

    private LinkedListManager(int size, int bound, Optional<Integer> includeValue, Optional<Integer> atIndex) {
        for (int i = 0; i < size; i++) {
            list.insert(generateNewValue(i, bound, includeValue.get(), atIndex.get()), i);
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