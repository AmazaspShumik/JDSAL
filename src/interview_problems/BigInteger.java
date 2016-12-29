package interview_problems;

import java.util.Arrays;

/**
 * Created by amazaspshaumyan on 12/17/16.
 */
public class BigInteger {

    private int[] arr;

    public BigInteger(int[] x){
        this.arr = x;
    }

    private void resize(int cap){
        int[] temp = new int[cap];
        System.arraycopy(arr,0,temp,(cap-arr.length),arr.length);
        arr        = temp;
    }

    public void increment(){

        for(int j = arr.length-1; j>=0; j-- ){
            boolean over = arr[j]+1<10;
            arr[j] = (arr[j] + 1) % 10;
            if(over) break;
            else{
                if(j==0) {
                    resize(arr.length+1);
                    arr[0] = 1;
                    break;
                }
            }
        }

    }

    public String toString(){
        return Arrays.toString(arr);
    }


    public static String polygonPlotOfLand(int n, int s, int[] prices) {
        double area = prices[0]*s*s*n/(4*Math.tan(Math.toRadians(180.0/n)));
        double perimeter = prices[1]*s*n;
        double cost      = area+perimeter;
        // round to 2 decimal points
        double val       = Math.round(cost*100);
        val              = val/100;
        String str       = ((Double)(val)).toString();
        if(str.charAt(str.length()-2)=='.') str = str+"0";
        return str;
    }


    public static void main(String[] args){
        int[] x        = {9,9,9};
        BigInteger big = new BigInteger(x);
        big.increment();
        System.out.println(big.toString());
        int[] prices = {12,62};
        System.out.println(polygonPlotOfLand(6,10,prices));
    }
}
