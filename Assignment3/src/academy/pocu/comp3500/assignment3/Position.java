package academy.pocu.comp3500.assignment3;

import java.util.Objects;

final public class Position {
    public static final int[][] PWAN = {
            {0, 2},
            {0, 1},
            {1, 1},
            {-1, 1},
    };

    public static final int[][] KING = {
            {-1, 1},
            {-1, 0},
            {-1, -1},
            {0, 1},
            {0, -1},
            {1, 1},
            {1, 0},
            {1, -1}
    };

    public static final int[][] KNIGHT = {
            {-2, -1},
            {-2, 1},
            {-1, -2},
            {-1, 2},
            {1, -2},
            {1, 2},
            {2, -1},
            {2, 1}
    };


    public int posX;
    public int posY;

    public Position(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public void setPosition(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    @Override
    public boolean equals(Object o) {
        Position position = (Position) o;
        return posX == position.posX && posY == position.posY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(posX, posY);
    }
}
