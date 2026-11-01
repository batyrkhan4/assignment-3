package shortestpath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DijkstraResult {
    public final int[] distances;
    public final int[] parents;
    public final long executionTimeNs;
    public final int operationsCount;

    public DijkstraResult(int[] distances, int[] parents, long executionTimeNs, int operationsCount) {
        this.distances = distances;
        this.parents = parents;
        this.executionTimeNs = executionTimeNs;
        this.operationsCount = operationsCount;
    }


    public List<Integer> getPathTo(int target) {
        List<Integer> path = new ArrayList<>();
        if (distances[target] == Integer.MAX_VALUE) {
            return path;
        }
        for (int at = target; at != -1; at = parents[at]) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }
}
