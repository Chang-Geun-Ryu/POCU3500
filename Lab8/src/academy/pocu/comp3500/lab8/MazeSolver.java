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
        List<Point> list = new ArrayList<>();

        return findPathRecursive(maze, start, list);
    }

    private static List<Point> findPathRecursive(char[][] maze, Point point, List<Point> list) {
        if (point == null) {
            return null;
        }

        list.add(point);

        if (maze[point.getY()][point.getX()] == 'E') {
            return list;
        }

        maze[point.getY()][point.getX()] = 'O';

        for (int i = 0; i < MOVE.length; ++i) {
            int x = MOVE[i][0] + point.getX();
            int y = MOVE[i][1] + point.getY();

            if (x < 0 || y < 0 || x >= maze[0].length || y >= maze.length) {
                continue;
            }

            if (maze[y][x] == 'x' || maze[y][x] == 'O') {
                continue;
            }

            List<Point> tempList = findPathRecursive(maze, new Point(x, y), list);

            if (tempList != null && maze[tempList.get(tempList.size() - 1).getY()][tempList.get(tempList.size() - 1).getX()] == 'E') {
                return tempList;
            }
        }

        return null;
    }
}