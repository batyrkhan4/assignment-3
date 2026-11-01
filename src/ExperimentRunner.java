import connectivity.KosarajuSCC;
import connectivity.TopologicalSort;
import graph.Graph;
import graph.GraphGenerator;
import mst.KruskalMST;
import mst.MSTResult;
import mst.PrimMST;
import shortestpath.Dijkstra;
import shortestpath.DijkstraResult;

public class ExperimentRunner {
    private static final int RUNS = 5;
    private static final long SEED = 42L;

    private static final int[][] SIZES = {
            {10, 30},
            {100, 500},
            {1000, 5000}
    };
    private static final String[] SIZE_LABELS = {"Small", "Medium", "Large"};

    public static void main(String[] args) {
        System.out.println("Starting Experimental Analysis...\n");
        runMSTExperiments();
        runDijkstraExperiments();
        runConnectivityExperiments();
    }

    private static void runMSTExperiments() {
        System.out.println("### 1. Minimum Spanning Tree (Kruskal vs Prim)");
        System.out.println("| Size | V | E | Kruskal Time (ns) | Kruskal Ops | Prim Time (ns) | Prim Ops |");
        System.out.println("|---|---|---|---|---|---|---|");

        for (int i = 0; i < SIZES.length; i++) {
            int v = SIZES[i][0];
            int e = SIZES[i][1];

            long totalKruskalTime = 0, totalPrimTime = 0;
            long totalKruskalOps = 0, totalPrimOps = 0;

            for (int run = 0; run < RUNS; run++) {
                Graph g = GraphGenerator.generateConnectedUndirected(v, e, SEED + run);

                MSTResult kruskal = KruskalMST.findMST(g);
                totalKruskalTime += kruskal.executionTime;
                totalKruskalOps += kruskal.operationsCount;

                MSTResult prim = PrimMST.findMST(g);
                totalPrimTime += prim.executionTime;
                totalPrimOps += prim.operationsCount;
            }

            System.out.printf("| %s | %d | %d | %d | %d | %d | %d |\n",
                    SIZE_LABELS[i], v, e,
                    totalKruskalTime / RUNS, totalKruskalOps / RUNS,
                    totalPrimTime / RUNS, totalPrimOps / RUNS);
        }
        System.out.println();
    }

    private static void runDijkstraExperiments() {
        System.out.println("### 2. Shortest Paths (Dijkstra)");
        System.out.println("| Size | V | E | Execution Time (ns) | Operations Count |");
        System.out.println("|---|---|---|---|---|");

        for (int i = 0; i < SIZES.length; i++) {
            int v = SIZES[i][0];
            int e = SIZES[i][1];

            long totalTime = 0;
            long totalOps = 0;

            for (int run = 0; run < RUNS; run++) {
                Graph g = GraphGenerator.generateDirected(v, e, SEED + run);
                DijkstraResult result = Dijkstra.findShortestPaths(g, 0);
                totalTime += result.executionTimeNs;
                totalOps += result.operationsCount;
            }

            System.out.printf("| %s | %d | %d | %d | %d |\n",
                    SIZE_LABELS[i], v, e, totalTime / RUNS, totalOps / RUNS);
        }
        System.out.println();
    }

    private static void runConnectivityExperiments() {
        System.out.println("### 3. Connectivity (Kosaraju SCC & Topological Sort)");
        System.out.println("| Size | V | E | SCC Time (ns) | TopoSort (DAG) Time (ns) |");
        System.out.println("|---|---|---|---|---|");

        for (int i = 0; i < SIZES.length; i++) {
            int v = SIZES[i][0];
            int e = SIZES[i][1];

            long totalSCCTime = 0;
            long totalTopoTime = 0;

            for (int run = 0; run < RUNS; run++) {
                Graph gDirected = GraphGenerator.generateDirected(v, e, SEED + run);
                long startSCC = System.nanoTime();
                KosarajuSCC.findSCCs(gDirected);
                totalSCCTime += (System.nanoTime() - startSCC);

                Graph gDAG = GraphGenerator.generateDAG(v, e, SEED + run);
                long startTopo = System.nanoTime();
                TopologicalSort.sort(gDAG);
                totalTopoTime += (System.nanoTime() - startTopo);
            }

            System.out.printf("| %s | %d | %d | %d | %d |\n",
                    SIZE_LABELS[i], v, e, totalSCCTime / RUNS, totalTopoTime / RUNS);
        }
        System.out.println();
    }
}
