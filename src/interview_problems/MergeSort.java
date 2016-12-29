package interview_problems;

import java.util.Comparator;

/**
 * Created by amazaspshaumyan on 11/30/16.
 */
public class MergeSort {


    private static<T> boolean less(T a, T b, Comparator<T> comp){
        if(comp.compare(a,b)<0) return true;
        return false;
    }

    private static<T> void mergeFirst(T[] a, T[] aux, int lo, int mid, int hi, Comparator<T> comp){
        int i = lo, j = mid+1;

        // copy to auxillary array
        for(int k=lo; k<=hi; k++ ) aux[k]= a[k];

        // move back, so that a[lo:hi] is in sorted order
        for(int k=lo; k<=hi; k++){
            if(i > mid)                       a[k] = aux[j++];
            else if(j > hi)                   a[k] = aux[i++];
            else if(less(aux[i],aux[j],comp)) a[k] = aux[i++];
            else                              a[k] = aux[j++];
        }
    }

    private static<T> void mergeSecond(T[] a, T[] aux, int lo, int mid, int hi, Comparator<T> comp){
        int i = lo, j = mid+1;

        // copy to auxillary array
        for(int k=lo; k<=hi; k++ ) aux[k]= a[k];

        // move back
        int k = lo;
        while( i <= mid && j <= hi){
            if(less(aux[i], aux[j], comp)) a[k++] = aux[i++];
            else                           a[k++] = aux[j++];
        }
        while( i <= mid) a[k++] = aux[i++];
        while( j <= hi)  a[k++] = aux[j++];
    }

    /*
    Top Down recursive sort
     */
    private static<T> void recursiveSortTD(T[] a, T[] aux, int lo, int hi, Comparator<T> comp){
        if(lo >= hi) return;
        int mid = lo + (hi-lo)/2; // find middle point in the way, that avoids overflow
        recursiveSortTD(a,aux,lo,mid,comp);   // sort left sub array
        recursiveSortTD(a,aux,mid+1,hi,comp); // sort right sub array
        mergeSecond(a,aux,lo,mid,hi,comp);
        System.out.println("lo = "+((Integer)lo).toString() + " hi = " + ((Integer)hi).toString() );
    }

    /*
    Bottom Up recusrsive sort
     */
    public static<T> void mergeSortBottomUp(T[] a, Comparator<T> comp){
        int N   = a.length;
        T[] aux = (T[]) new Object[N];
        for(int sz=1; sz<=N; sz*=2){
            for(int lo=0; lo < N-sz; lo+=2*sz){
                mergeFirst(a,aux,lo,lo+sz-1,Math.min(N-1,lo+2*sz - 1),comp);
            }
        }
    }

    public static<T> void mergeSortBottomUp(T[] a){
        mergeSortBottomUp(a,new DefaultComparator<>());
    }

    public static<T> void mergeSortTopDown(T[] a, Comparator<T> comp){
        T[] aux = (T[]) new Object[a.length];
        recursiveSortTD(a,aux,0,a.length-1,comp);
    }


    public static<T> void mergeSortTopDown(T[] a){
        mergeSortTopDown(a,new DefaultComparator<T>());
    }


    // ================================================= TESTS =========================================================


    public static void main(String[] args){
        Integer[] x = {4,2,3,1,6,5,7,10,9,8,11};
        Integer[] y = {2,1};
        //mergeSortTopDown(x);
        mergeSortBottomUp(x);
        for(int k=0; k< x.length; k++){
            System.out.println(x[k]);
        }

        System.out.println();

        mergeSortBottomUp(y);
        for(int k=0; k< y.length; k++){
            System.out.println(y[k]);
        }
    }



}
