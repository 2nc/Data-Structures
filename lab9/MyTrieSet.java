/**
 * @author wnc
 */

import java.util.*;

public class MyTrieSet implements TrieSet61B {

    Node root = new Node();

    public class Node{
        char c;
        boolean b;
        boolean isKey;
        Map<Character, Node> map;
        public Node(char c, boolean b){
            this.c = c;
            this.b = b;
            map = new HashMap<>();
        }

        public Node(){
            map = new HashMap<>();
        }
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean contains(String key) {
        if (key == null || key.length() < 1 || root == null) {
            return false;
        }
        Node curr = root;
        for(int i = 0, n = key.length(); i < n; i++){
            char c = key.charAt(i);
            if(!curr.map.containsKey(c)){
                return false;
            }
            curr = curr.map.get(c);
        }
        if(!curr.isKey){
            return false;
        }
        return true;
    }

    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                curr.map.put(c, new Node(c, false));
            }
            curr = curr.map.get(c);
        }
        curr.isKey = true;
    }

    @Override
    public List<String> keysWithPrefix(String prefix) {
        List<String> result = new LinkedList<>();
        Node curr = root;
        for(int i = 0, n = prefix.length(); i < n; i++){
            char c  = prefix.charAt(i);
            if(curr.map.containsKey(c)){
                curr = curr.map.get(c);
            }else {
                return result;
            }
        }
        colHelp(prefix, result, curr);
        return result;
    }

    public void colHelp(String s, List<String> x, Node n){
        if(n.isKey){
            x.add(s);
        }
        Iterator<Character> i = n.map.keySet().iterator();
        while(i.hasNext()){
            char c = i.next();
            colHelp(s + c, x, n.map.get(c));
        }
    }

    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }
}
