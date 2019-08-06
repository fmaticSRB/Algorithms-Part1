 /***********************************************************
 * AUTHOR: Filip Matic
 * Title: Deque.java
 * Description: Implements a deque
 ************************************************************/

/* 
* A double-ended queue or deque (pronounced “deck”) is a 
* generalization of a stack and a queue that supports adding 
* and removing items from either the front or the back 
* of the data structure
*/

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> 
{
    private class Node 
    {
        private Item item;
        private Node next;
        private Node pre;

        public Node(Item item, Node next, Node pre) 
        {
            this.item = item;
            this.next = next;
            this.pre = pre;
        }
    }


    private class DIterator implements Iterator<Item> 
    {
        private Node current;

        public DIterator() 
        {
            current = first;
        }

        @Override
        public boolean hasNext() 
        {
            if (current == null) return false;
            return true;
        }

        @Override
        public Item next() 
        {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() 
        {
            throw new java.lang.UnsupportedOperationException();
        }
    }

    private DIterator iterator;
    private Node first, last;
    private int size;

    // construct an empty deque
    public Deque() 
    {
        first = last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() 
    {
        return first == null;
    }

    // return the number of items on the deque
    public int size() 
    {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) 
    {
        if (item == null) throw new java.lang.IllegalArgumentException();
        Node work = new Node(item, first, null);
        if (isEmpty()) 
        {
            first = last = work;
        } else 
        {
            first.pre = work;
            first = first.pre;
        }
        size++;
    }

    // add the item to the end
    public void addLast(Item item) 
    {
        if (item == null) throw new java.lang.IllegalArgumentException();
        Node work = new Node(item, null, last);
        if (isEmpty()) 
        {
            first = last = work;
        } else 
        {
            last.next = work;
            last = last.next;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() 
    {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        size--;
        Node work = first;
        if (first == last && first != null) 
        {
            first = last = null;
            return work.item;
        }
        first = first.next;
        work.next = null;
        first.pre = null;
        return work.item;
    }

    // remove and return the item from the end
    public Item removeLast() 
    {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        size--;
        Node work = last;
        if (first == last && first != null) 
        {
            first = last = null;
            return work.item;

        }
        last = last.pre;
        last.next = null;
        work.pre = null;
        return work.item;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() 
    {
        iterator = new DIterator();
        return iterator;
    }

    // unit testing
    public static void main(String[] args) 
    {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(1);
        deque.addFirst(3);
        deque.addLast(1);
        deque.addFirst(3);
        deque.addLast(1);
        deque.addFirst(3);
        deque.addLast(1);
        deque.addFirst(3);
        deque.addLast(1);
        deque.addFirst(3);
        Iterator<Integer> iterator = deque.iterator();
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());

    }
}