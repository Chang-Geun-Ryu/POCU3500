package academy.pocu.comp3500.assignment4;

import academy.pocu.comp3500.assignment4.project.Task;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private final String title;
    private final ArrayList<Node> predecessors = new ArrayList<>(64);
    private int estimate;
    private int flowVolume;

    public Node(final String title, final int estimate) {
        this.title = title;
        this.estimate = estimate;
        this.flowVolume = 0;
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
        return this.predecessors;
    }

    public int getEstimate() {
        return this.estimate;
    }

    public int canFlowVolume() {
        return this.estimate - this.flowVolume;
    }

    public void addFlowVolume(int flowVolume) {
        this.flowVolume += flowVolume;
        assert (this.estimate >= this.flowVolume);
    }

    public int canBackVolume() {
        return this.flowVolume;
    }

    public void addBackVolume(int backVolume) {
        this.flowVolume -= backVolume;
        assert (this.flowVolume >= 0);
    }
}
