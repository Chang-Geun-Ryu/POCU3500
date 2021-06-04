package academy.pocu.comp3500.assignment2;

import academy.pocu.comp3500.assignment2.datastructure.ArrayList;

public final class Node {
    private String log;
    private ArrayList<Indent> indents = new ArrayList<>();

    public Node(String log) {
        this.log = log;
    }

    public String getLog() {
        return this.log;
    }

    public void setIndent(Indent indent) {
        this.indents.add(indent);
    }

    public ArrayList<Indent> getIndents() {
        return indents;
    }
}
