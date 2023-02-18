import edu.princeton.cs.algs4.Stack;

public class QueueWithTwoStacks<T> {



  private Stack<T> stackOne;
  private Stack<T> stackTwo;

  public QueueWithTwoStacks() {
    stackOne = new Stack<>();
    stackTwo = new Stack<>();
  }

  public void enqueue(T item) {
    stackOne.push(item);
  }

  public T dequeue() {
    if (stackTwo.isEmpty()) {
      while (!stackOne.isEmpty()) {
        stackTwo.push(stackOne.pop());
      }
    }

    return stackTwo.pop();
  }

  public boolean isEmpty() {
    return stackOne.isEmpty() && stackTwo.isEmpty();
  }

  public static void main(String[] args) {
    QueueWithTwoStacks<Integer> queue = new QueueWithTwoStacks<>();
    queue.enqueue(1);
    queue.enqueue(2);
    queue.enqueue(3);
    System.out.println(queue.dequeue());
    queue.enqueue(4);
    System.out.println(queue.dequeue());
    System.out.println(queue.dequeue());
    System.out.println(queue.dequeue());
    
  }

}
