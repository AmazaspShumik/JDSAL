package interview_problems;

import jdk.nashorn.internal.runtime.arrays.ArrayIndex;

import java.util.Comparator;

/**
 * Created by amazaspshaumyan on 12/21/16.
 */
public class BinarySearchPromblems {


    //=============================== First & Last & Number of occurences===============================================

    /*
    Find first occurrence of element
     */
    public static<T> int firstOccurrence(T[] a, T key, Comparator<T> comp){
        int lo = 0, hi = a.length-1, res = -1;
        while(hi >= lo){
            int mid = lo + (hi-lo)/2;
            int cmp = comp.compare(key, a[mid]);
            if( cmp < 0 )      hi = mid-1;
            else if( cmp > 0)  lo = mid+1;
            else               {res = mid; hi = mid-1;}
        }
        return res;
    }

    /*
    Find last occurrence of element
     */
    public static<T> int lastOccurence(T[] a, T key, Comparator<T> comp){
        int lo = 0, hi = a.length-1, res = -1;
        while(hi>=lo){
            int mid = lo+(hi-lo)/2;
            int cmp = comp.compare(key,a[mid]);
            if( cmp > 0)         lo = mid+1;
            else if( cmp < 0 )   hi = mid-1;
            else                { res = mid; lo = mid+1;}
        }
        return res;
    }


    /*
    Find number of occurences of element in sorted array
     */
    public static<T> int numberOfOccurences(T[] a, T key, Comparator<T> comp){
        int lo = 0, hi = a.length-1, left = -1, right = -1;

        while(hi>=lo){
            int mid = lo + (hi-lo)/2;
            int cmp = comp.compare(key,a[mid]);
            if( cmp < 0 )       hi = mid - 1;
            else if( cmp > 0)   lo = mid + 1;
            else{
                right = left = mid;
                left  = firstOccur(a,key,comp,lo,mid);
                right = lastOccur(a,key,comp,mid,hi);
                return right-left+1;
            }
        }
        return 0;
    }


    private static<T> int firstOccur(T[] a, T key, Comparator<T> comp, int lo, int hi){
        int res = hi;
        while(hi>=lo){
            int mid = lo+(hi-lo)/2;
            int cmp = comp.compare(key,a[mid]);
            if( cmp == 0) {res = mid; hi = mid-1;}
            else          {lo  = mid+1; }
        }
        return res;
    }


