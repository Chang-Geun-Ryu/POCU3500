package academy.pocu.comp3500.lab11;

import academy.pocu.comp3500.lab11.data.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BallBoy {

    public static ArrayList<Edge> run(final String[] nodes, final Edge[] edges) {
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

    public static List<Point> findPath(final Point[] points) {
        return null;
    }
}