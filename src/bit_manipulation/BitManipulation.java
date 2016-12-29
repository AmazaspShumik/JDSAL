package bit_manipulation;

import java.util.NoSuchElementException;

/**
 * Created by amazaspshaumyan on 12/15/16.
 */
public class BitManipulation {

    //   =====================  get i-th Bit from left or right ===========================

    public static boolean getRightBit(int n, int k){
        return (n & (1<<k) )!=0;
    }

    public static boolean getLeftBit(int n, int k){
        int left = 31 - k + 1;
        return (n & (1<<left))!=0;
    }

    //  ====================== set/kill i-th Bit from left ================================

    public static int setBit(int n , int k){
        return n | (1<<k);
    }

    public static int killBit(int n, int k){
        return n^(1<<k);
    }

    //  ==== ===========================  other  ==========================================

    public static int computeNonZeroBits(int n){
        int count = 0, mask = 0;
        for(int j=0; n > 0; j++){
            mask = 1<<j;
            if((n&mask)!=0){
                count++;
                n = n & ~mask;
            }
        }
        return count;
    }


    public static int insertBitWise(int N, int M, int j, int i){
        int mask = ~(((1<<(i-j))-1)<<j);
        N        = N&mask;
        N        = N | (M<<j);
        return N;
    }


    /*
    Finds next largest integer
     */
    public static int nextLargest(int n){
        int c0, c1;
        int temp = n;

        // find first zero in bit representation of integer
        for(c0 = 0; (temp&1)==0 && temp > 0; c0++,temp>>=1) System.out.println(temp);
        //System.out.println(c0);

        // find first one in bit representation, after first series of 1's
        for(c1 = c0; (temp&1)==1 && temp>0; c1++,temp>>=1)  System.out.println(temp);
        //System.out.println(c1);

        // check you are not at MSB
        int p = c1;
        n = n | (1<<p);
        //System.out.println(n);

        // clear all bit from 0 to p
        n = n & ~((1<<p)-1);

        // insert c1-1 one-bits
        n = n | ((1<<(c1-c0-1))-1);
        return n;
    }


    public static int nextSmallest(int k){
        int c0 = 0, c1 = 0, temp = k;

        // find first zero after sequence of ones
        for( c0=0 ; (temp&1)!=0 && temp>0; c0++, temp>>=1);

        // k consisted only out of 1's in rightmost bit positions, then there is no element smaller
        if(temp==0) throw new NoSuchElementException();

        // find first 1 after sequence of zeros
        for( c1 = c0; (temp&1)==0 && temp>0; c1++, temp>>=1);

        // flip that 1 bit from 1 to zero
        k = k & ~(1<<c1);

        // clear all bits to the right of flipped one
        k = k & ~((1<<c1)-1);

        System.out.println(k);

        // insert c0 + 1 ones
        k = k | (((1<<(c0+1))-1)<<(c1-c0-1));

        return k;
    }


    public static int mirrorBits(int k){
        int i = 0, temp = k, reverse = 0;
        for(i=0; temp>0; i++,temp>>=1){
            reverse = reverse<<1;
            reverse = reverse | (temp&1);
            System.out.println(reverse);
        }
        return reverse;
    }


    public static int firstRightMostZeroBit(int n){
        return n^(n+1)|(n+1);
    }



    public static void main(String[] args){
        int n = 12&(~(1<<2));
        //System.out.println(n);
        //System.out.println(1<<0);

        // get i-th bit
        //System.out.println(getRightBit(9,3));
        //System.out.println(getLeftBit(4,30));

        // set i-th bit
        //System.out.println(setBit(4,3));

        // compute non zero bits
        //System.out.println(computeNonZeroBits(1));
        //System.out.println(insertBitWise(64,8,1,4));


        //System.out.println("nextLargest");

        //System.out.println(nextLargest(16));
        //System.out.println(nextSmallest(16));


        // Mirror bits
        System.out.println(mirrorBits(97));
        String str = "AAbaa";
        str.toLowerCase();
        System.out.println(str);
    }
}
