/** This class outputs all palindromes in the words file in the current directory. */
public class PalindromeFinder {
    public static void main(String[] args) {
        int minLength = 4;
        In in = new In("../library-sp19/data/words.txt");
        Palindrome palindrome = new Palindrome();

        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && palindrome.isPalindrome(word)) {
                System.out.println(word);
            }
        }
        System.out.println();
        In inn = new In("../library-sp19/data/words.txt");
        while (!inn.isEmpty()) {
            String word = inn.readString();
            if (word.length() >= minLength && palindrome.isPalindrome(word, new OffByOne())) {
                System.out.println(word);
            }
        }
    }
} //Uncomment this class once you've written isPalindrome. */