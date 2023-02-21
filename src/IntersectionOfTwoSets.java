
class Point implements Comparable<Point> {
  private int x;
  private int y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  @Override
  public int compareTo(Point p) {
    /* (x1, y1) (x2, y2)*/
    if (p != null) {
      if (x == p.x) {
        return Integer.compare(y, p.y);
      } else {
        return Integer.compare(x, p.x);
      }
    }

    return -1;
  }
}

class Insertion {
  public static void sort(Point[] comparables) {
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
public class IntersectionOfTwoSets {
  public static void main(String[] args) {
    Point[] a = new Point[] { new Point(1, 3), new Point(2, 4), new Point(-1, 2), new Point(3, 5), new Point(6, 0)};
    Point[] b = new Point[] {new Point(2, 4), new Point(3, 5), new Point(6, 0), new Point(1, 3), new Point(3, 3), new Point(4, 4), new Point(8, 3) };

    Insertion.sort(a);
    Insertion.sort(b);

    System.out.println("A:");
    for (Point p: a) {
        System.out.println("(" + p.getX() + ", " + p.getY() + ")");
    }

    System.out.println("B:");
    for (Point p: b) {
      System.out.println("(" + p.getX() + ", " + p.getY() + ")");
    }

    int n = a.length;
    if (n > b.length) {
      n = b.length;
    }

    Point[] common = new Point[n];

    int i = 0, j = 0, counter = 0;
    while (i < a.length && j < b.length) {
      switch (a[i].compareTo(b[j])) {
        case 1:
          j++;
          break;
        case 0:
          common[counter] = new Point(a[i].getX(), a[i].getY());
          counter++;
          i++;
          j++;
          break;
        case -1:
          i++;
          break;
      }
    }

    System.out.println("Count: " + counter);
    for (Point p: common) {
      if (p != null)
        System.out.println("(" + p.getX() + ", " + p.getY() + ")");
    }

  }
}
