package codefights;

/**
 * Created by amazaspshaumyan on 12/12/16.
 */
public class sumRepresentation {

    static int countSumOfTwoRepresentations2(int n, int l, int r) {

        // handle corner cases
        if(  n < l )       return 0;
        if(2*r < n )       return 0;

        int A = l, B = r;

        while(A<=B){

            if(A+B > n)      B--;
            else if(A+B < n) A++;
            else             {
                int nElements = B - A + 1;
                return nElements/2 + nElements%2;
            }
        }
        return 0;
    }


    public static void main(String[] args){
        int x = countSumOfTwoRepresentations2(6,2,4);
        System.out.println(x);
    }
}
