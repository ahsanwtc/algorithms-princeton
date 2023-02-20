import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
  private Node head;
  private class Node {
    Item item;
    Node next;
  }

  private int size;
  // construct an empty randomized queue
  public RandomizedQueue() {
    size = 0;
  }

  // is the randomized queue empty?
  public boolean isEmpty() {
    return size == 0;
  }

  // return the number of items on the randomized queue
  public int size() {
    return size;
  }

  // add the item
  public void enqueue(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("null argument");
    }

    Node node = new Node();
    node.item = item;

    /* base case */
    if (isEmpty()) {
      head = node;
      size++;
      return;
    }

    int index = StdRandom.uniformInt(size + 1);
    Node current = head;
    Node previous = null;

    for (int i = 0; i < index; i++) {
      previous = current;
      current = current.next;
    }

    /* adding at the head */
    if (previous == null) {
      size++;
      node.next = head;
      head = node;
      return;
    }

    /* adding at the end */
    if (current == null) {
      size++;
      previous.next = node;
      return;
    }

    /* adding at the middle */
    size++;
    previous.next = node;
    node.next = current;
  }

  // remove and return a random item
  public Item dequeue() {
    if (isEmpty()) {
      throw new java.util.NoSuchElementException("queue is empty");
    }

    int index = StdRandom.uniformInt(size);

    size--;

    /* remove first */
    if (index == 0) {
      Node item = head;
      head = head.next;
      return item.item;
    }

    /* remove any */
    Node current = head;
    Node previous = null;

    for (int i = 1; i <= index; i++) {
      previous = current;
      current = current.next;
    }

    Node item = current;
    previous.next = current.next;
    return item.item;
  }

  // return a random item (but do not remove it)
  public Item sample() {
    if (isEmpty()) {
      throw new java.util.NoSuchElementException("queue is empty");
    }

    int index = StdRandom.uniformInt(size);

    /* remove first */
    if (index == 0) {
      return head.item;
    }

    /* remove any */
    Node current = head;
    for (int i = 1; i <= index; i++) {
      current = current.next;
    }

    return current.item;
  }

  // return an independent iterator over items in random order
  public Iterator<Item> iterator() {
    return new QueueIterator();
  }

  private class QueueIterator implements Iterator<Item> {
    private Node current = head;
    @Override
    public boolean hasNext() {
      return current != null;
    }

    @Override
    public Item next() {
      if (current == null) {
        throw new java.util.NoSuchElementException("no more items");
      }

      Item item = current.item;
      current = current.next;
      return item;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException("method not supported");
    }
  }

  // unit testing (required)
  public static void main(String[] args) {
    RandomizedQueue<Integer> queue = new RandomizedQueue<>();
    queue.enqueue(1);
    queue.enqueue(2);
    queue.enqueue(3);
    queue.enqueue(4);


    System.out.println("size: " + queue.size());
    for (Integer item: queue) {
      System.out.print(item + " ");
    }
    System.out.println();

    System.out.println("remove: " + queue.dequeue());
    System.out.println("remove: " + queue.dequeue());
    System.out.println("remove: " + queue.dequeue());
    System.out.println("remove: " + queue.dequeue());

    for (Integer item: queue) {
      System.out.print(item + " ");
    }
    System.out.println();

    queue.enqueue(5);
    queue.enqueue(6);

    for (Integer item: queue) {
      System.out.print(item + " ");
    }
    System.out.println();

    System.out.println("peek: " + queue.sample());
    System.out.println("peek: " + queue.sample());

    for (Integer item: queue) {
      System.out.print(item + " ");
    }
    System.out.println();

    System.out.println("-------------------------------");

    int n = 5;
    RandomizedQueue<Integer> rqueue = new RandomizedQueue<Integer>();
    for (int i = 0; i < n; i++)
      rqueue.enqueue(i);
    for (int a : rqueue) {
      for (int b : rqueue)
        System.out.print(a + "-" + b + " ");
      System.out.println();
    }

  }

}
