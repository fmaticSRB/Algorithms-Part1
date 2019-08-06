 /***********************************************************
 * AUTHOR: Filip Matic
 * Title: FastCollinearPoints.java
 * Description: Implements a fast solution for finding
 * collinear points in a set of points
 ************************************************************/
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FastCollinearPoints 
{

    private final Point[] points;
    private final int numberOfSegments;
    private final LineSegment[] lineSegments;

    public FastCollinearPoints(Point[] points)
    {
        if (points == null) throw new IllegalArgumentException();

        this.points = new Point[points.length];
        Point[] workingSetPoints = new Point[points.length];
        for (int i = 0; i< points.length; i++)
        {
            if (points[i] == null) throw new IllegalArgumentException();

            for (int k = 0; k < i; k ++)
            {
                if (this.points[k].compareTo(points[i]) == 0) throw new IllegalArgumentException();
            }
            this.points[i] = points[i];
            workingSetPoints[i] = points[i];
        }

        int numOfPoints = points.length;
        List<LineSegment> lineSegments = new LinkedList<>();
        List<Point> skipList = new LinkedList<>();
        for (int pid = 0; pid < numOfPoints; pid++)
        {
            Point originPoint = points[pid];
            if ( shouldSkip(skipList, originPoint) ) 
            {
                continue;
            }

            Arrays.sort(workingSetPoints, 0, numOfPoints, originPoint.slopeOrder());
            int collinearSetSize = 1;
            boolean startedEqualSegment = false;
            int segmentIndex = 1;
            for (int k = segmentIndex; k < numOfPoints - 1; k++)
            {
                if (originPoint.slopeTo(workingSetPoints[k]) == originPoint.slopeTo(workingSetPoints[k + 1])) 
                {
                    collinearSetSize++;
                    if ( !startedEqualSegment )
                    {
                        startedEqualSegment = true;
                        segmentIndex = k;
                    }
                } else if (startedEqualSegment) 
                {
                    break;
                }
            }
            if (collinearSetSize >= 3)
            {
                Point[] collinearSet = new Point[collinearSetSize + 1];
                collinearSet[0] = originPoint;
                for (int i = 0; i < collinearSetSize; i++)
                {
                    collinearSet[i + 1] = workingSetPoints[i + segmentIndex];
                }

                Arrays.sort(collinearSet, 0, collinearSetSize + 1);
                lineSegments.add( new LineSegment(collinearSet[0], collinearSet[collinearSetSize]));
                for (int i = 0; i <= collinearSetSize; i++)
                {
                    skipList.add(collinearSet[i]);
                }
            }
        }
        this.numberOfSegments = lineSegments.size();
        this.lineSegments = lineSegments.toArray(new LineSegment[this.numberOfSegments]);
    }

    private boolean shouldSkip(List<Point> points, Point p)
    {

        return points
                .stream()
                .filter(point -> point.compareTo(p) == 0)
                .findFirst()
                .isPresent();

    }

    private void swap(Object[] arr , int i, int j)
    {
        Object tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public int numberOfSegments()
    {
        return this.numberOfSegments;
    }


    public LineSegment[] segments()
    {
        return Arrays.copyOf(this.lineSegments, numberOfSegments());
    }


    public static void main(String[] args) 
    {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) 
        {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) 
        {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) 
        {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}