/**
 * @author wnc
 */
public class Palindrome {

    public Deque<Character> wordToDeque(String word){
        Deque<Character> d = new LinkedListDeque<>();
        for(int i = 0; i < word.length(); i++){
            d.addLast(word.charAt(i));
        }
        return d;
    }

    public boolean isPalindrome(String word){
        Deque<Character> a = wordToDeque(word);
        Deque<Character> b = wordToDeque(word);
        int size = a.size();
        for(int i = 0; i < size; i++){
            if(a.removeFirst() != b.removeLast()){
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        if(word.length() == 0 || word.length() == 1){
            return true;
        }
        Deque<Character> a = wordToDeque(word);
        Deque<Character> b = wordToDeque(word);
        int size = a.size();
        for(int i = 0; i < size / 2; i++){
            if(!cc.equalChars(a.removeFirst(), b.removeLast())){
                return false;
            }
        }
        return true;
    }
}
