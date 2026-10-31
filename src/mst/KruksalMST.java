package mst;

import graph.Graph;
import dsu.DSU;
import graph.Edge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KruksalMST {
    public static MSTResult findMST(Graph graph) {
        long startTime = System.nanoTime();
        int operationsCount = 0;

        List<Edge> edges = new ArrayList<>(graph.allEdges);

        Collections.sort(edges);
        operationsCount += edges.size();

        DSU dsu = new DSU(graph.vertices);
        List<Edge> mstEdges = new ArrayList<>();
        long totalWeight = 0;

        for (Edge edge : edges) {
            operationsCount++;
            if (dsu.union(edge.source, edge.destination)) {
                mstEdges.add(edge);
                totalWeight += edge.weight;
            }
        }

        long endTime = System.nanoTime();
        return new MSTResult(totalWeight, mstEdges, endTime - startTime, operationsCount, dsu.isConnected());
    }
}
