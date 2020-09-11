/**
 * @author wnc
 */
public class ArrayDequeTest {
    /** Performs some basic array list tests. */
    public static boolean checkGet(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("get() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Utility method for printing out empty checks. */
    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Utility method for printing out empty checks. */
    public static boolean checkSize(int expected, int actual) {
        if (expected != actual) {
            System.out.println("size() returned " + actual + ", but expected: " + expected);
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

    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public static void addIsEmptySizeTest() {
        System.out.println("Running add/isEmpty/Size test.");
        //System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<String> lld1 = new ArrayDeque<String>();

        boolean passed = checkEmpty(true, lld1.isEmpty());

        lld1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        passed = checkSize(1, lld1.size()) && passed;
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

        lld1.addLast("middle");
        passed = checkSize(2, lld1.size()) && passed;

        lld1.addLast("back");
        passed = checkSize(3, lld1.size()) && passed;

        lld1.addFirst("front1");
        System.out.println("Printing out deque: ");
        lld1.printDeque();

        printTestStatus(passed);

    }

    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public static void addRemoveTest() {

        System.out.println("Running add/remove test.");

        //System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        // should be empty
        boolean passed = checkEmpty(true, lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

        lld1.removeFirst();
        // should be empty
        passed = checkEmpty(true, lld1.isEmpty()) && passed;

        printTestStatus(passed);

    }

    public static void getRemoveTest() {
        boolean check = true;
        System.out.println("Running get/remove test.");
        ArrayDeque<Integer> myDeque = new ArrayDeque<>();
        myDeque.addLast(4);
        myDeque.addFirst(9);
        int m = myDeque.get(1);
        check = checkGet(true, m == 4) && check;
        int n = myDeque.removeLast();
        check = checkGet(true, n == 4) && check;
        printTestStatus(check);
    }

    public static void resizeTest(){
        boolean check = true;
        System.out.println("Running resizeTest.");
        ArrayDeque<Integer> a = new ArrayDeque<>();
        a.addFirst(1);
        a.addFirst(2);
        a.addFirst(3);
        a.addFirst(4);
        a.addLast(5);
        a.addLast(6);
        a.addLast(7);
        a.addLast(8);
        check = checkSize(8, a.size()) && check;
        a.addFirst(9);
        check = checkSize(9, a.size()) && check;
        check = checkSize(16, a.amount()) && check;

        ArrayDeque<Integer> b = new ArrayDeque<>();
        for(int i = 0; i < 30; i++){
            b.addLast(i);
        }
        check = checkSize(30, b.size()) && check;
        check = checkSize(32, b.amount()) && check;
        for(int i = 0; i < 26; i++){
            b.removeLast();
        }
        check = checkSize(4, b.size()) && check;
        check = checkSize(16, b.amount()) && check;
        System.out.println("Printing out deque: ");
        b.printDeque();
        b.addLast(10);
        check = checkSize(5, b.size()) && check;

        ArrayDeque<Integer> c = new ArrayDeque<>();
        for(int i = 0; i < 30; i++){
            c.addFirst(i);
        }
        check = checkSize(30, c.size()) && check;
        check = checkSize(32, c.amount()) && check;
        for(int i = 0; i < 26; i++){
            c.removeFirst();
        }
        check = checkSize(4, c.size()) && check;
        check = checkSize(16, c.amount()) && check;
        System.out.println("Printing out deque: ");
        c.printDeque();
        c.addLast(10);
        check = checkSize(5, c.size()) && check;

        printTestStatus(check);


    }

    public static void main(String[] args) {
        System.out.println("Running tests.\n");
        addIsEmptySizeTest();
        addRemoveTest();
        getRemoveTest();
        resizeTest();
    }

}
