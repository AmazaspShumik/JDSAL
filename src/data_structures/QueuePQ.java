package data_structures;

import java.util.Comparator;
import java.util.Iterator;

public class QueuePQ<T> implements Queue<T>,Iterable<T>{

    private int lastID = Integer.MIN_VALUE; // prevent quick overflow
    private MinPQ<NodePQ>  mpq;

    public QueuePQ(){
        mpq = new MinPQ<NodePQ>(10,new NodeComparator());
    }

    private class NodePQ{
        private int t;
        private T item;

        public NodePQ(int t, T item){
            this.t = t;
            this.item = item;
        }
    }

    private class NodeComparator implements Comparator<NodePQ>{

        public int compare(NodePQ a, NodePQ b){
            return a.t - b.t;
        }
    }

    public int size(){ return mpq.size();}

    public boolean isEmpty(){ return mpq.isEmpty();}

    public void enqueue(T item){
        NodePQ newest = new NodePQ(lastID++,item);
        mpq.insert(newest);
    }

    public T dequeue(){
        T element = mpq.deleteMin().item;
        if(mpq.size()==0) lastID = Integer.MIN_VALUE;
        return element;
    }


    public T top(){
        return mpq.min().item;
    }


    public Iterator<T> iterator(){
        return new QueuePQIterator();
    }


    private class QueuePQIterator implements Iterator<T>{

        Iterator<NodePQ> iter = mpq.iterator();

        public boolean hasNext() { return iter.hasNext();}
        public T next() { return iter.next().item;}
        public void remove() {iter.remove();}
    }


    public static void main(String[] args){
        Integer[] x = {1,2,3,4,5,6,7,8,9,10};
        QueuePQ<Integer> stp = new QueuePQ<>();
        for(int k=0; k< x.length; k++){
            stp.enqueue(x[k]);
        }

        Integer t1 = stp.dequeue();
        stp.enqueue(13);

        for(int k=0; k< x.length; k++){
            System.out.println(stp.dequeue());
        }

    }


}
