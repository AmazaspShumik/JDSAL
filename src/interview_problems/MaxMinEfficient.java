package interview_problems;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Created by amazaspshaumyan on 12/13/16.
 */
public class MaxMinEfficient {

    /*
    Efficient implementation of simultaneous Min and Max
     */
    public static Comparable[] effMinMax(Comparable[] a){
        if(a.length==1) throw new NoSuchElementException("Need to have at least two elements");

        // Initialise
        Comparable min = a[1], max = a[0];
        if(a[0].compareTo(a[1]) <= 0 ) {
            min = a[0];
            max = a[1];
        }

        // Iterate through array
        for(int j = 3; j < a.length; j+=2){
            if( a[j].compareTo(a[j-1]) <= 0){
                if( a[j].compareTo(min)<0) min = a[j];
                if( a[j-1].compareTo(max)>0) max = a[j-1];
            } else{
                if( a[j].compareTo(max)>0) max = a[j];
                if( a[j-1].compareTo(min)<0) min = a[j-1];
            }
        }
        if( a.length%2 == 1) {
            min = (a[a.length-1].compareTo(min)>0) ? min : a[a.length-1];
            max = (a[a.length-1].compareTo(max)<0) ? max : a[a.length-1];
        }

        Comparable[] minMax = {min,max};
        return minMax;
    }


    public static void main(String[] args){
        Integer[] x  = {2,13,-4};
        Comparable[] mm = effMinMax(x);
        System.out.println(Arrays.toString(mm));
    }
}
