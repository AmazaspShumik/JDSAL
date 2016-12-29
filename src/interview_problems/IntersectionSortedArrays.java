package interview_problems;

import java.util.Arrays;

/**
 * Created by amazaspshaumyan on 12/8/16.
 */
public class IntersectionSortedArrays {


    /*
    Finds intersection of two sorted arrays (uses modified part of merge sort)
    Time Complexity ~ O(n) in worst case (where n is number of elements in both arrays)
     */
    public static Comparable[] intersection(Comparable[] a, Comparable[] b){

        int N           = a.length;
        int M           = b.length;
        Comparable[] c  = new Comparable[Math.min(N,M)];

        int i = 0, j = 0, k=0;

        while(i < N && j < M){
            if( less(a[i],b[j]) )      i++;
            else if( less(b[j],a[i]))  j++;
            else{
                if( k == 0) c[k++] = a[i];
                else{
                    if(less(c[k-1],a[i])) c[k++] = a[i];
                }
                i++;
                j++;
            }
        }

        return c;
    }


    public static boolean less (Comparable a, Comparable b){
        return a.compareTo(b) < 0;
    }


    public static void main(String[] args){
        Integer[] x = {1,5,7,8,8,8,9,11};
        Integer[] y = {3,6,7,8,9,9,9,12};

        System.out.println(Arrays.toString(intersection(x,y)));

    }
}
