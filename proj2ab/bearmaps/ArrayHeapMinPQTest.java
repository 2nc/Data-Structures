package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.introcs.StdRandom;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ArrayHeapMinPQTest {

    @Test
    public void addGetSmallTest(){
        ArrayHeapMinPQ<Integer> a = new ArrayHeapMinPQ<>();
        assertEquals(0, a.size());
        a.add(1,1);
        a.add(2,2);
        a.add(3,3);
        assertEquals(3, a.size());
        for(int i = 4; i < 7; i++){
            a.add(i, i);
        }
        a.add(10, 0.5);
        assertEquals(7, a.size());
        assertEquals(10, (int) a.getSmallest());
    }

    @Test
    public void removeTest(){
        ArrayHeapMinPQ<Integer> a = new ArrayHeapMinPQ<>();
        for(int i = 5; i > 0; i--){
            a.add(i, i);
        }
        assertEquals(1, (int) a.removeSmallest());
        assertEquals(2, (int) a.removeSmallest());
        assertEquals(3, (int) a.removeSmallest());
        assertEquals(4, (int) a.removeSmallest());
        assertEquals(5, (int) a.removeSmallest());
        //a.getSmallest();
        //a.removeSmallest();
    }

    @Test
    public void changeP1Test(){
        ArrayHeapMinPQ<Integer> a = new ArrayHeapMinPQ<>();
        for(int i = 5; i > 0; i--){
            a.add(i, i);
        }
        a.changePriority(2, 0.5);
        assertEquals(2, (int) a.removeSmallest());
    }

    @Test
    public void changeP2Test(){
        ArrayHeapMinPQ<Integer> a = new ArrayHeapMinPQ<>();
        for(int i = 5; i > 0; i--){
            a.add(i, i);
        }
        a.changePriority(2, 3.9);
        assertEquals(2, (int) a.removeSmallest());
    }

    @Test
    public void timeTest(){
        ArrayHeapMinPQ<Double> a = new ArrayHeapMinPQ<>();
        for(int i = 0; i < 1000000; i++){
            double ran = StdRandom.uniform(1,1000000);
            a.add(ran * Math.random(), ran);
        }
        Stopwatch sw = new Stopwatch();
        for(int j = 0; j < 1000; j++){
            a.removeSmallest();
        }
        System.out.println("Total time elapsed: " + sw.elapsedTime() +  " seconds by ArrayHeapMinPQ");
        NaiveMinPQ<Double> b = new NaiveMinPQ<>();
        for(int i = 0; i < 1000000; i++){
            double ran = StdRandom.uniform(1,1000000);
            b.add(ran * Math.random(), ran);
        }
        Stopwatch sw1 = new Stopwatch();
        for(int j = 0; j < 1000; j++){
            b.removeSmallest();
        }
        System.out.println("Total time elapsed: " + sw1.elapsedTime() +  " seconds by NaiveMinPQ");

    }
}
