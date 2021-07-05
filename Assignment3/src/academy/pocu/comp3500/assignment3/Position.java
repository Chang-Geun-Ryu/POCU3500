package academy.pocu.comp3500.assignment3;

import java.util.Objects;

final public class Position {
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
