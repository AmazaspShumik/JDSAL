package data_structures;

/**
 * Created by amazaspshaumyan on 11/17/16.
 */
public interface Queue<T> {

    int size();
    boolean isEmpty();
    void enqueue(T item);
    T dequeue();
}
