package graph;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    public final int vertices;
    public final List<List<Edge>> adjList;
    public final List<Edge> allEdges;
    public final boolean isDirected;

    public Graph(int vertices, boolean isDirected) {
        this.vertices = vertices;
        this.isDirected = isDirected;
        this.adjList = new ArrayList<>(vertices);
        this.allEdges = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination, int weight) {
        Edge edge = new Edge(source, destination, weight);
        adjList.get(source).add(edge);
        allEdges.add(edge);

        if (!isDirected) {
            adjList.get(destination).add(new Edge(source, destination, weight));
        }
    }




}
