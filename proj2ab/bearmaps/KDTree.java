package bearmaps;

import java.util.ArrayList;
import java.util.List;

public class KDTree implements PointSet {
    private static final boolean horizontal = true;
    private static final boolean vertical = false;
    private Node root;
    private class Node{
        private Point p;
        private Node leftChild;
        private Node rightChild;
        private boolean oritation;
        public Node(Point p, boolean orientation){
            this.p = p;
            this.oritation = orientation;
        }
    }

    private int comparePoints(Point a, Point b, boolean oritation){
        if(oritation == horizontal){
            return Double.compare(a.getX(), b.getX());
        } else {
            return Double.compare(a.getY(), b.getY());
        }
    }
    private Node add(Point p, Node n, boolean oritation){
        if(n == null){
            return new Node(p, oritation);
        }
        if(p.getX() == n.p.getX() && p.getY() == n.p.getY()){
            return n;
        }
        int result = comparePoints(p, n.p, oritation);
        if(result < 0){
            n.leftChild = add(p, n.leftChild, !oritation);
        } else if(result >= 0){
            n.rightChild = add(p, n.rightChild, !oritation);
        }
        return n;
    }

    public KDTree(List<Point> points){
        for(Point p: points){
            root = add(p, root, horizontal);
        }
    }

    private Node nearestHelper(Node n, Point goal, Node best){
        if(n == null){
            return best;
        }
        if(Point.distance(n.p, goal) < Point.distance(goal, best.p)){
            best = n;
        }
        int result = comparePoints(goal, n.p, n.oritation);
        Node goodSide;
        Node badSide;
        if(result < 0){
            goodSide = n.leftChild;
            badSide = n.rightChild;
        } else {
            goodSide = n.rightChild;
            badSide = n.leftChild;
        }
        best = nearestHelper(goodSide, goal, best);
        Point bestBadSidePoint = bestBadSidePoint(n, goal);
        if(Point.distance(bestBadSidePoint, goal) < Point.distance(best.p, goal)){
            best = nearestHelper(badSide, goal, best);
        }
        return best;
    }

    private Point bestBadSidePoint(Node n, Point goal){
        Point bestBadSidePoint;
        if(n.oritation){
            bestBadSidePoint = new Point(n.p.getX(),goal.getY());
        } else {
            bestBadSidePoint = new Point(goal.getX(), n.p.getY());
        }
        return bestBadSidePoint;
    }
    /*
    private Node nearestHelper(Node n, Point goal, Node best){
        if(n == null){
            return best;
        }
        if(Point.distance(n.p, goal) < Point.distance(goal, best.p)){
            best = n;
        }
        best = nearestHelper(n.leftChild, goal, best);
        best = nearestHelper(n.rightChild, goal, best);
        return best;
    }
     */

    @Override
    public Point nearest(double x, double y) {
        Node goal = new Node(new Point(x, y), horizontal);
        Node best = nearestHelper(root, goal.p, root);
        return best.p;
    }

    public static void main(String[] args){
        /*
        Point p1 = new Point(2, 3);
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 2);
        Point p4 = new Point(4, 5);
        Point p5 = new Point(3, 3);
        Point p6 = new Point(1, 5);
        Point p7 = new Point(4, 4);

        KDTree k = new KDTree(List.of(p1, p2, p3, p4, p5, p6, p7));
        System.out.println();

         */
    }
}
