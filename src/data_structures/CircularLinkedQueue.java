package data_structures;

import java.util.Iterator;

/**
 * Created by amazaspshaumyan on 11/25/16.
 */

public class CircularLinkedQueue<T> implements Iterable<T>,Queue<T>{

    CircularlyLinkedList<T> queue = new CircularlyLinkedList<>();

    public int size(){ return queue.size(); }
    public boolean isEmpty(){ return queue.isEmpty(); }
    public void enqueue(T item){ queue.addLast(item); }
    public T dequeue(){ return queue.removeFirst(); }
    public void rotate(){ queue.rotate(); }
    public Iterator<T> iterator(){ return queue.iterator();}

}
