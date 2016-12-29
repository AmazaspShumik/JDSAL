package codefights;

/**
 * Created by amazaspshaumyan on 12/12/16.
 */
public class Rounders {

    static int rounders(int value) {

        int d;
        int answer = 0, val = 0;

        for(d=1; value > 10; value/=10, d*=10) if( value%10 >= 5) value +=10;


        return value*d;
    }


    public static void main(String[] args){
        int k = rounders(1445);
        int x = rounders(1234);
        System.out.println(k);
        System.out.println(x);
    }
}
