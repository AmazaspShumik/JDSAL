package interview_problems;

import java.util.Arrays;

/**
 * Created by amazaspshaumyan on 12/9/16.
 */
public class QuickSort3Way {


    public static void sort(Comparable[] a){
        sort3Way(a,0, a.length-1);
    }


    private static void sort3Way(Comparable[] a, int lo, int hi){

        if( lo>=hi ) return;

        int lt = lo, i = lt + 1, gt = hi;
        Comparable v = a[lo];
        while(i <= gt){
            if        (less(a[i],v)) exch(a,i++,lt++);
            else if   (less(v,a[i])) exch(a,gt--,i);
            else       i++;
        }
        sort3Way(a,lo,lt-1);
        sort3Way(a,gt+1,hi);
    }


    // ====================== Helper Methods ===========================


    public static boolean less(Comparable a, Comparable b){
        return a.compareTo(b) < 0;
    }

    public static void exch(Comparable[] a, int i, int j){
        Comparable v = a[i];
        a[i]         = a[j];
        a[j]         = v;
    }


    // ======================= Unit Tests ===============================

    public static void main(String[] args){

        Integer[] x = {6,2,8,0,1,1,13,4,5,2,3,7,11,9,10,7,7};
        sort(x);
        System.out.println(Arrays.toString(x));

    }
}



