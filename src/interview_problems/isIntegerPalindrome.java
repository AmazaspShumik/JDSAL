package interview_problems;

/**
 * Created by amazaspshaumyan on 12/17/16.
 */
public class isIntegerPalindrome {

    public static boolean isPalindrome(int x){
        int temp = x, d = 1, reverse = 0;
        for(int j=0; temp>0; j++, d*=10, temp/=10){
            reverse += d*(temp%10);
        }
        return (reverse^x)==0;
    }

    public static void main(String[] args){
        System.out.println(isPalindrome(121));
    }

}
