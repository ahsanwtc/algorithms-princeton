import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  private WeightedQuickUnionUF percolation;
  private WeightedQuickUnionUF backwash;
  private boolean[][] sites;
  private int openedSites;
  private int topIndex;
  private int bottomIndex;

  // creates n-by-n grid, with all sites initially blocked
  public Percolation(int n) {
    if (n < 1) {
      throw new IllegalArgumentException("n <= 0");
    }

    sites = new boolean[n][n];
    int oneDElements = n * n + 2;
    topIndex = oneDElements - 1;
    bottomIndex = oneDElements - 2;
    percolation = new WeightedQuickUnionUF(oneDElements);
    backwash = new WeightedQuickUnionUF(oneDElements);
  }

  // opens the site (row, col) if it is not open already
  public void open(int row, int col) {
    bounded(row, col);

    int x = row - 1;
    int y = col - 1;

    if (!sites[x][y]) {
      sites[x][y] = true;
      openedSites++;
    }

    /* mapping 2d to 1d */
    int currentCell = x * sites.length + y;

    /* top row can not be lower than 0 */
    if (x - 1 >= 0) {
      /* if it is not the top most row */
      if (sites[x - 1][y]) {
        percolation.union(currentCell, xyTo1D(row - 1, col));
        backwash.union(currentCell, xyTo1D(row - 1, col));
      }
    } else {
      /* top row */
      percolation.union(currentCell, topIndex);
      backwash.union(currentCell, topIndex);
    }

    /* bottom row can not be higher than length */
    if (x + 1 < sites.length) {
      /* if it is not the bottom most row */
      if (sites[x + 1][y]) {
        percolation.union(currentCell, xyTo1D(row + 1, col));
        backwash.union(currentCell, xyTo1D(row + 1, col));
      }
    } else {
      /* bottom row */
      percolation.union(currentCell, bottomIndex);
      if (backwash.find(currentCell) == backwash.find(topIndex)) {
        backwash.union(currentCell, bottomIndex);
      }
    }

    /* check left site if it is open, then add connection */
    /* left most column can not be lower than 0 */
    if (y - 1 >= 0 && sites[x][y - 1]) {
      percolation.union(currentCell, xyTo1D(row, col - 1));
      backwash.union(currentCell, xyTo1D(row, col - 1));
    }

    /* check right site if it is open, then add connection */
    /* right most column can not be higher than length */
    if (y + 1 < sites.length && sites[x][y + 1]) {
      percolation.union(currentCell, xyTo1D(row, col + 1));
      backwash.union(currentCell, xyTo1D(row, col + 1));
    }
  }

  // is the site (row, col) open?
  public boolean isOpen(int row, int col) {
    bounded(row, col);
    return sites[row - 1][col - 1];
  }

  // is the site (row, col) full?
  public boolean isFull(int row, int col) {
    bounded(row, col);
    return backwash.find(xyTo1D(row, col)) == backwash.find(topIndex);
  }

  // returns the number of open sites
  public int numberOfOpenSites() {
    return openedSites;
  }

  // does the system percolate?
  public boolean percolates() {
    return percolation.find(bottomIndex) == percolation.find(topIndex);
  }

  private void bounded(int row, int col) {
    if (row < 1 || row > sites.length) {
      throw new IndexOutOfBoundsException("row index " + row + " is out of bound");
    }

    if (col < 1 || col > sites.length) {
      throw new IndexOutOfBoundsException("col index " + col + " is out of bound");
    }
  }

  private int xyTo1D(int row, int col) {
    return (row - 1) * sites.length + col - 1;
  }

  // test client (optional)
  public static void main(String[] args) {

  }
}
