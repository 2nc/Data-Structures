import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author wnc
 */


public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    Set<K> s = new HashSet<>();
    private BST root;
    private class BST{
        private K k;
        private V v;
        private BST leftC, rightC;


        public BST(K k, V v) {
            this.k = k;
            this.v = v;
        }
    }
    @Override
    public void clear() {
        root = null;
        s = null;
    }

    @Override
    public boolean containsKey(K key) {
        return find(root, key) != null;
    }

    @Override
    public V get(K key) {
            BST b = find(root, key);
            if (b != null){
                return b.v;
            }
            return null;
    }

    private BST find(BST T, K sk){
        if (T == null){
            return null;
        }
        int cmp = sk.compareTo(T.k);
        if (cmp == 0){
            return T;
        } else if ( cmp < 0) {
            return find (T.leftC, sk);
        } else {
            return find (T.rightC, sk);
        }
    }

    @Override
    public int size() {
        if (s != null){
            return s.size();
        }
        return 0;
    }

    @Override
    public void put(K key, V value) {
        if (key == null){
            throw new IllegalArgumentException();
        }
        if (value == null){
            remove(key);
            return;
        }
        root = insert(root, key, value);
    }

    private BST insert(BST T, K ik, V val){
        if (T == null){
            s.add(ik);
            return new BST(ik,val);
        }
        int cmp = ik.compareTo(T.k);
        if (cmp < 0){
            T.leftC = insert(T.leftC, ik, val);
        } else if(cmp > 0) {
            T.rightC = insert(T.rightC, ik, val);
        }
        return T;
    }

    public void printInOrder(){
       printInOrderBST(root);
    }

    private void printInOrderBST(BST b){
        if (b == null) {
            return;
        }
        printInOrderBST(b.leftC);
        System.out.println(b.k + " " + b.v);
        printInOrderBST(b.rightC);
    }


    @Override
    public Set keySet() {
        return s;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException();
    }
}
