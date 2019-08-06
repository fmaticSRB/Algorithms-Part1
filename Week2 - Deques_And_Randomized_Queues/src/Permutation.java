 /***********************************************************
 * AUTHOR: Filip Matic
 * Title: Permutation.java
 * Description: Takes an integer, k, as a command line
 * argument, reads a sequence of strings from standard input
 * and prints exactly k of them, uniformly at random.
 ************************************************************/
import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;

public class Permutation 
{
    public static void main(String[] args) 
    {
        RandomizedQueue<String> rque = new RandomizedQueue<>();
        int num = Integer.parseInt(args[0]);

        String str;
        while (!StdIn.isEmpty()) 
        {
            str = StdIn.readString();
            rque.enqueue(str);
        }
        Iterator<String> iterator = rque.iterator();
        for (int i = 0; i < num; i++)
            System.out.println(iterator.next());
    }
}