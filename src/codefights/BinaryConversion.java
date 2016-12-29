package codefights;

/**
 * Created by amazaspshaumyan on 12/14/16.
 */
public class BinaryConversion {


    public static String binaryConversion(int k){
        String bin = "";
        for(int d = 1; k>0; k/=2,d*=10){
            bin = ((Integer)(k%2)).toString()+bin;
        }
        return bin;
    }


    public static void main(String[] args){
        System.out.println(binaryConversion(123));

        System.out.println((1<<3));
    }
}
