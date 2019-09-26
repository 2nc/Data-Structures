import java.util.ArrayList;

/**
 * @author wnc
 */

public class UnionFind {


    ArrayList<Integer> disjointSet;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        disjointSet = new ArrayList<>(n);
        for (int i = 0; i < n; i++){
            disjointSet.add(-1);
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex){
        if (vertex >= disjointSet.size() || vertex < -disjointSet.size()){
            throw new IllegalArgumentException();
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        return -disjointSet.get(find(v1));
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);
        return disjointSet.get(v1);
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        int rootV1 = find(v1);
        int rootV2 = find(v2);
        if (-disjointSet.get(rootV1) <= -disjointSet.get(rootV2)) {
            disjointSet.set(rootV2, disjointSet.get(rootV2) + disjointSet.get(rootV1));
            disjointSet.set(rootV1, rootV2);
        } else {
            disjointSet.set(rootV1, disjointSet.get(rootV2) + disjointSet.get(rootV1));
            disjointSet.set(rootV2, rootV1);
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        validate(vertex);
        int[] temp = new int[disjointSet.size()];
        int count = 0;
        int root = vertex;
        while (parent(root) >= 0) {
            temp[count] = root;
            count++;
            root = parent(root);
        }
        while(count > 0) {
            disjointSet.set(temp[count - 1], root);
            count--;
        }
        return root;
    }

}
