package interview_problems;

/**
 * Created by amazaspshaumyan on 12/14/16.
 */
public class BinarySearch {

    //==================  Simple binary search on array =================================
    public static int search(Comparable[] a, Comparable key){
        return searchRecursive(a,key,0,a.length);
    }

    private static int searchRecursive(Comparable[] a, Comparable key, int lo, int hi){
        if( hi<=lo ) return -1;
        int mid = lo + (hi-lo)/2;
        int cmp = a[mid].compareTo(key);
        if(cmp > 0)        return searchRecursive(a,key,lo,mid);
        else if(cmp < 0)   return searchRecursive(a,key,mid+1,hi);
        else               return mid;

    }


    public static void main(String[] args){
        Integer[] x = {1,2,6,8,10,12,14};
        System.out.println(search(x,12));
    }
}
