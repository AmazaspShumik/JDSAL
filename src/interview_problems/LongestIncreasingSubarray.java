package interview_problems;

import java.util.Arrays;

/**
 * Solves problem 6.11 in EPI
 */
public class LongestIncreasingSubarray {

    /*
    Finds start and end of longest contigious increasing subarray
     */
    public static int[] lis(int[] x){
        int leftMax = 0, rightMax = 0, leftCurr = 0, rightCurr = 0, max = 0;

        for(int j = 1; j < x.length; j++){
            if(x[j] > x[j-1]) rightCurr++;
            else{
                if(rightCurr - leftCurr > max) {
                    leftMax = leftCurr; rightMax = rightCurr;
                    max     = rightMax - leftMax;
                }
                leftCurr = j; rightCurr = j;
            }
        }
        int[] ans = {leftMax,rightMax};
        return ans;
    }


    public static void main(String[] args){
        int[] x = {2,11,3,5,13,7,19,17,23};
        System.out.println(Arrays.toString(lis(x)));
    }
}
