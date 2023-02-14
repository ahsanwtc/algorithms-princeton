/*
Successor with delete. Given a set of n integers S={0,1,...,n−1} and a sequence of requests of the following form:
 - Remove x from S
 - Find the successor of x: the smallest y in S such that y >= x.
Design a data type so that all operations (except construction)  take logarithmic time or better in the worst case.
*/
public class SuccessorWithDelete {
  private int[] members;
  private int[] size;

  public SuccessorWithDelete(int n) {
    members = new int[n];
    size = new int[n];
    for (int i = 0; i < n; i++) {
      size[i] = 1;
      members[i] = i;
    }
  }

  private int root(int i) {
    while (i != members[i]) {
      members[i] = members[members[i]];
      i = members[i];
    }
    return i;
  }

  public boolean connected(int p, int q) {
    return root(p) == root(q);
  }

  public void union(int p, int q) {
    int rootP = root(p);
    int rootQ = root(q);

    if (rootP != rootQ) {
      members[rootQ] = rootP;
    }
  }

  public int find(int i) {
    return i;
  }

  public void remove(int x) {

  }
}
