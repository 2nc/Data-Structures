package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    double[] possiblilty;
    int times = 0;
    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf){
        possiblilty = new double[T];
        times = T;
        for(int i = 0; i < T; i++){
            Percolation p = pf.make(N);
            while(!p.percolates()){
                p.open(StdRandom.uniform(N), StdRandom.uniform(N));
            }
            possiblilty[i] = (p.openSize + 0.0)/ (N * N);

        }
    }
    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(possiblilty);
    }
    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(possiblilty);
    }
    // low endpoint of 95% confidence interval
    public double confidenceLow(){
        double returnD = mean() - (1.96 * stddev() /Math.sqrt(times));
        return returnD;
    }
    // high endpoint of 95% confidence interval
    public double confidenceHigh(){
        double returnD = mean() + (1.96 * stddev() /Math.sqrt(times));
        return returnD;
    }

    public static void main(String args[]){
        PercolationFactory pf = new PercolationFactory();
        PercolationStats mm = new PercolationStats(10, 200, pf);
        System.out.println(mm.mean());
    }
}
