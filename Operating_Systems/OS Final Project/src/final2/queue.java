
package final2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
Coded By: M. Fawad Jawaid Malik (11k-2116) 
             Ali Manzar Jaffery (11k-2202)
*/


public class queue<Item> implements Iterable<Item> {
    private int N;         // number of elements on queue
    private Node first;    // beginning of queue
    private Node last;     // end of queue

    // helper linked list class
    private class Node {
        private Item item;
        private Node next;
    }

   /**
     * Create an empty queue.
     */
    public queue() {
        first = null;
        last  = null;
    }

   /**
     * Is the queue empty?
     */
    public boolean isEmpty() {
        return first == null;
    }

   /**
     * Return the number of items in the queue.
     */
    public int size() {
        return N;
    }

   /**
     * Return the item least recently added to the queue.
     * Throw an exception if the queue is empty.
     */
    public Item peek() {
        if (isEmpty()) throw new RuntimeException("Queue underflow");
        return first.item;
    }

   /**
     * Add the item to the queue.
     */
    public void enqueue(Item item) {
        Node x = new Node();
        x.item = item;
        if (isEmpty()) { first = x;     last = x; }
        else           { last.next = x; last = x; }
        N++;
    }

   /**
     * Remove and return the item on the queue least recently added.
     * Throw an exception if the queue is empty.
     */
    public Item dequeue() {
        if (isEmpty()) throw new RuntimeException("Queue underflow");
        Item item = first.item;
        first = first.next;
        N--;
        if (isEmpty()) last = null;   // to avoid loitering
        return item;
    }

   /**
     * Return string representation.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this)
            s.append(item + " ");
        return s.toString();
    }


   /**
     * Return an iterator that iterates over the items on the queue in FIFO order.
     */
    public Iterator<Item> iterator()  {
        return new FIFOIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class FIFOIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
