import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
  private Node first;
  private Node last;

  private int size;

  private class Node {
    Item item;
    Node next;
  }

  // construct an empty deque
  public Deque() {}

  // is the deque empty?
  public boolean isEmpty() {
    return size == 0;
  }

  // return the number of items on the deque
  public int size() {
    return size;
  }

  // add the item to the front
  public void addFirst(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("null passed");
    }

    size++;
    Node node = new Node();
    node.item = item;
    node.next = first;
    first = node;

    if (last == null) {
      last = first;
    }
  }

  // add the item to the back
  public void addLast(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("null passed");
    }
    size++;
    Node node = new Node();
    node.item = item;
    last.next = node;
    last = node;

    if (first == null) {
      first = last;
    }
  }

  // remove and return the item from the front
  public Item removeFirst() {
    if (first == null) {
      throw new java.util.NoSuchElementException("Dequeue is empty");
    }

    if (size == 1) {
      Item item = first.item;
      first = null;
      last = null;
      size--;
      return item;
    }

    size--;
    Node item = first;
    first = first.next;

    return item.item;
  }

  // remove and return the item from the back
  public Item removeLast() {
    if (last == null) {
      throw new java.util.NoSuchElementException("Dequeue is empty");
    }

    if (size == 1) {
      Item item = first.item;
      first = null;
      last = null;
      size--;
      return item;
    }

    Node current = first;
    int i = 0;
    while (i < size - 2) {
      current = current.next;
      i++;
    }

    size--;
    Node node = last;
    last = current;
    last.next = null;
    return node.item;
  }

  // return an iterator over items in order from front to back
  public Iterator<Item> iterator() {
    return new ListIterator();
  }

  private class ListIterator implements Iterator<Item> {
    private Node current = first;
    @Override
    public boolean hasNext() {
      return current != null;
    }

    @Override
    public Item next() {
      if (current == null) {
        throw new java.util.NoSuchElementException("Dequeue is empty");
      }

      Item item = current.item;
      current = current.next;
      return item;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException("remove() is not supported");
    }
  }

  // unit testing (required)
  public static void main(String[] args) {
    Deque<Integer> deque = new Deque<>();
    System.out.println(deque.isEmpty());
    deque.addFirst(1);
    deque.addFirst(2);
    deque.addLast(3);
    deque.addLast(4);

    for (Integer item: deque) {
      System.out.print(item + " ");
    }
    System.out.println();

    System.out.println(deque.removeFirst());
    for (Integer item: deque) {
      System.out.print(item + " ");
    }
    System.out.println();

    System.out.println(deque.removeLast());
    for (Integer item: deque) {
      System.out.print(item + " ");
    }
    System.out.println();

    System.out.println(deque.removeLast());
    for (Integer item: deque) {
      System.out.print(item + " ");
    }
    System.out.println();
  }
}