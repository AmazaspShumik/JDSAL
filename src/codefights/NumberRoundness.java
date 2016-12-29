package codefights;

/**
 * Created by amazaspshaumyan on 12/12/16.
 */
public class NumberRoundness {

    static boolean increaseNumberRoundness(int n) {
        boolean firstNonzero = false;
        for(int k = n; k >= 10; k/=10){
            if(k%10!=0) firstNonzero = true;
            else{
                if(firstNonzero) return true;
            }
        }
        return false;
    }

    public static void main(String[] args){
        boolean increase = increaseNumberRoundness(1101000);
        System.out.println(increase);
    }
}
