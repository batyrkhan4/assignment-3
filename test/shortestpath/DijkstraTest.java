package shortestpath;

import graph.Graph;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class DijkstraTest {
    @Test
    void testShortestPathNonNegative() {
        Graph g = new Graph(5, true);
        g.addEdge(0, 1, 10);
        g.addEdge(0, 4, 5);
        g.addEdge(1, 2, 1);
        g.addEdge(1, 4, 2);
        g.addEdge(4, 1, 3);
        g.addEdge(4, 2, 9);
        g.addEdge(4, 3, 2);
        g.addEdge(2, 3, 4);
        g.addEdge(3, 2, 6);
        g.addEdge(3, 0, 7);

        DijkstraResult result = Dijkstra.findShortestPaths(g, 0);

        assertEquals(0, result.distances[0]);
        assertEquals(8, result.distances[1]);
        assertEquals(9, result.distances[2]);
        assertEquals(7, result.distances[3]);
        assertEquals(5, result.distances[4]);
    }

    @Test
    void testUnreachableVertex() {
        Graph g = new Graph(4, true);
        g.addEdge(0, 1, 5);
        g.addEdge(2, 3, 5);

        DijkstraResult result = Dijkstra.findShortestPaths(g, 0);
        assertEquals(0, result.distances[0]);
        assertEquals(5, result.distances[1]);
        assertEquals(Integer.MAX_VALUE, result.distances[2]);
        assertEquals(Integer.MAX_VALUE, result.distances[3]);

        List<Integer> pathTo2 = result.getPathTo(2);
        assertTrue(pathTo2.isEmpty(), "Path to unreachable vertex should be empty");
    }

    @Test
    void testNegativeWeight() {
        Graph g = new Graph(3, true);
        g.addEdge(0, 1, 5);
        g.addEdge(1, 2, -2);

        assertThrows(IllegalArgumentException.class, () -> {
            Dijkstra.findShortestPaths(g, 0);
        });
    }
}
