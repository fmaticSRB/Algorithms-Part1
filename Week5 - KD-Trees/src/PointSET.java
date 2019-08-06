 /***********************************************************
 * AUTHOR: Filip Matic
 * Title: PointSET.java
 * Description: Brute-Force solution of 2D range and 
 * nearest calculations.
 ************************************************************/
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.List;


public class PointSET 
{

    private SET<Point2D> points = new SET<>();

    // is the set empty?
    public boolean isEmpty() 
    {   
        return points.isEmpty();
    }

    // number of points in the set
    public int size() 
    {   
        return points.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) 
    {   
        checkNotNull(p, "Not supported to insert null as point");
        points.add(p);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) 
    {   
        checkNotNull(p, "Null is never contained in a PointSET");
        return points.contains(p);
    }

    // draw all points to standard draw
    public void draw() 
    {   
        for (Point2D point : points) {
            StdDraw.point(point.x(), point.y());
        }
    }

    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) 
    {   
        checkNotNull(rect, "Can't calculate range for a rect will value null");

        List<Point2D> solution = new ArrayList<>();
        for (Point2D point : points) 
        {
            if (rect.contains(point)) 
            {
                solution.add(point);
            }
        }
        return solution;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) 
    {   
        checkNotNull(p, "Can't calculate nearest point to a point with value null");

        Point2D nearestPoint = null;
        for (Point2D point : points) {
            if (nearestPoint == null || point.distanceTo(p) < nearestPoint.distanceTo(p)) 
            {
                nearestPoint = point;
            }
        }
        return nearestPoint;
    }

    private static void checkNotNull(Object o, String messageIfObjectIsNull) 
    {
        if (o == null) throw new NullPointerException(messageIfObjectIsNull);
    }

}