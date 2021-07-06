package academy.pocu.comp3500.lab8;

import academy.pocu.comp3500.lab8.maze.Point;

import java.util.ArrayList;
import java.util.List;

public final class MazeSolver {

    private static final int[][] MOVE = {
            {-1, 0},
            {0, 1},
            {0, -1},
            {1, 0},
    };

    public static List<Point> findPath(final char[][] maze, final Point start) {
        return findPathRecursive(maze, new Node(start, new ArrayList<>()));
    }

    private static List<Point> findPathRecursive(char[][] maze, Node node) {
        if (node == null) {
            return null;
        }

        System.out.println(node.point.getX() + " " + node.point.getY());
        if (maze[node.point.getY()][node.point.getX()] == 'E') {
            return node.path;
        }

        maze[node.point.getY()][node.point.getX()] = 'O';

        for (int i = 0; i < MOVE.length; ++i) {
            int x = MOVE[i][0] + node.point.getX();
            int y = MOVE[i][1] + node.point.getY();

            if (x < 0 || y < 0 || x >= maze[0].length || y >= maze.length) {
                continue;
            }

            if (maze[y][x] == 'x' || maze[y][x] == 'O') {
                continue;
            }

            node.children.add(new Node(new Point(x, y), node.path));
        }

        for (Node n : node.children) {
            List<Point> path = findPathRecursive(maze, n);
            if (path != null) {
                return path;
            }
        }

        return null;
    }
}