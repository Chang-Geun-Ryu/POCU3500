package academy.pocu.comp3500.lab11;

import academy.pocu.comp3500.lab11.data.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class BallBoy {

    public static List<Point> findPath(final Point[] points) {

        return getNearPoint(new Point(0, 0), points, new HashMap<String, Point>(), new LinkedList<Point>());
    }

    public static LinkedList<Point> getNearPoint(Point finder, final Point[] points, HashMap<String, Point> discover, LinkedList<Point> path) {
        path.addLast(finder);

        double min = Double.MAX_VALUE;
        int minIndex = -1;

        while (path.size() < points.length + 1) {

            for (int i = 0; i < points.length; ++i) {
                if (discover.containsKey(points[i].toString())) {
                    continue;
                }

                double x = Math.pow(path.getLast().getX() - points[i].getX(), 2);
                double y = Math.pow(path.getLast().getY() - points[i].getY(), 2);

                double distance = Math.sqrt(x + y);

                if (min > distance) {
                    min = distance;
                    minIndex = i;
                }
            }

            min = Double.MAX_VALUE;
            path.addLast(points[minIndex]);
            discover.put(points[minIndex].toString(), points[minIndex]);
        }

        if (path.getLast().toString().hashCode() != finder.toString().hashCode()) {
            path.addLast(finder);
        }

        return path;
    }

    public static ArrayList<Edge> kruskal(final String[] nodes, final Edge[] edges) {
        DisjointSet set = new DisjointSet(nodes);

        ArrayList<Edge> mst = new ArrayList<>(edges.length);

        Arrays.sort(edges);

        for (int i = 0; i < edges.length; ++i) {
            String n1 = edges[i].getNode1();
            String n2 = edges[i].getNode2();

            String root1 = set.find(n1);
            String root2 = set.find(n2);

            if (!root1.equals(root2)) {
                mst.add(edges[i]);
                set.union(n1, n2);
            }
        }

        return mst;
    }
}