package graph;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GraphGenerator {
    public static Graph generateConnectedUndirected(int vertices, int edges, long seed) {
        Graph graph = new Graph(vertices, false);
        Random random = new Random(seed);
        Set<String> existingEdges = new HashSet<>();


        for (int i = 1; i < vertices; i++) {
            int connectTo = random.nextInt(i);
            int weight = random.nextInt(100) + 1;
            graph.addEdge(i, connectTo, weight);
            existingEdges.add(getEdgeKey(i, connectTo, false));
        }

        int remainingEdges = edges - (vertices - 1);
        addRandomEdges(graph, vertices, remainingEdges, random, existingEdges, false);

        return graph;
    }


    public static Graph generateDirected(int vertices, int edges, long seed) {
        Graph graph = new Graph(vertices, true);
        Random random = new Random(seed);
        Set<String> existingEdges = new HashSet<>();

        addRandomEdges(graph, vertices, edges, random, existingEdges, true);
        return graph;
    }


    public static Graph generateDAG(int vertices, int edges, long seed) {
        Graph graph = new Graph(vertices, true);
        Random random = new Random(seed);
        Set<String> existingEdges = new HashSet<>();

        int addedEdges = 0;
        int maxEdges = vertices * (vertices - 1) / 2;
        edges = Math.min(edges, maxEdges);

        while (addedEdges < edges) {
            int u = random.nextInt(vertices - 1);
            int v = u + 1 + random.nextInt(vertices - 1 - u);

            String key = getEdgeKey(u, v, true);
            if (!existingEdges.contains(key)) {
                int weight = random.nextInt(100) + 1;
                graph.addEdge(u, v, weight);
                existingEdges.add(key);
                addedEdges++;
            }
        }
        return graph;
    }

    private static void addRandomEdges(Graph graph, int vertices, int edgesToAdd, Random random, Set<String> existingEdges, boolean isDirected) {
        int added = 0;
        while (added < edgesToAdd) {
            int u = random.nextInt(vertices);
            int v = random.nextInt(vertices);

            if (u != v) {
                String key = getEdgeKey(u, v, isDirected);
                if (!existingEdges.contains(key)) {
                    int weight = random.nextInt(100) + 1;
                    graph.addEdge(u, v, weight);
                    existingEdges.add(key);
                    added++;
                }
            }
        }
    }

    private static String getEdgeKey(int u, int v, boolean isDirected) {
        if (isDirected) {
            return u + "->" + v;
        } else {
            return Math.min(u, v) + "-" + Math.max(u, v);
        }
    }
}
