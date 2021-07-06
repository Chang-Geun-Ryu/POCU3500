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
        return findPathRecursive(maze, start.getX(), start.getY());
    }

    private static List<Point> findPathRecursive(char[][] maze, int posX, int posY) {
        if (maze[posY][posX] == 'E') {
            ArrayList<Point> find = new ArrayList<>();
            find.add(new Point(posX, posY));
            return find;
        }

        maze[posY][posX] = 'O';

        for (int i = 0; i < MOVE.length; ++i) {
            int x = MOVE[i][0] + posX;
            int y = MOVE[i][1] + posY;

            if (x < 0 || y < 0 || x >= maze[0].length || y >= maze.length) {
                continue;
            }

            if (maze[y][x] == 'x' || maze[y][x] == 'O') {
                continue;
            }

            List<Point> p = findPathRecursive(maze, x, y);
            if (p.size() > 0) {
                p.add(0, new Point(posX, posY));
                return p;
            }
        }

        return new ArrayList<>();
    }

    private static List<Point> findPathRecursive1(char[][] maze, Node node) {
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

            Node n = new Node(new Point(x, y), node.path);
            node.children.add(n);

            List<Point> path = findPathRecursive1(maze, n);
            if (path.size() > 0) {
                return path;
            }
        }

        return new ArrayList<>();
    }
}