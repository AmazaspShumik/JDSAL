package interview_problems;

import java.util.Arrays;

/**
 * Created by amazaspshaumyan on 12/13/16.
 */
public class DuplicateMissing {

    /*
    Find duplicate and missing element in array a.
    Solves Problem 12.15 from EPI
     */
    public static int[] duplicateMissing(int[] a){
        int idx;
        for(int j=0; j < a.length; j++){
            idx = a[j];
            if(idx != a[idx]) exch(a,idx,j);
        }

        System.out.println(Arrays.toString(a));

        int[] duplMiss = new int[2];
        for(int j=0; j < a.length; j++){
            if(a[j]!=j){
                duplMiss[0] = a[j];
                duplMiss[1] = j;
            }
        }
        return duplMiss;
    }


    public static void exch(int[] a, int i, int j){
        int temp = a[i];
        a[i]     = a[j];
        a[j]     = temp;
    }


    public static void main(String[] args){
        int[] z = {2,3,0,0,1,5};
        int[] dm = duplicateMissing(z);

    }
}
