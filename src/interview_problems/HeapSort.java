package interview_problems;

import java.util.Arrays;
import java.util.Comparator;



public class HeapSort {


    private static class InverseComparator<T> implements Comparator<T>{

        private Comparator<T> comp;

        InverseComparator(Comparator<T> comp){
            this.comp = comp;
        }

        public int compare(T a, T b){
            return -1*comp.compare(a,b);
        }

    }

    public static void exch(Object[] a, int i, int j){
        Object key = a[i];
        a[i]       = a[j];
        a[j]       = key;
    }

    public static<T> void sink(T[] a, int k, int N, Comparator<T> comp){
        int j;

        while(2*k + 1 < N){
            j = 2*k + 1;
            if(j < N-1 && comp.compare(a[j+1],a[j]) < 0) j++;
            if( comp.compare(a[j], a[k]) >= 0 ) break;
            exch(a,k,j);
            k = j;
        }
    }


    public static<T> void heapSort(T[] a, Comparator<T> comp){
        int N = a.length;
        int start = N/2 -1;

        // heapify array
        for( int j = start; j >= 0; j--){
            sink(a,j,N,comp);
        }

        // sort down
        while(N>0){
            exch(a,N-1,0);
            sink(a,0,--N,comp);
        }
    }

    public static<T> void heapSort(T[] a, boolean decreasing){
        Comparator<T> compar;
        if(decreasing){
            Comparator<T> comp = new DefaultComparator<>();
            compar = new InverseComparator<>(comp);
        } else{
            compar = new DefaultComparator<>();
        }
        heapSort(a,compar);
    }



    // Unit tests
    public static void main(String[] args){
        Integer[] x = {3,1,0,6,7,9,1};
        heapSort(x,false);
        System.out.println(Arrays.toString(x));
    }


}
