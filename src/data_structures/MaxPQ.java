package data_structures;

import interview_problems.*;
import interview_problems.DefaultComparator;

import java.util.Iterator;
import java.util.Comparator;


public class MaxPQ<T> implements Iterable<T>{

    // use adapter pattern
    private MinPQ<T> mpq;

    public MaxPQ(int cap, Comparator<T> comp){
        Comparator<T> compar = new InverseComparator(comp);
        mpq = new MinPQ<>(cap, compar);
    }

    public MaxPQ(int cap){
        this(cap, new DefaultComparator<>());
    }

    public MaxPQ(T[] arr, Comparator<T> comp){
        Comparator<T> compar = new InverseComparator(comp);
        mpq = new MinPQ<>(arr,compar);
    }

    public MaxPQ(T[] arr){
        this(arr, new DefaultComparator<>());
    }


    public void insert(T a){
        mpq.insert(a);
    }

    public T deleteMax(){
        return mpq.deleteMin();
    }

    public T max(){
        return mpq.min();
    }

    public T min(){
        return mpq.max();
    }

    public int size(){ return mpq.size();}

    public boolean isEmpty(){ return mpq.isEmpty();}

    public String toString(){ return mpq.toString();}


    //====================================== Iterator =====================================

    public Iterator<T> iterator(){ return mpq.iterator();}


    private class InverseComparator implements Comparator<T>{

        Comparator<T> comp;

        public InverseComparator( Comparator<T> comp){
            this.comp = comp;
        }

        public int compare(T a, T b){
            return -1* comp.compare(a,b);
        }
    }


    // =======================================   TESTS =====================================

    public static void main(String[] args){
        Integer[] x = {2,6,1,7,0};
        MaxPQ<Integer> mpq = new MaxPQ<>(x);
        mpq.insert(10);
        mpq.insert(1);
        mpq.insert(-1);

        System.out.println(mpq.toString());

        System.out.println();

        //mpq.heapSort(x);

        //System.out.println(Arrays.toString(x));

        //System.out.println();

        int R = mpq.size();
        for(int k=0; k < R; k++){
            System.out.println("MIN = " + mpq.min().toString());
            Integer el = mpq.deleteMax();
            System.out.println(el);
        }


    }

}
