package interview_problems;

import java.util.Arrays;
import data_structures.Stack;
import data_structures.LinkedListStack;

/**
 * Created by amazaspshaumyan on 12/7/16.
 */
public class QuickSortPrimitive {

    // ====================  Standard Two Way Quick Sort ===================================

    public static void quickSortTwoWay(Comparable[] a){
        // TODO: we omit shuffling here, should be included later
        quickSort(a,0,a.length-1);
    }


    private static void quickSort(Comparable[] a, int lo, int hi){
        if(hi<=lo) return;
        int j = partitionQuickSort(a,lo,hi);
        quickSort(a,  lo, j-1);
        quickSort(a, j+1, hi);
    }


    private static int partitionQuickSort(Comparable[] a, int lo, int hi){
        int i = lo, j = hi+1;
        Comparable v = a[lo];
        while(true){
            while(less(a[++i],v)) if(i==hi) break;
            while(less(v,a[--j])) if(j==lo) break;
            if(i >= j) break;
            exch(a,i,j);
        }
        exch(a,lo,j);
        return j;
    }


    // ========================= Two Way Quick Sort with Sentinels ==============================

    public static void quickSortTwoWaySentinels(Comparable[] a){
        // TODO: we omit shuffling here, should be included later
        exch(a,argmax(a),a.length-1);
        quickSortSentinels(a,0,a.length-1);
    }

    private static void quickSortSentinels(Comparable[] a, int lo, int hi){
        if(hi-lo <= 1) return;
        int j = partitionSetinels(a,lo,hi);
        quickSortSentinels(a,lo,j);
        quickSortSentinels(a,j+1,hi);
    }

    private static int partitionSetinels(Comparable[] a, int lo, int hi){
        int i = lo + 1, j = hi - 1;
        Comparable v = a[lo];
        while(true){
            while(less(a[i++],v));
            while(less(v,a[j--]));
            if(i >= j) break;
            exch(a,i,j);
        }
        exch(a,lo,j);
        return j;
    }


    // ============================  Nonrecursive quicksort ==============================


    public static void stackQuickSort(Comparable[] a){

        // TODO: randomly shuffle array, to avoid worst case scenario
        Stack<Integer> st = new LinkedListStack<>();
        int lo, hi;

        // Use sentinels to avoid "if" checks in inner loop
        exch(a,argmax(a),a.length-1);

        // initial interval
        st.push(0); st.push(a.length-1);

        while(!st.isEmpty()){
            hi    = st.pop();
            lo    = st.pop();
            if( hi - lo >= 2) {
                int j = stackPartition(a, lo, hi);

                // first push right subarray
                st.push(j + 1);
                st.push(hi);

                // push left subarray
                st.push(lo);
                st.push(j);
            }
        }
    }

    public static int stackPartition(Comparable[] a, int lo, int hi){
        int i = lo, j = hi;
        Comparable v      = a[lo];
        while(true){
            while ( less(a[++i], v) );
            while ( less(v, a[--j]) );
            if( i >= j) break;
            exch(a, i, j);
        }
        exch(a,lo,j);
        return j;
    }


    // =================================  Helper Method ==================================

    public static boolean less(Comparable a, Comparable b){
        return a.compareTo(b) < 0;
    }

    public static void exch(Comparable[] a, int i, int j){
        Comparable v = a[i];
        a[i]         = a[j];
        a[j]         = v;
    }

    public static int argmax(Comparable[] a){
        int argmax = 0;
        for(int j=0; j < a.length; j++){
            if(less(a[argmax],a[j])) argmax = j;
        }
        return argmax;
    }

    // =================================== Unit Tests ====================================

    public static void main(String[] args){

        Integer[] x = {6,2,8,0,1,1,13,4,5,2,3,7,11,9,10};
        // quickSortTwoWaySentinels(x);
        // quickSortTwoWay(x);
        stackQuickSort(x);
        System.out.println(Arrays.toString(x));
    }

}
