package academy.pocu.comp3500.lab11;

import academy.pocu.comp3500.lab11.data.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class BallBoy {
    public static List<Point> findPath(final Point[] points) {
        HashMap<String, Point> nodeMap = new HashMap<>();
        ArrayList<Edge> edgeList = new ArrayList<>();
        HashMap<String, ArrayList<String>> treeMap = new HashMap<>();
        ArrayList<Point> list = new ArrayList<>();
        HashMap<String, Point> discovered = new HashMap<>();

        createNode(points, nodeMap, edgeList);

        // mst 생성
        ArrayList<Edge> mst = kruskal(nodeMap, edgeList.toArray(new Edge[0]), treeMap);
        Stack<String> stack = new Stack<>();
        stack.add("0");


        // 순회
        preorderTraverseRecursive(treeMap, "0", nodeMap, discovered, list);
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
            edgeList.add(new Edge("0", s, sqrt * 10000.0));
        }

        for (int i = 0; i < points.length; ++i) {
            String s = String.format("%d", i + 1);
            for (int j = i + 1; j < points.length; ++j) {
                double x = Math.pow(points[i].getX() - points[j].getX(), 2);
                double y = Math.pow(points[i].getY() - points[j].getY(), 2);
                double sqrt = Math.sqrt(x + y);
                String ss = String.format("%d", j + 1);
                edgeList.add(new Edge(s, ss, sqrt * 10000.0));
            }
        }
    }

    private static ArrayList<Edge> kruskal(final HashMap<String, Point> nodes, final Edge[] edges, HashMap<String, ArrayList<String>> treeMap) {
        DisjointSet set = new DisjointSet(nodes.keySet().toArray(new String[0]));

        ArrayList<Edge> mst = new ArrayList<>(edges.length);

//        Arrays.sort(edges);
        quickSortRecursive(edges, 0, edges.length - 1);

        for (int i = 0; i < edges.length; ++i) {
            String n1 = edges[i].getNode1();
            String n2 = edges[i].getNode2();

            String root1 = set.find(n1);
            String root2 = set.find(n2);

            if (!root1.equals(root2)) {
                mst.add(edges[i]);
                set.union(n1, n2);

                if (treeMap.containsKey(edges[i].getNode1())) {
                    treeMap.get(edges[i].getNode1()).add(edges[i].getNode2());
                } else {
                    ArrayList<String> child = new ArrayList<>();
                    child.add(edges[i].getNode2());
                    treeMap.put(edges[i].getNode1(), child);
                }

//                if (treeMap.containsKey(edges[i].getNode2())) {
//                    treeMap.get(edges[i].getNode2()).add(edges[i].getNode1());
//                } else {
//                    ArrayList<String> child = new ArrayList<>();
//                    child.add(edges[i].getNode1());
//                    treeMap.put(edges[i].getNode2(), child);
//                }

            }
        }

        return mst;
    }

    private static void preorderTraverseRecursive(HashMap<String, ArrayList<String>> treeMap, String parent, HashMap<String, Point> nodeMap, HashMap<String, Point> discovered, ArrayList<Point> traverseList) {
        if (discovered.containsKey(parent) == false) {
            traverseList.add(nodeMap.get(parent));
            discovered.put(parent, nodeMap.get(parent));
        }

        if (treeMap.containsKey(parent)) {
            for (var name : treeMap.get(parent)) {
                preorderTraverseRecursive(treeMap, name, nodeMap, discovered, traverseList);

                if (discovered.containsKey(parent) == false) {
                    traverseList.add(nodeMap.get(parent));
                    discovered.put(parent, nodeMap.get(parent));
                }
            }
        }
    }

    private static void quickSortRecursive(Edge[] edges, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivot = getPivot(edges, left, right);

        quickSortRecursive(edges, left, pivot - 1);
        quickSortRecursive(edges, pivot + 1, right);
    }

    private static int getPivot(Edge[] edges, int left, int right) {
        int i = left - 1;

        for (int index = left; index < right; ++index) {
            if (edges[index].getWeight() < edges[right].getWeight()) {
                Edge temp = edges[++i];
                edges[i] = edges[index];
                edges[index] = temp;
            }
        }

        Edge temp = edges[right];
        edges[right] = edges[++i];
        edges[i] = temp;

        return i;
    }
}