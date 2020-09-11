/**
 * @author wnc
 */

public class LinkedListDeque<T> {
    Node sentinel;
    int size;
    private class Node{
        Node next;
        Node prev;
        T value;
        public Node(Node prev, Node next, T value){
            this.prev = prev;
            this.next = next;
            this.value = value;
        }
    }
    public LinkedListDeque(){
        size = 0;
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }
    public LinkedListDeque(LinkedListDeque other){
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;

        for(int i = 0; i < other.size(); i++){
            addLast((T) other.get(i));
        }
    }

    public void addFirst(T item){
        size = size + 1;
        Node after = sentinel.next;
        Node add = new Node(sentinel, sentinel.next, item);
        sentinel.next = add;
        after.prev = add;
    }

    public void addLast(T item){
        size = size + 1;
        Node before = sentinel.prev;
        Node add = new Node(sentinel.prev,sentinel, item);
        before.next = add;
        sentinel.prev = add;
    }

    public boolean isEmpty(){
        if(size == 0){
            return true;
        }
        return false;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        Node first = sentinel.next;
        for(int i = 0; i < size; i++){
            System.out.print(first.value + " ");
            first = first.next;
        }
        System.out.println();
    }

    public T removeFirst(){
        Node second = sentinel.next.next;
        Node first = sentinel.next;
        sentinel.next = second;
        second.prev = sentinel;
        first.next = null;
        first.prev = null;
        size = size - 1;
        return first.value;
    }

    public T removeLast(){
        Node secondToLast = sentinel.prev.prev;
        Node last = sentinel.prev;
        secondToLast.next = sentinel;
        sentinel.prev = secondToLast;
        last.next = null;
        last.prev = null;
        size = size - 1;
        return last.value;
    }

    public T get(int index){
        Node first = sentinel.next;
        for(int i = 0; i < index; i++){
            first = first.next;
        }
        return first.value;
    }





}
