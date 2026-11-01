package dsu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DSUTest {
    @Test
    void testUnionAndFind() {
        DSU dsu = new DSU(5);
        assertTrue(dsu.union(0, 1));
        assertTrue(dsu.union(2, 3));
        assertTrue(dsu.union(0, 2));

        assertFalse(dsu.union(1, 3));

        assertEquals(dsu.find(1), dsu.find(3));
        assertNotEquals(dsu.find(0), dsu.find(4));
    }

    @Test
    void testIsConnected() {
        DSU dsu = new DSU(3);
        assertFalse(dsu.isConnected());
        dsu.union(0, 1);
        assertFalse(dsu.isConnected());
        dsu.union(1, 2);
        assertTrue(dsu.isConnected());
    }

    @Test
    void testSingleVertex() {
        DSU dsu = new DSU(1);
        assertTrue(dsu.isConnected());
        assertEquals(0, dsu.find(0));
    }

}
