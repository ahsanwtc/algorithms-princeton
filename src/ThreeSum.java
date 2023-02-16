public class ThreeSum {
  private int[] numbers;
  private boolean[] used;

  public ThreeSum(int[] numbers) {
    this.numbers = numbers;
    used = new boolean[numbers.length];
  }

  public int count() {
    int count = 0;

    /* loop through the array till the third last because it is a 3 sum, and we need two more to calculate sum of 3 */
    for (int i = 0; i < numbers.length - 2; i++) {

      /* select the next index to i */
      int j = i + 1;

      /* set the end pointer to the end of array */
      int end = numbers.length - 1;

      /* keep moving through the array while fixing i to find the 2 sum */
      while (j < end) {
        int sum = numbers[i] + numbers[j] + numbers[end];

        /* found the sum */
        if (sum == 0) {
          count++;
          /* move the both pointers so that loop can move towards ending condition */
          j++;
          end--;
        } else if (sum < 0) {
          /* since array is sorted, to increase the sum move the j pointer to the right */
          j++;
        } else {
          /* since array is sorted, to decrease the sum move the end pointer to the left */
          end--;
        }

      }
    }
    return count;
  }

  public int countBrute() {
    int n = numbers.length;
    print();
    int count = 0;
    for (int i = 0; i < n; i++) {
      for (int j = i+1; j < n; j++) {
        for (int k = j+1; k < n; k++) {
          if (numbers[i] + numbers[j] + numbers[k] == 0) {
            count++;
          }
        }
      }
    }
    return count;
  }

  private void print() {
    System.out.print("[");
    for (int i = 0; i < numbers.length; i++) {
      System.out.print(numbers[i] + ", ");
    }
    System.out.print("]\n");
  }

  public int search(int key) {
    int lo = 0, hi = numbers.length - 1;

    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;

      if (key < numbers[mid]) {
        hi = mid - 1;
      } else if (key > numbers[mid]) {
        lo = mid + 1;
      } else {
        return mid;
      }
    }

    return -1;
  }

  public static void main(String[] args) {
    int[] numbers = { -40, -20, -10, 0, 5, 10, 30, 40 };
    ThreeSum sum = new ThreeSum(numbers);
    System.out.println("count is " + sum.count());
  }
}
