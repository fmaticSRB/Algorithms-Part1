import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private class Node {
        private Item item;
        private Node next;
        private Node pre;

        public Node(Item item, Node next, Node pre) {
            this.item = item;
            this.next = next;
            this.pre = pre;
        }
    }


    private class DIterator implements Iterator<Item> {
        private Node current;

        public DIterator() {
            current = first;
        }

        @Override
        public boolean hasNext() {
            if (current == null) return false;
            return true;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }

    private DIterator iterator;
    private Node first, last;
    private int size;

    public Deque() {// construct an empty deque
        first = last = null;
        size = 0;
    }

    public boolean isEmpty() {// is the deque empty?注意区判空条件和判满条件
        return first == null;
    }

    public int size() {// return the number of items on the deque。
        return size;
    }

    public void addFirst(Item item) {// add the item to the front
        if (item == null) throw new java.lang.IllegalArgumentException();
        Node work = new Node(item, first, null);
        if (isEmpty()) {
            first = last = work;
        } else {
            first.pre = work;
            first = first.pre;
        }
        size++;
    }

    public void addLast(Item item) {// add the item to the end
        if (item == null) throw new java.lang.IllegalArgumentException();
        Node work = new Node(item, null, last);
        if (isEmpty()) {
            first = last = work;
        } else {
            last.next = work;
            last = last.next;
        }
        size++;
    }

    public Item removeFirst() {// remove and return the item from the front
        if (isEmpty()) throw new java.util.NoSuchElementException();
        size--;
        Node work = first;
        if (first == last && first != null) {
            first = last = null;
            return work.item;
        }
        first = first.next;
        work.next = null;
        first.pre = null;
        return work.item;
    }

    public Item removeLast() {// remove and return the item from the end
        if (isEmpty()) throw new java.util.NoSuchElementException();
        size--;
        Node work = last;
        if (first == last && first != null) {
            first = last = null;
            return work.item;

        }
        last = last.pre;
        last.next = null;
        work.pre = null;
        return work.item;
    }


    public Iterator<Item> iterator() {// return an iterator over items in order from front to end
        iterator = new DIterator();
        return iterator;
    }

    public static void main(String[] args) {// unit testing (optional)
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