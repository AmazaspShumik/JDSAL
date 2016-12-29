package interview_problems;

import java.util.Arrays;

/**
 * This solution to Problem N 6.1 in Elements of Programming Interviews
 */
public class DutchNationalFlag {


    public static void dutchFlag(Comparable[] a, int i){
        exch(a,i,0);
        Comparable v = a[0];
        int lt = 0, j = 1, gt = a.length-1;
        while( j<=gt ){
            if     ( less(a[j],v) )    exch(a,lt++,j++);
            else if( less(v,a[j]) )    exch(a,j, gt--);
            else                       j++;
        }
    }


    // ==================  Helper Methods ==========================

    public static void exch(Object[] a, int i, int j){
        Object v     = a[i];
        a[i]         = a[j];
        a[j]         = v;
    }

    public static boolean less(Comparable a, Comparable b){
        return a.compareTo(b) < 0;
    }


    // ===================== Unit Tests ============================

    public static void main(String[] args){
        Integer[] x = {6,2,8,0,1,5,13,4,5,2,3,7,5,9,10};
        dutchFlag(x,5);
        System.out.println(Arrays.toString(x));
    }
}
