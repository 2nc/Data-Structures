import java.util.Objects;

/**
 * @author wnc
 */

public class ArrayDeque<T> {
    private int size;
    private int amount;
    private int index = 0;
    private T[] arrayT;
    private int nextLast;
    private int nextFirst;
    public ArrayDeque(){
        size = 0;
        amount = 8;
        nextFirst = 0;
        nextLast = 1;
        arrayT = (T[]) new Object[amount];
    }

    public ArrayDeque(ArrayDeque other){
        size = 0;
        amount = 8;
        nextFirst = 0;
        nextLast = 1;
        arrayT = (T[]) new Object[amount];

        for(int i = 0; i < other.size(); i++){
            addLast((T) other.get(i));
        }
    }

    public void addFirst(T item){
        if(size == amount){
            largeSize();
        }
        arrayT[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size = size + 1;
    }

    public void addLast(T item){
        if(size == amount){
            largeSize();
        }
        arrayT[nextLast] = item;
        nextLast = plusOne(nextLast);
        size = size + 1;
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

    public int amount() {return amount;}

    public void printDeque(){
        int index = plusOne(nextFirst);
        for(int i = 0; i < size; i++){
            System.out.print(arrayT[index] + " ");
            index = plusOne(index);
        }
        System.out.println();
    }

    public T removeFirst(){
        size = size - 1;
        if(amount >= 16 && 4 * size < amount){
            smallSize();
        }
        T returnT = arrayT[plusOne(nextFirst)];
        nextFirst = plusOne(nextFirst);
        return returnT;
    }

    public T removeLast(){
        size = size - 1;
        if(amount >= 16 && 4 * size < amount){
            smallSize();
        }
        T returnT = arrayT[minusOne(nextLast)];
        arrayT[minusOne(nextLast)] = null;
        nextLast = minusOne(nextLast);
        return returnT;
    }

    public T get(int index){
        int firstIndex = plusOne(nextFirst);
        for(int i = 0; i < index; i++){
            firstIndex = plusOne(firstIndex);
        }
        return arrayT[firstIndex];
    }

    private void largeSize(){
        int index = plusOne(nextFirst);
        T[] arrayN = (T[]) new Object[amount * 2];
        for(int i = 0; i < size; i++){
            arrayN[i] = arrayT[index];
            index = plusOne(index);
        }
        amount = amount * 2;
        nextFirst = amount - 1;
        nextLast = size;
        arrayT = arrayN;
    }

    private void smallSize(){
        int index = plusOne(nextFirst);
        T[] arrayN = (T[]) new Object[amount / 2];
        for(int i = 0; i <= size; i++){
            arrayN[i] = arrayT[index];
            index = plusOne(index);
        }
        amount = amount / 2;
        nextFirst = amount - 1;
        nextLast = size + 1;
        arrayT = arrayN;
    }

    private int minusOne(int index){
        int returnIndex;
        if(index == 0){
            returnIndex = amount - 1;
        }else{
            returnIndex = index - 1;
        }
        return returnIndex;
    }

    private int plusOne(int index){
        int returnIndex;
        if(index == amount - 1){
            returnIndex = 0;
        }else{
            returnIndex = index + 1;
        }
        return returnIndex;
    }

}