    private static<T> int lastOccur(T[] a, T key, Comparator<T> comp, int lo, int hi){
        int res = lo;
        while(hi>=lo) {
            int mid = lo + (hi - lo) / 2;
            int cmp = comp.compare(key, a[mid]);
            if (cmp == 0) {
                res = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return res;
    }


    // =============================== Floor & Ceiling =========================================

    /*
    Finds first element larger than key
     */
    public static<T> int firstGreater(T[] a, T key, Comparator<T> comp){
        int lo = 0, hi = a.length-1, res = -1;
        if(comp.compare(key,a[hi])>=0) return -1;
        while(hi >= lo){
            int mid = lo+(hi-lo)/2;
            int cmp = comp.compare(key,a[mid]);
            if( cmp > 0)       lo = mid+1;
            else if(cmp < 0) {
                res = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return res;
    }


    /*
    Finds largest element smaller than key
     */
    public static<T> int lastSmaller(T[] a, T key, Comparator<T> comp){
        int lo = 0, hi = a.length-1, res = -1;
        if( comp.compare(key,a[lo]) <= 0 ) return -1;

        while(hi>=lo){
            int mid = lo+(hi-lo)/2;
            int cmp = comp.compare(key,a[mid]);
            if( cmp < 0 )      hi = mid-1;
            else if( cmp >0 ){
                res = mid;
                lo  = mid+1;
            }else{
                hi  = mid-1;
            }
        }
        return res;
    }

    /*
    Largest element that is smaller or equal to key
     */
    public static<T> int floor(T[] a, T key, Comparator<T> comp){
        int lo = 0, hi = a.length-1, res = -1;
        if( comp.compare(key,a[lo]) < 0 ) return -1;

        while(hi >=lo){
            int mid = lo+(hi-lo)/2;
            int cmp = comp.compare(key,a[mid]);
            if( cmp < 0)  hi = mid-1;
            else{
                res = mid;
                lo  = mid+1;
            }
        }
        return res;
    }

    /*
    Smallest element that is larger or equal to key
     */
    public static<T> int ceiling(T[] a, T key, Comparator<T> comp){
        int lo = 0, hi = a.length-1, res = -1;
        if( comp.compare(key,a[lo]) < 0 ) return -1;

        while(hi>=lo){
            int mid = lo+(hi-lo)/2;
            int cmp = comp.compare(key,a[mid]);
            if(cmp > 0) lo = mid+1;
            else{
                res = mid;
                hi  = mid-1;
            }
        }
        return res;
    }

    // ============================== A[i]==i Problem 12.3 in EPI ==================================

    public static int indexEqual(int[] a){
        int lo = 0, hi = a.length-1, res = -1;
        if((a[lo]<lo && a[hi]<hi) || (a[lo]>lo && a[hi]>hi)) return res;

        while(hi>=lo){
            int mid = lo + (hi-lo)/2;
            if(a[mid]-mid>0)      lo = mid+1;
            else if(a[mid]-mid<0) hi = mid-1;
            else                  return mid;
        }
        return res;
    }

    // =============================== Search Array of unknown length ================================================

    /*
    Searches array of unknown length
     */
    public static<T> int binarySearchUnknownLengthArrray(T[] a, T key, Comparator<T> comp){

        int lo = 0, hi = 1, dhi = 10;

        while(dhi>0){
            int cmp = comp.compare(key, a[hi]);
            if(cmp < 0)         return binarySearch(a,key,lo,hi-1,comp);
            else if( cmp == 0)  return hi;
            else {
                dhi = hi;
                while( approveLength(a,hi,dhi) ) dhi /= 2;
                lo  = hi+1;
                hi += dhi;
            }
        }
        return -1;
    }


    private static<T> int binarySearch(T[] a, T key, int lo, int hi, Comparator<T> comp){
        while(hi>=lo){
            int mid = lo + (hi-lo)/2;
            int cmp = comp.compare(key,a[mid]);
            if( cmp > 0 )     lo = mid+1;
            else if(cmp < 0)  hi = mid-1;
            else              return mid;
        }
        return -1;
    }

    private static boolean approveLength(Object[] a, int hi, int dhi){
        boolean approve = false;
        try{
            Object k = a[hi+dhi];
        } catch (ArrayIndexOutOfBoundsException e){
            approve = true;
        }
        return approve;
    }


    // ===================================  Integer square root ===================================


    public static int sqrtInt(int k){
        int lo = 1, hi = k, res = lo;
        while(hi >= lo){
            int mid = lo + (hi-lo)/2;
            int cmp = mid*mid-k;
            if( cmp > 0) hi = mid-1;
            else{
                res  = mid;
                lo = mid+1;
            }
        }
        return res;
    }



    // =================================== Unit Tests ==============================================



    public static void main(String[] args){
        /*
        Integer[] x = {0,1,1,1,2,3,4,5,5,5,7,9,11,17};
        Comparator<Integer> comp = new DefaultComparator<>();

        // ------------------------ Testing first & last & number of occurences -----------------------

        System.out.println("First & Last & Number of occurences");
        int first = firstOccurrence(x,1,comp);
        int last  = lastOccurence(x,1,comp);
        int tot   = numberOfOccurences(x,1,comp);
        System.out.println(first);
        System.out.println(last);
        System.out.println(tot);


        // ------------------------ Testing first element greater than k ------------------------------
        System.out.println("\n First Greater than key");

        int gt = firstGreater(x,6,comp);
        System.out.println(x[gt]);

        int lt = lastSmaller(x,6,comp);
        System.out.println(lt);

        int fl = floor(x,5,comp);
        System.out.println(fl);

        int cl = ceiling(x,7,comp);
        System.out.println(cl);


        // -------------------------------- Testing index equality ----------------------------------
        System.out.println("\n Index equality problem");

        int[] no   = {-1,0,1,2,3,4,5,6};
        int[] yes  = {-1,0,1,3,4,5,7};
        System.out.println(indexEqual(no));
        System.out.println(indexEqual(yes));

        // --------------------------- Searching Array of unknown length ----------------------------
        */
        System.out.println("\n Searching Array of unknown length");
        Integer[] X = {1,2,3,5,5,6,7,8};
        System.out.println(binarySearchUnknownLengthArrray(X,8,new DefaultComparator<>()));


        // ---------------------------- Integer square root ----------------------------------

        System.out.println("\n Integer square root");
        System.out.println(sqrtInt(9));



    }







}
