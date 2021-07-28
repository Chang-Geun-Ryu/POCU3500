package academy.pocu.comp3500.lab11;

public final class Edge implements Comparable<Edge> {
    private final String node1;
    private final String node2;
    private final double weight;

    public Edge(final String node1,
                final String node2,
                final double weight) {
        this.node1 = node1;
        this.node2 = node2;
        this.weight = weight;
    }

    public String getNode1() {
        return this.node1;
    }

    public String getNode2() {
        return this.node2;
    }

    public double getWeight() {
        return this.weight;
    }

    @Override
    public int compareTo(Edge e) {
        return (int) (this.weight * 10000 - e.weight * 10000);
    }
}
