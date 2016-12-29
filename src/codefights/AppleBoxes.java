package codefights;

/**
 * Square apple boxes
 *
 */
public class AppleBoxes {

    static int appleBoxesOne(int k) {
        int yellow = 0, red = 0;
        for(int j = 1; j <=k; j++){
            if(j%2==1) yellow += j*j;
            else       red    += j*j;
        }
        return Math.abs(red - yellow);
    }


    static int appleBoxesTwo(int k){
        if(k==1) return -1;
        int nEven = k / 2;
        int diff = 2*nEven*nEven+nEven;           // difference between red and yellow
        if( nEven*2 != k) diff -= k*k;
        return diff;
    }

    public static void main(String[] args){

        int first  = appleBoxesOne(6);
        int second = appleBoxesTwo(6);

        System.out.println(first);
        System.out.println(second);


    }
}
