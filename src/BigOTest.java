public class BigOTest {
    public static void main(String[] args) {
        ArrayManager arrayManager = new ArrayManager(10, 3, 0, 0);
        System.out.println(arrayManager.array.toString());

        LinkedListManager linkedListManager = new LinkedListManager(10, 3,0, 0);
        System.out.println(linkedListManager.list);
    }
}
