package codefights;

/**
 * Created by amazaspshaumyan on 12/12/16.
 */
public class Diagonal {

    static double getDiagonal(int colValue, int n, double angle){
        return n + angle*colValue;
    }


    static int countBlackCells(int n, int m){
        double angle = (double) -n / m;
        int black = 0;
        double yValPrev  = getDiagonal(0,n,angle);
        double yValNext;
        for(int j = 0; j< m; j++){
            yValNext     = getDiagonal(j+1,n,angle);
            for(int i = 0; i<n; i++){
                // check that leftmost point is below diagonal && rightmost above
                if( i <= yValPrev && i+1>=yValNext) black++;
            }
            yValPrev = yValNext;
        }
        return black;
    }


    static int countBlackCellsTwo(int n, int m){
        double angle   = (double) -n / m;
        int black = 0, j = 0;
        for(int i = n-1; i>=0; i--){

            while(i < getDiagonal(j,n,angle)){
                System.out.println("Diagonal "+((Double)getDiagonal(j,n,angle)).toString());
                black++;j++;
            }
            if(i == getDiagonal(j,n,angle) && i > 0) {
                black+=2;
            } else{
                j--;
            }

            System.out.println(black);
        }
        return black;
    }




    public static void main(String[] args){
        int i = 10,j=2;
        double angle = (double) -i / j;
        System.out.println(countBlackCellsTwo(3,3));
    }
}
