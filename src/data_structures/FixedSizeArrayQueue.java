package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class FixedSizeArrayQueue<T> implements Iterable<T>,Queue<T>{

    private T[] queue;
    private int N;
    private int head;
    private int tail;

    public FixedSizeArrayQueue(int cap){
        queue = (T[]) new Object[cap];
    }


    public int size(){
        return N;
    }

    public boolean isEmpty(){
        return N==0;
    }

    public void enqueue(T item) throws IndexOutOfBoundsException{
        // if tail==head array is either full or empty
        if (tail==head)
            if(N==queue.length) throw new IndexOutOfBoundsException(); // check that array is not full
        queue[tail++] = item;
        if (tail == queue.length) tail = 0;
        N++;
    }

    public T dequeue(){
        if(isEmpty()) throw new NoSuchElementException();
        T item = queue[head];
        queue[head++] = null;  // avoid loitering
        if (head==queue.length) head = 0;
        N--;
        return item;
        }


    public Iterator<T> iterator(){
        return new ArrayQueueIterator();
    }

    private class ArrayQueueIterator implements Iterator<T>{

        private int i;

        public boolean hasNext(){
            return i < N;
        }

        public T next(){
            T item = queue[(head+i)%queue.length];
            i++;
            return item;
        }
        public void remove(){}
    }


    public static void main(String[] args){
        int [] x = new int[4];
        x[1] = 1;
        x[2] = 2;
        x[3] = 3;
        FixedSizeArrayQueue<Integer> q = new FixedSizeArrayQueue<>(4);
        for(int j=0; j < 4; j++){
            q.enqueue(x[j]);
        }

        for(int j=0; j < 2; j++){
            int current = q.dequeue();
            System.out.println(current);
        }

        for(int j=0; j < 2; j++){
            q.enqueue(x[j]);
        }

        // check with for each loop
        for(Integer itg: q){
            System.out.println(itg);
        }

    }

}
