package graph;

public class Edge implements Comparable<Edge> {
    public final int source;
    public final int destination;
    public final int weight;

    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.weight, o.weight);
    }

}
