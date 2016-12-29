package interview_problems;

import java.util.Arrays;

/**
 * Created by amazaspshaumyan on 12/18/16.
 */
public class FirstMissingPositiveNumber {

    /*
    Returns first missing positive entry of array
     */
    public static int firstMissing(int[] x){
        int[] ans = new int[x.length];
        for(int j=0; j<x.length; j++ ){
            if(x[j]>0 && x[j]<x.length) ans[x[j]] = x[j];
        }

        for(int j=1; j<ans.length; j++){
            if(ans[j]!=j) return j;
        }
        return ans[ans.length-1]+1;
    }


    /*
    Returns first missing positive entry of array
     */
    public static int firstMissingEffective(int[] x){
        int i = 0;
        while(i<x.length){
            // first two conditions check that element can correspond to index of array
            // third condition checks that they are not in their required position
            // fourth condition checks that there is no duplicate already at their position
            if( x[i]>0 && x[i]<x.length && x[i]-1!=i && x[x[i]-1]!=x[i]) exch(x,i,x[i]-1);
            else i++;
        }

        // second pass
        for(int j=0; j < x.length; j++){
            if( x[j]-1!=j) return j+1;
        }
        return x.length+1;

    }


    public static void exch(int[] x, int i, int j){
        int temp = x[i];
        x[i]     = x[j];
        x[j]     = temp;
    }

    public static void main(String[] args){
        int[] x = {1,2,3,6,4,5,10,-10,8,9};
        int j   = firstMissingEffective(x);
        System.out.println(j);
    }
}
