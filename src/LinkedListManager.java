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
    }

    public LinkedListManager(LinkedListManager manager) {
        Node currentOld = manager.list.front;
        if (currentOld == null) {
            return;
        }

        Node newFront = new Node();
        list.front = newFront;
        newFront.setValue(currentOld.getValue());
        currentOld = currentOld.getLink();
        Node currentNew = list.front;

        while (currentOld != null) {
            Node newNode = new Node(currentOld.getValue());
            currentNew.setLink(newNode);
            currentNew = currentNew.getLink();
            currentOld = currentOld.getLink();
            list.size++;
        }

    }

    public boolean linearSearch(int value) {
        Node current = list.front;
        while (current != null) {
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

    public void insertionSort() {
        LinkedListImplementation sorted = new LinkedListImplementation();
        Node current = list.front;
        // Traverse the given linked list and insert every node to sorted
        while (current != null) {
            // Store next for next iteration
            Node next = current.getLink();

            sorted.sortedInsert(current);
            current = next;
        }
        list.front = sorted.front;
    }

    public void mergeSort() {
        list.front = mergeSortRecursive(list.front);
    }

    private Node mergeSortRecursive(Node head) {
        if (head == null || head.getLink() == null) {
            return head;
        }

        Node middle = getMiddle(head);
        Node nextofmiddle = middle.getLink();

        middle.setLink(null);
        // Apply mergeSort on left list
        Node left = mergeSortRecursive(head);
        // Apply mergeSort on right list
        Node right = mergeSortRecursive(nextofmiddle);
        // Merge the left and right lists
        Node sortedlist = sortedMerge(left, right);
        return sortedlist;
    }

    private Node sortedMerge(Node a, Node b) {
        Node result = null;
        if (a == null)
            return b;
        if (b == null)
            return a;

        if (a.getValue() <= b.getValue()) {
            result = a;
            result.setLink(sortedMerge(a.getLink(), b));
        } else {
            result = b;
            result.setLink(sortedMerge(a, b.getLink()));
        }
        return result;
    }

    private Node getMiddle(Node h) {
        if (h == null)
            return h;
        Node fastptr = h.getLink();
        Node slowptr = h;

        // Move fastptr by two and slow ptr by one
        // Finally slowptr will point to middle node
        while (fastptr != null) {
            fastptr = fastptr.getLink();
            if(fastptr!=null) {
                slowptr = slowptr.getLink();
                fastptr=fastptr.getLink();
            }
        }
        return slowptr;
    }

    public void bubbleSort() {
        if (list.size() > 1) {
            for (int i = 0; i < list.size(); i++ ) {
                Node currentNode = list.front;
                Node next = list.front.getLink();
                for (int j = 0; j < list.size() - 1; j++) {
                    if (currentNode.getValue() > next.getValue()) {
                        int currentNodeValue = currentNode.getValue();
                        currentNode.setValue(next.getValue());
                        next.setValue(currentNodeValue);
                    }
                    currentNode = next;
                    next = next.getLink();
                }
            }
        }
    }

}

class LinkedListImplementation {
    public Node front;
    public int size;

    public LinkedListImplementation() {}


    public LinkedListImplementation(Node front, int size) {
        this.front = front;
        this.size = size;
    }

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

    public void sortedInsert(Node newNode) {
        if (front == null) {
            newNode.setLink(null);
            front = newNode;
        } else if (front.getValue() >= newNode.getValue()) {
            newNode.setLink(front);
            front = newNode;
        } else {
            Node current = front;
            // Locate the node before the point of insertion
            while (current.getLink() != null && current.getLink().getValue() < newNode.getValue()) {
                current = current.getLink();
            }
            newNode.setLink(current.getLink());
            current.setLink(newNode);
        }
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
        try {
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
            sb.delete(firstIndex, firstIndex + 2);
            int lastIndex = sb.lastIndexOf(", ");
            sb.delete(lastIndex, lastIndex + 2);

            return sb.toString();
        } catch (StringIndexOutOfBoundsException e) {
            return "";
        }
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