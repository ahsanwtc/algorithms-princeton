import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
  public static void main(String[] args) {
   String champ = "";
    int count = 1;
    while (!StdIn.isEmpty()) {
      String challenger = StdIn.readString();

      if (StdRandom.bernoulli(1.0 / count)) {
        champ = challenger;
      }
      count++;
    }
    System.out.println(champ);
  }
}
