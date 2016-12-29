package interview_problems;

/**
 * Created by amazaspshaumyan on 12/14/16.
 */
public class IntegerSquareRoot {


    public static int intSquareRoot(int k){
        int lo  = 0, hi = k;
        int mid  = lo + (hi-lo)/2;
        int diff = getDiff(mid,k);
        while(hi>=lo){
            if(diff > 0) hi = mid;
            else if( diff < 0) {
                if( getDiff(mid+1,k) >= 0 ) return mid;
                lo = mid+1;
            }
            else return mid;
            //System.out.println("HU");
            //System.out.println(hi);
            //System.out.println(lo);
            mid  = lo + (hi-lo)/2;
            diff = mid*mid - k;
        }
        return mid;
    }

    private static int getDiff(int mid, int k){
        return mid*mid - k;
    }


    public static void main(String[] args){
        System.out.println(intSquareRoot(6));
    }
}
