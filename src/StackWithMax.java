import edu.princeton.cs.algs4.Stack;
public class StackWithMax<T extends Double> {
  private Stack<T> stack;
  private Stack<T> maxStack;

  public StackWithMax() {
    stack = new Stack<>();
    maxStack = new Stack<>();
  }

  public void push(T item) {
    stack.push(item);
    if (maxStack.isEmpty() || Double.compare(maxStack.peek(), item) < 0) {
      maxStack.push(item);
    }
  }

  public T pop() {
    T item = stack.pop();

    if (Double.compare(maxStack.peek(), item) == 0) {
      maxStack.pop();
    }
    return item;
  }

  public T max() {
    return maxStack.peek();
  }

  public static void main(String[] args) {
    StackWithMax<Double> stackWithMax = new StackWithMax<>();
    stackWithMax.push(44d);
    stackWithMax.push(55d);
    stackWithMax.push(33d);
    System.out.println("max is " + stackWithMax.max());
    System.out.println("pop: " + stackWithMax.pop());
    System.out.println("max is " + stackWithMax.max());
    System.out.println("pop: " + stackWithMax.pop());
    System.out.println("max is " + stackWithMax.max());
  }
}
