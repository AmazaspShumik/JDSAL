package interview_problems;

import java.util.Arrays;

/**
 * Created by amazaspshaumyan on 12/22/16.
 */
public class SpiralOrder {

    public static void recursiveSpiralPrint(int[][] matrix){
        recursiveSpiralPrint(matrix,0,matrix.length-1, 0, matrix[0].length-1);
    }


    public static void recursiveSpiralPrint(int[][] matrix, int lr, int ur, int lc, int uc){
        if(lr==ur && lc==uc) {
            System.out.println(matrix[lr][lc]);
            return;
        }
        if(lr > ur || lc > uc) return;
        for(int j = lc; j <= uc; j++)    System.out.println(matrix[lr][j]);
        for(int j = lr+1; j <= ur; j++)  System.out.println(matrix[j][uc]);
        for(int j = uc-1; j >= lc; j--)  System.out.println(matrix[ur][j]);
        for(int j = ur-1; j > lr; j--)   System.out.println(matrix[j][lc]);
        recursiveSpiralPrint(matrix,lr+1,ur-1,lc+1,uc-1);
    }


    public static int[][] recursiveSpiralSum(int[][] matrix){
        recursiveSpiralSum(matrix, 0, matrix.length-1, 0, matrix[0].length-1,0);
        return matrix;
    }

    private static void recursiveSpiralSum(int[][] matrix, int lr, int ur, int lc, int uc, int prev){
        if(lr > ur || lc > uc) return;
        if(lr==ur && lc==uc) {
            matrix[lr][lc] += matrix[lr][lc-1];
            return;
        }
        int counter = lc > 0 ? matrix[lr][lc-1] : 0;
        matrix[lr][lc]+= counter;
        for(int j=lc+1; j <= uc; j++)    { matrix[lr][j] += matrix[lr][j-1];}
        for(int j=lr+1; j <= ur; j++)    { matrix[j][uc] += matrix[j-1][uc];}
        for(int j=uc-1; j >= lc; j--)    { matrix[ur][j] += matrix[ur][j+1];}
        for(int j=ur-1; j > lr; j--)     { matrix[j][lc] += matrix[j+1][lc];}
        recursiveSpiralSum(matrix, lr+1, ur-1,lc+1,uc-1,counter);
    }



    public static void iterativeSpiralPrint(int[][] matrix){
        int lr = 0, ur  = matrix.length-1, lc = 0, uc = matrix[0].length-1;
        while(lr <= ur && lc <= uc){
            if(lr==ur && lc==uc) System.out.println(matrix[lr][lc]);
            for(int j = lc; j < uc; j++) System.out.println(matrix[lr][j]);
            for(int j = lr; j < ur; j++) System.out.println(matrix[j][uc]);
            for(int j = uc; j > lc; j--) System.out.println(matrix[ur][j]);
            for(int j = ur; j > lr; j--) System.out.println(matrix[j][lr]);
            lr+=1; ur-=1; uc-=1;lc+=1;
        }
    }


    public static int[][] iterativeSpiralSum(int[][] matrix){
        int lr = 0, ur = matrix.length-1, lc = 0, uc = matrix[0].length-1, count = 0;
        if(lr==ur && lc==uc) return matrix;
        while(lr<=ur && lc <= uc){
            count  = lc > 0 ? matrix[lr][lc-1] : 0;
            matrix[lr][lc]+= count;
            for(int j=lc+1; j <= uc; j++)   matrix[lr][j] += matrix[lr][j-1];
            for(int j=lr+1; j <= ur; j++)   matrix[j][uc] += matrix[j-1][uc];
            for(int j=uc-1; j >= lc; j--)   matrix[ur][j] += matrix[ur][j+1];
            for(int j=ur-1; j > lr; j--)    matrix[j][lc] += matrix[j+1][lc];
            lr+=1; lc+=1; uc-=1; ur-=1;
        }
        return matrix;
    }


    private static int[][] fillOnes(int[][] x){
        for(int j = 0; j < x.length; j++){
            for(int i = 0; i < x[j].length; i++){
                x[i][j] = 1;
            }
        }
        return x;
    }


    public static String TwoDimToString(int[][] x){
        String s = "";
        for(int j = 0; j< x.length; j++){
            s = s+Arrays.toString(x[j])+"\n";
        }
        return s;
    }


    public static void main(String[] args){
        // create two
        int[][] y = new int[4][4];

        //int[][] x = {{1,2,3},{8,9,4},{7,6,5}};
        //recursiveSpiralPrint(x);

        int[][] x1 = iterativeSpiralSum(fillOnes(y));
        int[][] y1 = iterativeSpiralSum(fillOnes(new int[3][3]));
        System.out.println(TwoDimToString(x1));
        System.out.println(TwoDimToString(y1));
        //iterativeSpiralPrint(x1);

    }
}
