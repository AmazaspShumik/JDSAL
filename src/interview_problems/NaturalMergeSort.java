package interview_problems;

/**
 * Created by amazaspshaumyan on 12/1/16.
 */
public class NaturalMergeSort {


    private static void exch(Comparable[] a, int i, int j){
        Comparable temp = a[i];
        a[i]            = a[j];
        a[j]            = temp;
    }


    private static boolean less( Comparable a, Comparable b){
        return a.compareTo(b)<=0;
    }


    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi){
        int i = lo, j = mid+1;

        // copy array
        for(int k=lo; k <= hi; k++) aux[k] = a[k];

        // move back
        for(int k=lo; k<=hi; k++){
            if(i>mid)                     a[k] = aux[j++];
            else if(j>hi)                 a[k] = aux[i++];
            else if(less(aux[i],aux[j]))  a[k] = aux[i++];
            else                          a[k] = aux[j++];
        }
    }

    private static void sortNatural( Comparable[] a, Comparable[] aux){
        boolean sorted = false;
        int N          = a.length;
        // simple insertion sort for small array
        if(N==1) return;
        if(N==2){
            if(!less(a[0],a[1])) exch(a,0,1);
            return;
        }

        // if array is large enough
        int lo,mid,hi;
        while(!sorted){
            lo = mid = hi = 0;
            sorted        = true;
            for(int p=0; p < N-1; p++){

                if(!less(a[p],a[p+1]) && mid <= hi){
                    mid    = p;
                    sorted = false;
                    if(mid==N-2) merge(a,aux,lo,mid,N-1);
                } else if(!less(a[p],a[p+1]) && mid > hi){
                    hi     = p;
                    merge(a,aux,lo,mid,hi);
                    lo = hi+1;
                } else{
                    if(p==N-2 && mid > hi) merge(a,aux,lo, mid, N-1);
                }
            }

        }
    }

    public static void sort(Comparable[] a){
        Comparable[] aux = new Comparable[a.length];
        sortNatural(a,aux);
    }


    public static void main(String[] args){
        Integer[] x = {4,2,3,1,1,1,6,5,7,10,9,8,11};
        Integer[] y = {2,1};
        sort(x);
        for(int k=0; k< x.length; k++){
            System.out.println(x[k]);
        }
    }


}
