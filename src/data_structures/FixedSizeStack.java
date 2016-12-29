package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class FixedSizeStack<T> implements Iterable<T>,Stack<T>{

    private T[] stack;        // generic array holding elements of stack
    private int N;            // size of the stack

    public FixedSizeStack(int cap){
        this.stack = (T[]) new Object[cap];
    }

    public boolean isEmpty(){
        return N==0;
    }

    public int size(){
        return N;
    }

    public void push(T a){
        stack[N++] = a;
    }

    public T pop(){
        T item = stack[--N];
        stack[N] = null;
        return item;
    }

    public T peek(){
        if(isEmpty()) throw new NoSuchElementException();
        return stack[N-1];
    }

    /*
    Make sure that FixedSizeStack satisfies Iterable interface
     */
    public Iterator<T> iterator(){
        return new FixedSizeStackReverseIterator();
    }


    private class FixedSizeStackReverseIterator implements Iterator<T>{

        private int i=N;

        public boolean hasNext() { return i > 0; }
        public T next() {return stack[--i];}
        public void remove() {};

    }
}
