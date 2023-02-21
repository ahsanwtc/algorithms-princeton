
class InsertionSort {
  public static void sort(Integer[] comparables) {
    int N = comparables.length;
    for (int i = 0; i < N; i++) {
      for (int j = i; j > 0; j--) {
        if (less(comparables[j], comparables[j - 1])) {
          exchange(comparables, j, j - 1);
        } else {
          break;
        }
      }
    }
  }

  private static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
  }

  private static void exchange(Comparable[] comparables, int i, int j) {
    Comparable swap = comparables[i];
    comparables[i] = comparables[j];
    comparables[j] = swap;
  }
}

public class PermutationSort {

  public static void main(String[] args) {
    Integer[] a = new Integer[] { 1, 2, 3, 4, 5 };
    Integer[] b = new Integer[] { 4, 2, 5, 1, 3 };

    InsertionSort.sort(a);
    InsertionSort.sort(b);

    boolean isPermutated = true;

    for (int i = 0; i < a.length; i++) {
      if (!a[i].equals(b[i])) {
        isPermutated = false;
        break;
      }
    }

    System.out.println("Permutated: " + isPermutated);

  }


}
