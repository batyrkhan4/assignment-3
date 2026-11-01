package shortestpath;

import graph.Edge;
import graph.Graph;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Dijkstra {
    static class Node implements Comparable<Node> {
        int vertex;
        int distance;

        Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    public static DijkstraResult findShortestPaths(Graph graph, int source) {
        long startTime = System.nanoTime();
        int operationsCount = 0;

        int[] distances = new int[graph.vertices];
        int[] parents = new int[graph.vertices];
        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(parents, -1);

        distances[source] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(source, 0));
        operationsCount++;

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            operationsCount++;
            int u = current.vertex;

            if (current.distance > distances[u]) {
                continue;
            }

            for (Edge edge : graph.adjList.get(u)) {
                operationsCount++;

                if (edge.weight < 0) {
                    throw new IllegalArgumentException("Dijkstra's algorithm is unsuitable for graphs with negative edge weights.");
                }

                int v = edge.destination;
                int newDist = distances[u] + edge.weight;

                if (newDist < distances[v]) {
                    distances[v] = newDist;
                    parents[v] = u;
                    pq.add(new Node(v, newDist));
                    operationsCount++;
                }
            }
        }

        long endTime = System.nanoTime();
        return new DijkstraResult(distances, parents, endTime - startTime, operationsCount);
    }
}
