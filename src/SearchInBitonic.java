import java.util.ArrayList;
import java.util.Arrays;

public class SearchInBitonic {
  private int[] numbers;

  public SearchInBitonic(int[] numbers) {
    this.numbers = numbers;
  }

  public boolean search(int x) {
    int point = 0;
    for (int i = 0; i < numbers.length - 1; i++) {
      if (numbers[i] > numbers[i + 1]) {
        point = i;
        break;
      }
    }

    if (x == numbers[point]) {
      return true;
    }

    if (binarySearch(x, 0, point) != -1 ||
            binarySearch(x, numbers.length - 1, point + 1) != -1) {
      return true;
    }

    return false;

  }

  private int binarySearch(int key, int lo, int hi) {
    boolean reversed = false;

    if (lo > hi) {
      reversed = true;
      int temp = lo;
      lo = hi;
      hi = temp;
    }

    while (lo <= hi) {
      int mid = (lo + hi) / 2;

      if (numbers[mid] < key) {

        if (reversed) hi = mid - 1; else lo = mid + 1;
      } else if (numbers[mid] > key) {
        if (reversed) lo = mid + 1; else hi = mid - 1;
      } else if (numbers[mid] == key) {
        return mid;
      }
    }

    return -1;
  }

  public static void main(String[] args) {
    int[] numbers = { 1, 3, 5, 6, 7, 12, 9, 4, 2, 0 };
    SearchInBitonic search = new SearchInBitonic(numbers);
    System.out.println(search.search(11));
  }
}
