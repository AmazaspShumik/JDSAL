package data_structures;

import interview_problems.*;
import interview_problems.DefaultComparator;

import java.util.Comparator;
import java.util.Iterator;

/**
 * Client code , that uses two Priority Queues to find median
 *
 * insert - O(log(N))   ( inserts new element )
 * median - O(1)        ( returns median value )
 * delete - O(log(N))   ( deletes median )
 */
public class DynamicMedian<T> {

    private MinPQ<T> right;
    private MaxPQ<T> left;
    private Comparator<T> comp;
    private T median;

    public DynamicMedian(Comparator<T> comp){
        this.comp = comp;
        right  = new MinPQ<>(10,comp);
        left   = new MaxPQ<>(10,comp);
    }

    public DynamicMedian(){
        this(new DefaultComparator<>());
    }


    public void insert(T item){

        // first element is median
        if(left.isEmpty() && right.isEmpty() && median == null) {
            median = item;
            return;
        }

        // if added element is less or equal to median
        if(comp.compare(item,median) <= 0 ){
            left.insert(item);
        }else{
            right.insert(item);
        }


        if(left.size() - right.size() >= 1){
            right.insert(median);
            median = left.deleteMax();
            return;
        }
        if(right.size() - left.size() > 1){
            left.insert(median);
            median = right.deleteMin();
        }

    }


    public T deleteMedian(){
        T element = median;
        if(!left.isEmpty() || !right.isEmpty()) {
            if (left.size() >= right.size()) median = left.deleteMax();
            else median = right.deleteMin();
        }else{
            median = null;
        }
        return element;
    }

    public T median(){ return median;}


    public static void main(String[] args){
        Integer[] x  = {2,1,0,6,7,8,9,10};
        DynamicMedian<Integer> dm = new DynamicMedian<>();
        for(int j = 0; j<x.length; j++){
            dm.insert(x[j]);
            System.out.println("Median");
            System.out.println(dm.median());
        }

        for(int j = 0; j<x.length; j++){
            System.out.println("Median = "+ dm.median().toString());
            Integer med = dm.deleteMedian();
            System.out.println(med);
        }


        for(int j = 0; j<x.length; j++){
            dm.insert(x[j]);
            System.out.println("Median");
            System.out.println(dm.median());
        }


    }

}
