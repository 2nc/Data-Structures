/**
 * @author wnc
 * @source
 */
package bearmaps;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {

    private ArrayList<pNode> items;
    private Set<T> s = new HashSet<>();
    private int size = 0;
    public ArrayHeapMinPQ(){
        items = new ArrayList<>();
        items.add(0,new pNode(null, 0));
    }

    @Override
    public void add(T item, double priority) {
        if(contains(item)){
            throw new IllegalArgumentException();
        }
        items.add(new pNode(item, priority));
        s.add(item);
        swimUp(size());
    }

    private void swimUp(int i){
        if(i == 1){
            return;
        }
        double pFather = items.get(i / 2).nPriority;
        double pChild = items.get(i).nPriority;
        if(pChild < pFather){
            pNode temp = items.get(i);
            items.set(i, items.get(i / 2));
            items.set(i / 2, temp);
        }
        swimUp(i / 2);
    }

    /*
    private void addItem(T item, double priority, int num){
        if(items.get(num) == null){
            items.add(num, new pNode(item, priority));
            size = size + 1;
            if(num >= maxIndex){
                maxIndex = num;
            }
        } else if(priority >=items.get(num).getnPriority()){
            addItem(item, priority, 2 * num + 1);
        } else {
            addItem(item, priority, 2 * num);
        }
    }
     */
    @Override
    public boolean contains(T item) {
        return s.contains(item);
    }

    @Override
    public T getSmallest() {
        if(size() == 0){
            throw new NoSuchElementException();
        }
        return items.get(1).getnItem();
    }

    @Override
    public T removeSmallest() {
        if(size() == 0){
            throw new NoSuchElementException();
        }
        T returnItem = items.get(1).getnItem();
        items.set(1, items.get(size()));
        items.remove(size());
        s.remove(returnItem);
        if(size() >= 1){
            sink(1);
        }
        return returnItem;
    }

    private void sink(int parent){
        int left = 2 * parent;
        int right = 2 * parent + 1;
        pNode parentPNode = items.get(parent);
        if(hasNoChild(parent)){
            return;
        }else if (hasOneChild(parent)){
            if(parentPNode.compareTo(items.get(left)) > 0){
                swap(parent, left);
            }
            return;
        } else {
            pNode smallP = getSmaller(parent);
            if(parentPNode.compareTo(smallP) > 0){
                int indexSmallItem = indexOfSmallItem(parent);
                swap(parent, indexSmallItem);
                sink(indexSmallItem);
            } else {
                return;
            }
        }
    }

    private boolean hasNoChild(int parent){
        int left = 2 * parent;
        if(size() < left){
            return true;
        }
        return false;
    }

    private boolean hasOneChild(int parent){
        int left = 2 * parent;
        if(size() == left){
            return true;
        }
        return false;
    }

    private pNode getSmaller(int parent){
        int left = 2 * parent;
        int right = 2 * parent + 1;
        if(items.get(left).compareTo(items.get(right)) <= 0){
            return items.get(left);
        }
        return items.get(right);
    }


    private int indexOfSmallItem(int parent){
        int left = 2 * parent;
        int right = 2 * parent + 1;
        if(items.get(left).compareTo(items.get(right)) <= 0){
            return left;
        }
        return right;
    }

    private void swap(int p1, int p2){
        pNode ptemp = items.get(p1);
        items.set(p1, items.get(p2));
        items.set(p2, ptemp);
    }

    @Override
    public int size() {
        return s.size();
    }

    @Override
    public void changePriority(T item, double priority) {
        if(!contains(item)){
            throw new NoSuchElementException("Can't changePriority");
        }
        /*
        pNode cNode = new pNode(item, priority);
        int index = find(cNode, 1);
        double originP = items.get(index).nPriority;
        items.set(index,cNode);
        if(priority > originP){
            sink(index);
        }else{
            swimUp(index);
        }
        */
    }

    private int find(pNode p, int index){
        pNode getNode = items.get(index);
        if(getNode.equals(p)){
            return index;
        }
        if(p.compareTo(getNode) > 0){
           return find(p, 2 * index + 1);
        }else{
           return find(p, 2 * index);
        }
    }

    private class pNode implements Comparable<pNode>{
        private T nItem;
        private double nPriority;
        pNode(T item, double priority){
            nItem = item;
            nPriority = priority;
        }

        double getnPriority(){
            return nPriority;
        }

        T getnItem(){
            return nItem;
        }

        @Override
        public int compareTo(pNode other) {
            if(other == null){
                return -1;
            }
            return Double.compare(this.getnPriority(), other.getnPriority());
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || o.getClass() != this.getClass()) {
                return false;
            } else {
                return ((pNode) o).getnItem().equals(getnItem());
            }
        }

        @Override
        public int hashCode() {
            return nItem.hashCode();
        }
    }

    @Test
    public void addTest(){
        ArrayHeapMinPQ<Integer> a = new ArrayHeapMinPQ<>();
        assertEquals(0, a.size());
        a.add(1,1);
        a.add(2,2);
        a.add(3,3);
        assertEquals(3, a.size());
        Double[] m = new Double[a.size() + 1];
        for(int i = 1; i <= a.size(); i++){
            m[i] = a.items.get(i).getnPriority();
        }
        PrintHeapDemo.printFancyHeapDrawing(m);
        for(int i = 4; i < 7; i++){
            a.add(i, i);
        }
        Double[] m1 = new Double[a.size() + 1];
        for(int i = 1; i <= a.size(); i++){
            m1[i] = a.items.get(i).getnPriority();
        }
        PrintHeapDemo.printFancyHeapDrawing(m1);
        a.add(10, 0.5);
        Double[] m2 = new Double[a.size() + 1];
        for(int i = 1; i <= a.size(); i++){
            m2[i] = a.items.get(i).getnPriority();
        }
        PrintHeapDemo.printFancyHeapDrawing(m2);
    }

    @Test
    public void removeTest(){
        ArrayHeapMinPQ<Integer> a = new ArrayHeapMinPQ<>();
        for(int i = 5; i > 0; i--){
            a.add(i, i);
        }
        Double[] m = new Double[a.size() + 1];
        for(int i = 1; i <= a.size(); i++){
            m[i] = a.items.get(i).getnPriority();
        }
        PrintHeapDemo.printFancyHeapDrawing(m);
    }
}
