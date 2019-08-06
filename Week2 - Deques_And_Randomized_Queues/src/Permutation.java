import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> rque = new RandomizedQueue<>();
        int num = Integer.parseInt(args[0]);
        //In in = new In(args[1]);
        String str;
        while (!StdIn.isEmpty()) {
            str = StdIn.readString();
            rque.enqueue(str);
        }
        Iterator<String> iterator = rque.iterator();
        for (int i = 0; i < num; i++)
            System.out.println(iterator.next());
    }
}