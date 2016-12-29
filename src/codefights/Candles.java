package codefights;

/**
 * Created by amazaspshaumyan on 12/12/16.
 */
public class Candles {

    static int candles(int candlesNumber, int makeNew) {
        int leftOvers = 0, burned = 0;
        while( candlesNumber > 0){
            candlesNumber--;
            leftOvers++;
            burned++;
            if(leftOvers >= makeNew){
                leftOvers=1;
                burned++;
            }
        }
        return burned;
    }


    public static void main(String[] args){
        System.out.println(candles(5,2));
    }
}
