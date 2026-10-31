package connectivity;

import graph.Edge;
import graph.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class KosarajuSCC {
    public static List<List<Integer>> findSCCs(Graph graph) {
        if (!graph.isDirected) {
            throw new IllegalArgumentException("SCC is applicable to directed graphs only.");
        }

        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[graph.vertices];

        for (int i = 0; i < graph.vertices; i++) {
            if (!visited[i]) {
                fillOrder(graph, i, visited, stack);
            }
        }

        Graph reversedGraph = getTranspose(graph);

        Arrays.fill(visited, false);
        List<List<Integer>> sccList = new ArrayList<>();

        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (!visited[v]) {
                List<Integer> component = new ArrayList<>();
                dfsTranspose(reversedGraph, v, visited, component);
                sccList.add(component);
            }
        }

        return sccList;
    }

    private static void fillOrder(Graph graph, int u, boolean[] visited, Stack<Integer> stack) {
        visited[u] = true;
        for (Edge edge : graph.adjList.get(u)) {
            if (!visited[edge.destination]) {
                fillOrder(graph, edge.destination, visited, stack);
            }
        }
        stack.push(u);
    }

    private static void dfsTranspose(Graph graph, int u, boolean[] visited, List<Integer> component) {
        visited[u] = true;
        component.add(u);
        for (Edge edge : graph.adjList.get(u)) {
            if (!visited[edge.destination]) {
                dfsTranspose(graph, edge.destination, visited, component);
            }
        }
    }

    private static Graph getTranspose(Graph graph) {
        Graph reversedGraph = new Graph(graph.vertices, true);
        for (int u = 0; u < graph.vertices; u++) {
            for (Edge edge : graph.adjList.get(u)) {
                reversedGraph.addEdge(edge.destination, edge.source, edge.weight);
            }
        }
        return reversedGraph;
    }
}
