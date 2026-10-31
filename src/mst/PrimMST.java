package mst;

import graph.Edge;
import graph.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
public class PrimMST {
    public static MSTResult findMST(Graph graph) {
        long startTime = System.nanoTime();
        int operationsCount = 0;

        boolean[] visited = new boolean[graph.vertices];
        PriorityQueue<Edge> minHeap = new PriorityQueue<>();
        List<Edge> mstEdges = new ArrayList<>();
        long totalWeight = 0;
        int connectedVertices = 0;

        visited[0] = true;
        connectedVertices++;
        minHeap.addAll(graph.adjList.get(0));
        operationsCount += graph.adjList.get(0).size();

        while (!minHeap.isEmpty() && connectedVertices < graph.vertices) {
            Edge currentEdge = minHeap.poll();
            operationsCount++;

            int dest = currentEdge.destination;
            if (!visited[dest]) {
                visited[dest] = true;
                mstEdges.add(currentEdge);
                totalWeight += currentEdge.weight;
                connectedVertices++;

                for (Edge neighborEdge : graph.adjList.get(dest)) {
                    operationsCount++;
                    if (!visited[neighborEdge.destination]) {
                        minHeap.add(neighborEdge);
                    }
                }
            }
        }

        long endTime = System.nanoTime();
        boolean isConnected = (connectedVertices == graph.vertices);

        return new MSTResult(totalWeight, mstEdges, endTime - startTime, operationsCount, isConnected);
    }
}
