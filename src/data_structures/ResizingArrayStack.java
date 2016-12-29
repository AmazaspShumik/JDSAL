package data_structures;

/**
 * Created by amazaspshaumyan on 11/21/16.
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayStack<T> implements Iterable<T>,Stack<T> {


    private T[] stack = (T[]) new Object[1];
    private int N     = 0;


    private void resize(int cap){
        T[] temp = (T[]) new Object[cap];
        for(int j=0; j < N; j++)
            temp[j] = stack[j];
        stack = temp;
    }

    public int size(){
        return N;
    }

    public boolean isEmpty(){
        return N==0;
    }

    public void push(T item){
        if(N==stack.length)
            resize(2 * N);
        stack[N++] = item;
    }

    public T pop(){
        T item   = stack[--N];
        stack[N] = null;    // avoid loitering
        if (N > 0 && N < stack.length/4)
            resize(stack.length/2);
        return item;
    }

    public T peek(){
        if(isEmpty()) throw new NoSuchElementException();
        return stack[N-1];
    }

    /*
    Make sure that Iterable interface is satisfied (i.e. )
     */

    public Iterator<T> iterator() {
        return new ReverseResizingArrayStackIterator();
    }

    private class ReverseResizingArrayStackIterator implements Iterator<T>{

        private int i = N;

        public boolean hasNext() { return i > 0; }
        public T next(){return stack[--i];}
        public void remove(){};
    }
}
