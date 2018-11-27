import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CC = 1.96;
    private final int t;
    private final double meanVal;
    private final double stddevVal;
    private final double confidenceLoVal;
    private final double confidenceHiVal;


    public PercolationStats(int n, int trials) {
        t = trials;
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException();

        double[] thresholds = new double[trials];

        for (int i = 0; i < trials; i++) {
            thresholds[i] = experiment(n);
        }
        meanVal = StdStats.mean(thresholds);
        stddevVal = StdStats.stddev(thresholds);
        confidenceLoVal = mean() - CC * stddev() / Math.sqrt(t);
        confidenceHiVal = mean() + CC * stddev() / Math.sqrt(t);
    }

    private double experiment(int n) {
        Percolation p = new Percolation(n);
        while (!p.percolates()) {
            int row = StdRandom.uniform(1, n + 1);
            int col = StdRandom.uniform(1, n + 1);
            p.open(row, col);
        }
        return ((double) p.numberOfOpenSites()) / (n * n);
    }

    public double mean() {
        return meanVal;
    }

    public double stddev() {
        return stddevVal;
    }

    public double confidenceLo() {
        return confidenceLoVal;
    }

    public double confidenceHi() {
        return confidenceHiVal;
    }

    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(Integer.parseInt(args[0]),
                                                   Integer.parseInt(args[1]));
        System.out.println("mean                    = " + ps.mean());
        System.out.println("stddev                  = " + ps.stddev());
        System.out.println("95% confidence interval = [" + ps.confidenceLo() +
                                   ", " + ps.confidenceHi() + "]");
    }
}
