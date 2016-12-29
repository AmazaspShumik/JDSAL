package interview_problems;

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Created by amazaspshaumyan on 12/9/16.
 */
public class QuickSelect {


    // ================   NonRecursive Implementation of QuickSelect  ===============================

    public static<T> T select(T[] a, int k, Comparator<T> comp){
        if( k > a.length ) throw new NoSuchElementException();
        k--;
        int lo=0, hi=a.length-1;
        while( hi > lo ){
            int j = partitionNonRecursive(a,lo,hi,comp);
            if( k > j){
                lo = j + 1;
                k  = k - j;
            }  else if ( k < j){
                hi = j-1;
            } else{
                return a[j];
            }
        }
        return a[lo];
    }

    public static<T> T select(T[] a, int k){
        return select(a,k,new DefaultComparator<>());
    }


    public static<T> int partitionNonRecursive(T[] a, int lo, int hi, Comparator<T> comp){
        int i = lo, j = hi + 1;
        T v   = a[lo];
        while(true){
            while( comp.compare(a[++i],v) < 0 ) if(i==hi) break;
            while( comp.compare(v,a[--j]) < 0 );
            if(i>=j) break;
            exch(a,i,j);
        }
        exch(a,lo,j);
        return j;
    }


    // ================   Recursive Implementation of QuickSelect Algorithm  ========================


    public static<T> T selectRecursive(T[] a, int k, Comparator<T> comp){
        exch(a,argmax(a,comp),a.length-1);
        quickSelect(a,k-1,0,a.length-1,comp);
        return a[k-1];
    }

    public static<T> T selectRecursive(T[] a, int k){
        return selectRecursive(a,k,new DefaultComparator<>());
    }

    /*
    Select k-th largest element in the array
     */
    public static<T> void quickSelect(T[] a , int k, int lo, int hi, Comparator<T> comp){
        if(hi-lo<=1) return;
        int j = partition(a,lo,hi,comp);
        if(k > j)            quickSelect(a,k-j,j+1,hi,comp);
        else if( k < j)      quickSelect(a,k,lo,j,comp);
        else                 return;

    }


    private static<T> int partition(T[] a, int lo, int hi, Comparator<T> comp){
        int i = lo, j = hi ;
        T v   = a[lo];
        while(true){
            while(comp.compare(a[++i],v) < 0);
            while(comp.compare(v,a[--j]) < 0);
            if(i >= j) break;
            exch(a,i,j);
        }
        exch(a,lo,j);
        return j;
    }



    // ================================  Helper Methods ===============================


    public static void exch(Object[] a, int i, int j){
        Object v     = a[i];
        a[i]         = a[j];
        a[j]         = v;
    }


    public static<T> int argmax(T[] a, Comparator<T> comp){
        int argmax = 0;
        for(int j=0; j < a.length; j++){
            if(comp.compare(a[argmax],a[j])<0) argmax = j;
        }
        return argmax;
    }


    public static void main(String[] args){
        Integer[] x = {14,1,2,7,4,12,6,8,3,9,10,11,5,13,15};
        //System.out.println(selectRecursive(x,6));
        System.out.println(select(x,6));
    }
}
