/**
 * @author wnc
 */
package bearmaps;

import java.util.ArrayList;
import java.util.List;

public class NaivePointSet implements PointSet {
    List<Point> point = new ArrayList<>();
    public NaivePointSet(List<Point> points){
        for(Point p: points){
            point.add(p);
        }
    }
    @Override
    public Point nearest(double x, double y) {
        Point goal = new Point(x, y);
        Point bestPoint = point.get(0);
        double best = Point.distance(goal, bestPoint);
        for(int i = 0; i < point.size(); i++){
            Point iPoint = point.get(i);
            if(Point.distance(goal, iPoint) < best){
                best = Point.distance(goal, iPoint);
                bestPoint = point.get(i);
            }
        }
        return bestPoint;
    }
    
    public static void main(String args[]){
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);
        NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3));
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        System.out.println(ret.getX()); // evaluates to 3.3
        System.out.println(ret.getY()); // evaluates to 4.4
    }
}
