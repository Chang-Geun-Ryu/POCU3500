package academy.pocu.comp3500.assignment4;

import academy.pocu.comp3500.assignment4.project.Task;

public class Node {
    private Task task;
    private int flowVolume;

    public Node(final Task task) {
        this.task = task;
        this.flowVolume = 0;
    }

    public String getTitle() {
        return this.task.getTitle();
    }

    public int getEstimate() {
        return this.task.getEstimate();
    }

    public int canFlowVolume() {
        return this.task.getEstimate() - this.flowVolume;
    }

    public void addFlowVolume(int flowVolume) {
        this.flowVolume += flowVolume;
        assert (this.task.getEstimate() >= this.flowVolume);
    }

    public int canBackVolume() {
        return this.flowVolume;
    }

    public void addBackVolume(int backVolume) {
        this.flowVolume -= backVolume;
        assert (this.flowVolume >= 0);
    }
}
