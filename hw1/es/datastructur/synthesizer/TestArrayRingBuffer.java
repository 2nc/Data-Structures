package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author wnc
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        assertEquals(true, arb.isEmpty());
        assertEquals(false, arb.isFull());
        for(int i = 0; i < 10; i++){
            arb.enqueue(i);
        }
        for(int i = 0; i < 5; i++){
            System.out.print(arb.dequeue() + " ");
        }
        System.out.println();
        arb.enqueue(10);
        arb.enqueue(11);
        assertEquals(7, arb.fillCount());
        while(!arb.isEmpty()){
            System.out.print(arb.dequeue() + " ");
        }
    }
}
