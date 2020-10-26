package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KDTreeTest {

    Random r = new Random(500);

    private KDTree returnLectureTree(){
        Point p1 = new Point(2, 3);
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 2);
        Point p4 = new Point(4, 5);
        Point p5 = new Point(3, 3);
        Point p6 = new Point(1, 5);
        Point p7 = new Point(4, 4);

        KDTree k = new KDTree(List.of(p1, p2, p3, p4, p5, p6, p7));
        return k;
    }

    private Point createRandomPoint(){
        double x = r.nextDouble();
        double y = r.nextDouble();
        return new Point(x, y);
    }

    private List<Point> creatListPoint(int num){
        ArrayList aL = new ArrayList();
        for(int i = 0; i < num; i++){
            aL.add(createRandomPoint());
        }
        return aL;
    }

    @Test
    public void testManyPoints(){
        List<Point> points = creatListPoint(1000);
        NaivePointSet nPS = new NaivePointSet(points);
        KDTree kT = new KDTree(points);
        List<Point> testPoints = creatListPoint(100);
        for(Point p: testPoints){
            Point expect = nPS.nearest(p.getX(), p.getY());
            Point actual = kT.nearest(p.getX(), p.getY());
            assertEquals(expect, actual);
        }
    }

    @Test
    public void testTime(){
        List<Point> points = creatListPoint(100000);
        NaivePointSet nPS = new NaivePointSet(points);
        KDTree kT = new KDTree(points);
        List<Point> testPoints = creatListPoint(10000);
        Stopwatch sw1 = new Stopwatch();
        for(Point p: testPoints){
            nPS.nearest(p.getX(), p.getY());
        }
        System.out.println("Naive time:" + sw1.elapsedTime());
        Stopwatch sw = new Stopwatch();
        for(Point p: testPoints){
            kT.nearest(p.getX(), p.getY());
        }
        System.out.println("KDTree time:" + sw.elapsedTime());

    }

    @Test
    public void testNearest(){
        KDTree returnK = returnLectureTree();
        Point near = new Point(1, 5);
        Point kdNear = returnK.nearest(0, 7);
        assertEquals(near, kdNear);
    }

}
