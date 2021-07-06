package academy.pocu.comp3500.lab8;

import academy.pocu.comp3500.lab8.maze.Point;

import java.util.ArrayList;

final public class Node {
    public ArrayList<Node> children = new ArrayList<>();
    public ArrayList<Point> path;
    public Point point;

    public Node(Point point, ArrayList<Point> path) {
        this.point = point;
        this.path = new ArrayList<>(path);
        this.path.add(point);
    }
}
