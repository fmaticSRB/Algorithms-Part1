
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private class RIterator<Item> implements Iterator<Item> {
        private Item[] arr;
        private int current;

        public RIterator(Item[] arr) {
            int N = size();
            this.arr = (Item[]) new Object[N];
            for (int i = 0; i < N; i++) {
                this.arr[i] = arr[i];
            }
            StdRandom.shuffle(this.arr);
        }

        @Override
        public boolean hasNext() {
            return current != arr.length;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            return arr[current++];
        }

        @Override
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }

    private Item[] arr;
    private RIterator<Item> iterator;
    private int q;//q记录添加下一个Item的位置

    public RandomizedQueue() {// construct an empty randomized queue
        arr = (Item[]) (new Object[2]);
        q = 0;
    }

    public boolean isEmpty() {// is the randomized queue empty?
        return q == 0;
    }

    public int size() {// return the number of items on the randomized queue
        return q;
    }

    public void enqueue(Item item) {// add the item
        if (item == null) throw new java.lang.IllegalArgumentException();
        if (q == arr.length) resize(arr.length * 2);
        arr[q] = item;
        q = q + 1;
    }

    public Item dequeue() {// remove and return a random item
        if (isEmpty()) throw new java.util.NoSuchElementException();
        int num = StdRandom.uniform(size());
        Item item = arr[num];
        arr[num] = arr[q-1];
        q--;
        arr[q] = null;// avoid loitering
        if (!isEmpty() && size() == arr.length / 4) resize(arr.length / 2);
        return item;
    }

    public Item sample() {// return a random item (but do not remove it)
        if (isEmpty()) throw new java.util.NoSuchElementException();
        int num = StdRandom.uniform(size());
        return arr[num];
    }

    public Iterator<Item> iterator() {// return an independent iterator over items in random order
        iterator = new RIterator<Item>(arr);
        return iterator;
    }

    private void resize(int length) {
        Item[] newArr = (Item[]) (new Object[length]);
        int N = size();
        for (int i = 0; i < N; i++)
            newArr[i] = arr[i];
        arr = newArr;
    }

    public static void main(String[] args) {// unit testing (optional)
        RandomizedQueue<Integer> rque = new RandomizedQueue<>();
        rque.enqueue(1);
        rque.enqueue(2);
        rque.enqueue(3);
        System.out.println(rque.dequeue());
        System.out.println(rque.dequeue());
        System.out.println(rque.dequeue());
    }
}