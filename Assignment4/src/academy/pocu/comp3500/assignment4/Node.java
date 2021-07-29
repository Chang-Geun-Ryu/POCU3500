package academy.pocu.comp3500.assignment4;

import academy.pocu.comp3500.assignment4.project.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Node {
    private final String title;
    private final ArrayList<Node> predecessors = new ArrayList<>(64);
    private int estimate;
    private int flowEdge;
    private int backEdge;

    public Node(final String title, final int estimate) {
        this.title = title;
        this.estimate = estimate;
        this.flowEdge = 0;
        this.backEdge = 0;
    }

    public String getTitle() {
        return this.title;
    }

    public void addPredecessor(final Node task) {
        this.predecessors.add(task);
    }

    public void addPredecessor(final Node... tasks) {
        for (Node task : tasks) {
            addPredecessor(task);
        }
    }

    public List<Node> getPredecessors() {
        return Collections.unmodifiableList(this.predecessors);
    }

    public int getEstimate() {
        return this.estimate;
    }
}
