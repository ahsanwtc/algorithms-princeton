/*
Union-find with specific canonical element. Add a method find() to the union-find data type so that find(i) returns the
largest element in the connected component containing i. The operations, union(), connected(), and find() should all
take logarithmic time or better. For example, if one of the connected components is {1, 2, 6, 9}, then the find()
method should return 9 for each of the four elements in the connected components.
*/
public class SpecificCanonicalElement {
  private int[] members;
  private int[] size;
  private int[] max;

  public SpecificCanonicalElement(int n) {
    members = new int[n];
    size = new int[n];
    max = new int[n];
    for (int i = 0; i < n; i++) {
      size[i] = 1;
      members[i] = i;
      max[i] = i;
    }
  }

  private int root(int i) {
    while (i != members[i]) {
      members[i] = members[members[i]];
      max[i] = max[max[i]];
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

    if (rootP == rootQ) {
      return;
    }

    if (size[rootP] < size[rootQ]) {
      members[rootP] = rootQ;
      members[rootQ] += size[rootP];
      max[rootQ] = Math.max(max[rootP], max[rootQ]);
    } else {
      members[rootQ] = rootP;
      members[rootP] += size[rootQ];
      max[rootP] = Math.max(max[rootP], max[rootQ]);
    }
  }

  public int find(int i) {
    return max[root(i)];
  }
}
