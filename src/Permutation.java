import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;

public class Permutation {
  public static void main(String[] args) {
    int k = Integer.parseInt(args[0]);
    // In file = new In(args[1]);

    RandomizedQueue<String> queue = new RandomizedQueue<>();

    while (!StdIn.isEmpty()) {
      queue.enqueue(StdIn.readString());
    }

    for (int i = 0; i < k; i++) {
      System.out.println(queue.dequeue());
    }
  }
}