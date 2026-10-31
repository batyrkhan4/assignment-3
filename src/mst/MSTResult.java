package mst;

import graph.Edge;

import java.awt.*;
import java.util.List;

public class MSTResult {
    public final long totalWeight;
    public final List<Edge> mstEdges;
    public final long executionTime;
    public final int operationsCount;
    public final boolean isConnected;

    public MSTResult(long totalWeight, List<Edge> mstEdges, long executionTime, int operationsCount, boolean isConnected) {
        this.totalWeight = totalWeight;
        this.mstEdges = mstEdges;
        this.executionTime = executionTime;
        this.operationsCount = operationsCount;
        this.isConnected = isConnected;
    }


}
