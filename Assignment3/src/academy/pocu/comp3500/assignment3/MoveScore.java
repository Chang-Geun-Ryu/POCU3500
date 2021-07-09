package academy.pocu.comp3500.assignment3;

final public class MoveScore {
    public int fromX;
    public int fromY;
    public int toX;
    public int toY;
    public int score;
    public int index;

    public MoveScore() {
    }

    public MoveScore(final int fromX, final int fromY, final int toX, final int toY, int score) {
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
        this.score = score;
    }
}
