package connectivity;

import graph.Edge;
import graph.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TopologicalSort {
    public static List<Integer> sort(Graph graph) {
        if (!graph.isDirected) {
            throw new IllegalArgumentException("Topological sort is for directed graphs only.");
        }

        int[] state = new int[graph.vertices];
        List<Integer> order = new ArrayList<>();

        for (int i = 0; i < graph.vertices; i++) {
            if (state[i] == 0) {
                if (!dfs(graph, i, state, order)) {
                    throw new IllegalStateException("Graph contains a cycle. Topological ordering is impossible.");
                }
            }
        }

        Collections.reverse(order);
        return order;
    }

    private static boolean dfs(Graph graph, int u, int[] state, List<Integer> order) {
        state[u] = 1;

        for (Edge edge : graph.adjList.get(u)) {
            int v = edge.destination;
            if (state[v] == 1) {
                return false;
            }
            if (state[v] == 0) {
                if (!dfs(graph, v, state, order)) {
                    return false;
                }
            }
        }

        state[u] = 2;
        order.add(u);
        return true;
    }
}
