package interview_problems;

/**
 * Created by amazaspshaumyan on 12/13/16.
 */
public class MedianSortedArrays {


    public static Comparable median(Comparable[] x, Comparable[] y){
        int i  = 0, j = 0 ;
        int U  = x.length, V = y.length;
        int N2 = (U+V-1) / 2  ;
        Comparable[] v;

        while( i < U && j < V){
            if(x[i].compareTo(y[j])<0) {
                if( i+j == N2 ) return x[i];
                i++;
            } else{
                if( i+j == N2 ) return y[j];
                j++;
            }
        }

        while(i < U) {
            if( i+j == N2 ) return x[i];
            i++;
        }

        while(j < V){
            if( i+j == N2 ) return y[j];
            j++;
        }

        return y[0];
    }


    public static void main(String[] args){
        Integer[] x = {0,0,0,3,4,5};
        Integer[] y = {1,2,8,9,12};
        System.out.println(median(x,y));
    }
}
