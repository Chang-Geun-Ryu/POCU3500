package academy.pocu.comp3500.lab8;

import academy.pocu.comp3500.lab8.maze.Point;

import java.util.ArrayList;
import java.util.LinkedList;
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

    private static LinkedList<Point> findPathRecursive(char[][] maze, int posX, int posY) {
        if (maze[posY][posX] == 'E') {
            LinkedList<Point> find = new LinkedList<>();
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

            LinkedList<Point> p = findPathRecursive(maze, x, y);
            if (p.size() > 0) {
                p.addFirst(new Point(posX, posY));
                return p;
            }
        }

        return new LinkedList<>();
    }
}