/*
Social network connectivity. Given a social network containing n members and a log file containing m timestamps at
which times pairs of members formed friendships, design an algorithm to determine the earliest time at which all
members are connected (i.e., every member is a friend of a friend of a friend ... of a friend). Assume that the log
file is sorted by timestamp and that friendship is an equivalence relation. The running time of your algorithm should
be mlogn or better and use extra space proportional to n.
*/
public class SocialNetworkConnectivity {
  private int[] members;
  private int[] size;
  private int counter;

  public SocialNetworkConnectivity(int n) {
    members = new int[n];
    size = new int[n];
    counter = n;
    for (int i = 0; i < n; i++) {
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

    if (rootP == rootQ) {
      return;
    }

    if (size[rootP] < size[rootQ]) {
      members[rootP] = rootQ;
      members[rootQ] += size[rootP];
    } else {
      members[rootQ] = rootP;
      members[rootP] += size[rootQ];
    }
    counter--;
  }

  public boolean isAllConnected() {
    return counter == 1;
  }
}
