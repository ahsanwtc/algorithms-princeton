import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
  private double[] thresholds;

  // perform independent trials on an n-by-n grid
  public PercolationStats(int n, int trials) {
    if (n <= 0 || trials <= 0) {
      throw new IllegalArgumentException("illegal arguments");
    }

    thresholds = new double[trials];

    for (int t = 0; t < trials; t++) {
      Percolation percolation = new Percolation(n);
      while (!percolation.percolates()) {
        int row = StdRandom.uniformInt(1, n + 1);
        int col = StdRandom.uniformInt(1, n + 1);
        if (!percolation.isOpen(row, col)) {
          percolation.open(row, col);
        }
      }
      thresholds[t] = ((double) percolation.numberOfOpenSites()) / (n * n);
    }

  }

  // sample mean of percolation threshold
  public double mean() {
    return StdStats.mean(thresholds);
  }

  // sample standard deviation of percolation threshold
  public double stddev() {

    return StdStats.stddev(thresholds);
  }

  // low endpoint of 95% confidence interval
  public double confidenceLo() {
    return mean() - (1.96 * stddev() / Math.sqrt(thresholds.length));
  }

  // high endpoint of 95% confidence interval
  public double confidenceHi() {
    return mean() + (1.96 * stddev() / Math.sqrt(thresholds.length));
  }

  // test client (see below)
  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    int trials = Integer.parseInt(args[1]);
    PercolationStats stats = new PercolationStats(n, trials);

    System.out.printf("mean                    = %f\n", stats.mean());
    System.out.printf("stddev                  = %f\n", stats.stddev());
    System.out.printf("95%% confidence interval = [%f, %f]\n", stats.confidenceLo(), stats.confidenceHi());
  }

}
