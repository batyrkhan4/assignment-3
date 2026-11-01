package connectivity;

import graph.Graph;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ConnectivityTest {
    @Test
    void testValidDAGTopologicalSort() {
        Graph g = new Graph(6, true);
        g.addEdge(5, 2, 1);
        g.addEdge(5, 0, 1);
        g.addEdge(4, 0, 1);
        g.addEdge(4, 1, 1);
        g.addEdge(2, 3, 1);
        g.addEdge(3, 1, 1);

        List<Integer> order = TopologicalSort.sort(g);
        assertEquals(6, order.size());


        assertTrue(order.indexOf(5) < order.indexOf(2));
        assertTrue(order.indexOf(5) < order.indexOf(0));
        assertTrue(order.indexOf(4) < order.indexOf(0));
        assertTrue(order.indexOf(4) < order.indexOf(1));
        assertTrue(order.indexOf(2) < order.indexOf(3));
        assertTrue(order.indexOf(3) < order.indexOf(1));
    }

    @Test
    void testCyclicGraphTopologicalSort() {
        Graph g = new Graph(3, true);
        g.addEdge(0, 1, 1);
        g.addEdge(1, 2, 1);
        g.addEdge(2, 0, 1);

        assertThrows(IllegalStateException.class, () -> {
            TopologicalSort.sort(g);
        });
    }

    @Test
    void testKosarajuSCC() {
        Graph g = new Graph(5, true);
        g.addEdge(1, 0, 1);
        g.addEdge(0, 2, 1);
        g.addEdge(2, 1, 1);
        g.addEdge(0, 3, 1);
        g.addEdge(3, 4, 1);

        List<List<Integer>> sccs = KosarajuSCC.findSCCs(g);

        assertEquals(3, sccs.size());

        int sumOfSizes = 0;
        for (List<Integer> component : sccs) {
            sumOfSizes += component.size();
        }
        assertEquals(5, sumOfSizes);
    }

}
