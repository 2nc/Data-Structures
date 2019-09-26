import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author wnc
 */
public class UnionFindTest {

    @Test
    public void UnionTest() {
        UnionFind U = new UnionFind(5);
        assertEquals(3, U.find(3));
        U.union(4, 3);
        U.union(3, 2);
        U.union(2, 1);
        U.union(1, 0);
        assertEquals(3, U.find(2));
        assertEquals(5, U.sizeOf(3));
        assertEquals(5, U.sizeOf(0));
        assertEquals(3,U.parent(0));
        assertEquals(true, U.connected(2,4));

    }

    @Test
    public void PathCompressionTest(){
        UnionFind U2 = new UnionFind(5);
        U2.union(4, 3);
        U2.union(3, 2);
        U2.union(1, 0);
        U2.union(2, 1);
        assertEquals(3, U2.find(1));
        assertEquals(3, U2.parent(1));
    }



}
