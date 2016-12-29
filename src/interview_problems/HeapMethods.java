package interview_problems;

import java.util.Comparator;

/**
 * Created by amazaspshaumyan on 12/5/16.
 */
public class HeapMethods {

    private static class InverseComparator<T> implements Comparator<T> {

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


    private static<T> boolean isMinHeap(T[] a, int j, Comparator<T> comp){
        int N    = a.length;
        int left = 2*j+1;
        // check whether there are any children
        if(left >= N) return true;
        // if here , then left child already exist, so check whether it is larger
        if(comp.compare(a[j],a[left]) > 0) return false;
        if(left+1 < N && comp.compare(a[j],a[left]) > 0) return false;
        return isMinHeap(a,left,comp) && isMinHeap(a,left+1,comp);
    }

    public static<T> boolean isMinHeap(T[] a, Comparator<T> comp){
        return isMinHeap(a,0,comp);
    }

    public static<T> boolean isMaxHeap(T[] a, Comparator<T> comp){
        Comparator<T> compar = new InverseComparator<>(comp);
        return isMinHeap(a,0,compar);
    }

    public static<T> boolean isMinHeap(T[] a){
        return isMinHeap(a,new DefaultComparator<>());
    }

    public static<T> boolean isMaxHeap(T[] a){
        return isMaxHeap(a,new DefaultComparator<>());
    }


    public static void main(String[] args){
        Integer[] x = {9, 9, 6, 3, 1, 1, 0};
        System.out.println(isMaxHeap(x));
    }
}
