/**
 * @author wnc
 */
public class MyLinkedListDequeTest {
    public static boolean checkGet(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("get() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Prints a nice message based on whether a test passed.
     * The \n means newline. */
    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }

    public static void getRemoveTest(){
        boolean check = true;
        System.out.println("Running get/remove test.");
        LinkedListDeque<Integer> myDeque = new LinkedListDeque();
        myDeque.addLast(4);
        myDeque.addFirst(9);
        int m = myDeque.get(1);
        check = checkGet(true, m == 4) && check;
        int n = myDeque.removeLast();
        check = checkGet(true, n == 4) && check;
        printTestStatus(check);


    }
    public static void main(String[] args) {
        System.out.println("Running tests.\n");
        getRemoveTest();

    }
}
