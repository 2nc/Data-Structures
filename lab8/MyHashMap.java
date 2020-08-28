/**
 * @author wnc
 */

import java.util.*;

public class MyHashMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    int initialSize;
    double loadFactor;
    int hashSize = 0;
    HashSet<K> hSet = new HashSet<>();
    ArrayList<LinkedList> bucket = new ArrayList<>();

    public MyHashMap(){
        this.initialSize = 16;
        this.loadFactor = 0.75;
        for(int i = 0; i < initialSize; i++){
            bucket.add(new LinkedList<hashNode>());
        }
    }

    public MyHashMap(int initialSize){
        this.initialSize = initialSize;
        this.loadFactor = 0.75;
        for(int i = 0; i < initialSize; i++){
            bucket.add(new LinkedList<hashNode>());
        }
    }

    public MyHashMap(int initialSize, int loadFactor){
        this.initialSize = initialSize;
        this.loadFactor = loadFactor;
        for(int i = 0; i < initialSize; i++){
            bucket.add(new LinkedList<hashNode>());
        }
    }

    @Override
    public void clear() {
        hSet = null;
        bucket = null;
        hashSize = 0;
    }

    @Override
    public boolean containsKey(K key) {
        if(hSet == null){
            return false;
        }
        return hSet.contains(key);
    }

    @Override
    public V get(K key) {
        if(!containsKey(key)){
            return null;
        }
        int code = hashCode(key);
        LinkedList<hashNode>  l = bucket.get(code);
        Iterator<hashNode> iNode = l.iterator();
        while(iNode.hasNext()){
            hashNode h = iNode.next();
            if(key.compareTo(h.key) == 0){
                return h.val;
            }
        }
        return null;
    }

    @Override
    public int size() {
        if(hSet == null){
            return 0;
        }
        return hSet.size();
    }

    @Override
    public void put(K key, V value) {
        if(containsKey(key)){
            LinkedList l = bucket.get(hashCode(key));
            Iterator iLink = l.iterator();
            while(iLink.hasNext()){
                hashNode exist = (hashNode) iLink.next();
                if((exist.key.compareTo(key) == 0)){
                    exist.val = value;
                }
            }
        }
        hashNode h = new hashNode(key,value);
        if((size()+ 1.0 )/ bucket.size() >= loadFactor){
            resize();
        }
        LinkedList l = bucket.get(hashCode(key));
        l.add(h);
        hSet.add(key);
    }

    private void resize(){
        initialSize = initialSize * 2;
        ArrayList<LinkedList>  newBucket = new ArrayList<>();
        for(int i = 0; i < initialSize; i++){
            newBucket.add(new LinkedList());
        }
        for(int i = 0 ;i < bucket.size(); i++){
            for(int j = 0; j < bucket.get(i).size(); j++){
                hashNode h = (hashNode) (bucket.get(i).get(j));
                LinkedList l = newBucket.get(hashCode(h.key));
                l.add(h);
            }
        }
        bucket = newBucket;
    }

    @Override
    public Set keySet() {
        return hSet;
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
        return keySet().iterator();
    }

    public class hashNode{
        K key;
        V val;
        public hashNode(K key, V val){
            this.key = key;
            this.val = val;
        }
    }

    private int hashCode(K key){
        int code = key.hashCode() & 0x7fffffff;
        code = code % bucket.size();
        return code;
    }
}
