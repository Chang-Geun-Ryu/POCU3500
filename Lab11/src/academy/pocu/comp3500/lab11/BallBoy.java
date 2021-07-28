package academy.pocu.comp3500.lab11;

import academy.pocu.comp3500.lab11.data.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BallBoy {
    public static List<Point> findPath(final Point[] points) {
        HashMap<String, Point> nodeMap = new HashMap<>();
        ArrayList<Edge> edgeList = new ArrayList<>();
        HashMap<String, ArrayList<String>> treeMap = new HashMap<>();
        ArrayList<Point> list = new ArrayList<>();

        createNode(points, nodeMap, edgeList);

        // mst 생성
        kruskal(nodeMap, edgeList.toArray(new Edge[0]), treeMap);

        // 순회
        preorderTraverseRecursive(treeMap, "0", nodeMap, list);
        if (list.get(list.size() - 1).toString().hashCode() != nodeMap.get("0").toString().hashCode()) {
            list.add(nodeMap.get("0"));
        }

        return list;
    }

    private static void createNode(final Point[] points, HashMap<String, Point> nodeMap, ArrayList<Edge> edgeList) {
        nodeMap.put("0", new Point(0, 0));

        for (int i = 1; i < points.length + 1; ++i) {
            String s = String.format("%d", i);
            nodeMap.put(s, points[i - 1]);

            double sqrt = Math.sqrt(Math.pow(points[i - 1].getX(), 2) + Math.pow(points[i - 1].getY(), 2));
            edgeList.add(new Edge("0", s, sqrt));
        }

        for (int i = 0; i < points.length; ++i) {
            String s = String.format("%d", i + 1);
            for (int j = i + 1; j < points.length; ++j) {
                double x = Math.pow(points[i].getX() - points[j].getX(), 2);
                double y = Math.pow(points[i].getY() - points[j].getY(), 2);
                double sqrt = Math.sqrt(x + y);
                String ss = String.format("%d", j + 1);
                edgeList.add(new Edge(s, ss, sqrt));
            }
        }
    }

    private static void kruskal(final HashMap<String, Point> nodes, final Edge[] edges, HashMap<String, ArrayList<String>> treeMap) {
        DisjointSet set = new DisjointSet(nodes.keySet().toArray(new String[0]));

//        ArrayList<Edge> mst = new ArrayList<>(edges.length);

        Arrays.sort(edges);

        for (int i = 0; i < edges.length; ++i) {
            String n1 = edges[i].getNode1();
            String n2 = edges[i].getNode2();

            String root1 = set.find(n1);
            String root2 = set.find(n2);

            if (!root1.equals(root2)) {
//                mst.add(edges[i]);
                set.union(n1, n2);

                if (treeMap.containsKey(edges[i].getNode1())) {
                    treeMap.get(edges[i].getNode1()).add(edges[i].getNode2());
                } else {
                    ArrayList<String> child = new ArrayList<>();
                    child.add(edges[i].getNode2());
                    treeMap.put(edges[i].getNode1(), child);
                }
            }
        }

//        return mst;
    }

    private static void preorderTraverseRecursive(HashMap<String, ArrayList<String>> treeMap, String parent, HashMap<String, Point> nodeMap, ArrayList<Point> traverseList) {
        traverseList.add(nodeMap.get(parent));
        if (treeMap.containsKey(parent)) {
            for (var name : treeMap.get(parent)) {
                preorderTraverseRecursive(treeMap, name, nodeMap, traverseList);
            }
        }
    }
}