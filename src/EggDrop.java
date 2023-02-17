import java.util.Random;

public class EggDrop {
  private boolean[] floors;
  private int totalFloors;
  private final int startingFloor = 1;
  private int floorT;
  private int numberOfEggs = 0;
  private int numberOfTosses = 0;

  public EggDrop() { }

  private void init(int n) {
    numberOfEggs = 0;
    numberOfTosses = 0;
    totalFloors = n + 1;
    floorT = defineT();
    floors = new boolean[totalFloors];

    /* set all floors starting from T to true, meaning egg drop from any of the above floor */
    /* will result in egg break */
    for (int i = floorT; i < totalFloors; i++) {
      floors[i] = true;
    }
  }

  public int version0(int n) {
    init(n);
    numberOfEggs = 1;
    numberOfTosses = totalFloors;

    int floor = 1;
    while (floor < totalFloors) {
      if (dropEgg(floor)) {
        break;
      }

      floor++;
    }
    return floor;
  }

  /* binary search */
  public int version1(int n) {
    init(n);
    numberOfEggs = (int) Math.ceil(Math.log(totalFloors));
    numberOfTosses = numberOfEggs;

    if (numberOfTosses == 0) {
      return 1;
    }

    int lo = 1, hi = totalFloors - 1;
    while (lo < hi) {
      int mid = (lo + hi) / 2;

      if (!dropEgg(mid)) {
        lo = mid + 1;
      } else {
        hi = mid;
      }
    }

    return lo;
  }

  private boolean dropEgg(int floor) {
    isTossOrEggAvailableForThrow();

    if (floor < 1 || floor >= totalFloors) {
      throw new IllegalArgumentException("Illegal floor number!");
    }

    numberOfTosses--;
    if (floors[floor]) {
      numberOfEggs--;
      return true;
    }

    return false;
  }

  private void isTossOrEggAvailableForThrow() {
    if (numberOfTosses == 0) {
      throw new RuntimeException("Not enough tosses! FloorT: " + floorT);
    } else if (numberOfEggs == 0) {
      throw new RuntimeException("Not enough eggs! FloorT: " + floorT);
    }
  }

  private int defineT() {
    return new Random().nextInt(totalFloors - 1) + 1;
  }

  public int getTosses() {
    return numberOfTosses;
  }

  public int getEggs() {
    return numberOfEggs;
  }

  public static void main(String[] args) {
    EggDrop simulation = new EggDrop();
    int floor = simulation.version0(10);
    int tosses = simulation.getTosses();
    System.out.println("Version 0");
    System.out.println("Egg broke at " + floor + " floor. Tosses: " + tosses);

    System.out.println("-----------------------------------");

    floor = simulation.version1(10);
    tosses = simulation.getTosses();
    System.out.println("Version 1");
    System.out.println("Egg broke at " + floor + " floor. Tosses: " + tosses);
  }
}
