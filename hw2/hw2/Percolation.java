/**
 * @author wnc
 */
package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    int[] T;
    int size = 0;
    int openSize = 0;
    int top;
    int bottom;
    WeightedQuickUnionUF U;
    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N){
        size = N;
        U = new WeightedQuickUnionUF(N * N + 2);
        T = new int[N * N];
        for(int i = 0; i < N * N ;i++){
                T[i] = 0;
        }
        top = N * N;
        bottom = N * N + 1;
    }

    public int xyTo1D(int row, int col){
        return row * size + col;
    }
    // open the site (row, col) if it is not open already
    public void open(int row, int col){
        if(!isOpen(row, col)){
            T[xyTo1D(row,col)] = 1;
            openSize = openSize + 1;
            if(row == 0){
                U.union(top, xyTo1D(row,col));
            }
            if(!percolates() && row == size - 1){
                U.union(bottom, xyTo1D(row,col));
            }
            if(row != size - 1 && isOpen(row + 1, col)){
                U.union(xyTo1D(row,col), xyTo1D(row + 1,col));
            }
            if(row != 0 && isOpen(row - 1, col)){
                U.union(xyTo1D(row,col), xyTo1D(row - 1,col));
            }
            if(col != 0 && isOpen(row, col - 1)){
                U.union(xyTo1D(row,col), xyTo1D(row,col - 1));
            }
            if(col != size - 1 && isOpen(row, col + 1)){
                U.union(xyTo1D(row,col), xyTo1D(row ,col + 1));
            }
        }

    }
    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        if(T[xyTo1D(row,col)] == 1){
            return true;
        }
        return false;
    }
    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        return U.connected(top, xyTo1D(row,col));
    }
    // number of open sites
    public int numberOfOpenSites(){
        return openSize;
    }
    // does the system percolate?
    public boolean percolates(){
        return U.connected(top, bottom);
    }



    // use for unit testing (not required, but keep this here for the autograder)
    public static void main(String[] args){

    }

}
