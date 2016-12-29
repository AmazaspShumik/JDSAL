package data_structures;

import java.util.Iterator;
import java.util.Comparator;

public class StackPQ<T> implements Stack<T>,Iterable<T>{

    private MaxPQ<NodePQ> mpq;


    private class NodePQ{
        public Integer t;
        public T item;

        NodePQ(int t, T item){
            this.t    = t;
            this.item = item;
        }
    }


    private class NodeComparator implements Comparator<NodePQ>{

        public int compare(NodePQ a, NodePQ b){
            return a.t - b.t;
        }
    }


    public StackPQ(){
        mpq = new MaxPQ<>(2, new NodeComparator());
    }

    public int size() { return mpq.size();}

    public boolean isEmpty() { return mpq.isEmpty();}

    public void push(T item){
        NodePQ newest = new NodePQ(mpq.size(),item);
        mpq.insert(newest);
    }

    public T pop() { return mpq.deleteMax().item; }

    public T peek() { return mpq.max().item;}

    public Iterator<T> iterator(){
        return new StackPQIterator();
    }

    private class StackPQIterator implements Iterator<T>{

        Iterator<NodePQ> mpqIterator;

        public StackPQIterator(){
            mpqIterator = mpq.iterator();
        }

        public boolean hasNext(){ return mpqIterator.hasNext();}
        public T next() {return mpqIterator.next().item;}
        public void remove() {mpqIterator.remove();}
    }


    public static void main(String[] args){
        Integer[] x = {1,2,3,4,5,6,7,8,9,10};
        StackPQ<Integer> stp = new StackPQ<>();
        for(int k=0; k< x.length; k++){
            stp.push(x[k]);
        }

        Integer t1 = stp.pop();
        stp.push(13);


        for(int k=0; k< x.length; k++){
            System.out.println(stp.pop());
        }

    }



}
