package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class ResizingArrayQueue<T> implements Iterable<T>,Queue<T>{

    private int N;
    private T[] queue = (T[]) new Object[2];
    private int head;
    private int tail;


    private void resize(int cap){
        T[] temp = (T[]) new Object[cap];
        for(int j=0; j<N ; j++)
            temp[j] = queue[(head+j)%queue.length];
        queue = temp;
        head  = 0;
        tail  = N;
    }

    public boolean isEmpty(){
        return N==0;
    }

    public int size(){
        return N;
    }

    public void enqueue(T item){
        if(queue.length==N) resize(2*N);
        queue[tail++] = item;
        N++;
        if(tail==queue.length) tail = 0;
    }

    public T dequeue(){
        if(isEmpty()) throw new NoSuchElementException();
        T item  = queue[head];
        queue[head++] = null;  // avoid loitering
        N--;
        if(head==queue.length) head = 0;
        if(N < queue.length/4) resize(queue.length/2);
        return item;
    }

    public Iterator<T> iterator(){
        return new ResizingArrayQueueIterator();
    }

    private class ResizingArrayQueueIterator implements Iterator<T>{

        private int i;

        public boolean hasNext() { return i < N; }
        public T next() {
            T item = queue[(head + i) % queue.length];
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
        ResizingArrayQueue<Integer> q = new ResizingArrayQueue<>();
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
