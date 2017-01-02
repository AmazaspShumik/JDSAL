package interview_problems;


import java.util.Set;
import java.util.HashSet;

/**
 * Solution to problem 13.11
 */
public class LongestDistinct {

    public static int longestDistinct(int[] A){
        Set<Integer> set = new HashSet<>();
        int lo = 0, hi = 0, loPrev = 0, hiPrev = 0;
        for(int j = 0; j<A.length; j++){
            if(!set.contains(A[j])){
                set.add(A[j]);
                hi = j;
            } else {
                set.clear();
                set.add(A[j]);
                if(hi-lo>hiPrev-loPrev){ loPrev = lo; hiPrev = hi;}
                lo = hi = j;
            }
        }
        if(hi-lo>hiPrev-loPrev){ loPrev = lo; hiPrev = hi;}
        return hiPrev - loPrev;
    }

    public static void main(String[] args){
        int[] A = {1,2,3,5,6,6,8,1,1,1,2,3,4,5,6,7,8,9};
        int limits = longestDistinct(A);
        System.out.println(limits);
    }

}
