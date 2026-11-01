package mst;

import org.junit.jupiter.api.Test;
import graph.Graph;
import static org.junit.jupiter.api.Assertions.*;

public class MSTTest {
    @Test
    void testSmallConnectedGraph() {
        Graph g = new Graph(4, false);
        g.addEdge(0, 1, 10);
        g.addEdge(0, 2, 6);
        g.addEdge(0, 3, 5);
        g.addEdge(1, 3, 15);
        g.addEdge(2, 3, 4);

        MSTResult kruskal = KruskalMST.findMST(g);
        MSTResult prim = PrimMST.findMST(g);

        assertTrue(kruskal.isConnected, "Graph should be connected");
        assertTrue(prim.isConnected, "Graph should be connected");


        assertEquals(19, kruskal.totalWeight);
        assertEquals(19, prim.totalWeight);
    }

    @Test
    void testDisconnectedGraph() {
        Graph g = new Graph(4, false);
        g.addEdge(0, 1, 10);
        g.addEdge(2, 3, 5);

        MSTResult kruskal = KruskalMST.findMST(g);
        MSTResult prim = PrimMST.findMST(g);

        assertFalse(kruskal.isConnected, "Graph should be disconnected");
        assertFalse(prim.isConnected, "Graph should be disconnected");
    }

    @Test
    void testMultipleValidMSTs() {
        Graph g = new Graph(4, false);
        g.addEdge(0, 1, 1);
        g.addEdge(1, 2, 1);
        g.addEdge(2, 3, 1);
        g.addEdge(3, 0, 1);

        MSTResult kruskal = KruskalMST.findMST(g);
        MSTResult prim = PrimMST.findMST(g);

        assertEquals(3, kruskal.totalWeight);
        assertEquals(3, prim.totalWeight);
    }

    @Test
    void testSingleVertex() {
        Graph g = new Graph(1, false);
        assertEquals(0, KruskalMST.findMST(g).totalWeight);
        assertEquals(0, PrimMST.findMST(g).totalWeight);
    }

}
