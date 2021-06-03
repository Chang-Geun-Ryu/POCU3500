package academy.pocu.comp3500.assignment2;

public final class Indent {
    private int startIndex;
    private int endIndex;
    private boolean isShowLog = true;

    public Indent(final int startIndex) {
        this.startIndex = startIndex;
    }

    public void discard() {
        this.isShowLog = false;
    }

    public void unindent(final int endIndex) {
        this.endIndex = endIndex;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public boolean isShowLog() {
        return isShowLog;
    }
}