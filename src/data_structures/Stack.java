package data_structures;


public interface Stack<T> {


    int size();
    boolean isEmpty();
    void push(T item);
    T pop();
    T peek();
}
