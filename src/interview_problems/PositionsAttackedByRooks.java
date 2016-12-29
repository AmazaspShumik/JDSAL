package interview_problems;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Solves problem 6.25 in EPI
 */
public class PositionsAttackedByRooks {


    public static Integer[][] positionsAttacked(Integer[][] pos){
        Set<Integer> rows = new HashSet<>();
        Set<Integer> cols = new HashSet<>();

        for(int j = 0; j < pos.length; j++) {
            for (int i = 0; i < pos[j].length; i++) {
                if(pos[j][i]==0) {
                    rows.add(j);
                    cols.add(i);
                }
            }
        }

        for(int j = 0; j < pos.length; j++) {
            for (int i = 0; i < pos[j].length; i++) {
                if(rows.contains(j) || cols.contains(i)) {
                    pos[j][i] = 0;
                }
            }
        }

        return pos;
    }


    public static int[][] positionsAttackedEffective(int[][] pos){
        return pos;
    }


    // ================================ Helper Methods ===============================================================


    public static Integer[][] fillOnes(Integer[][] pos){
        for(int j = 0; j < pos.length; j++) {
            for (int i = 0; i < pos[j].length; i++) {
                pos[j][i] = 1;
            }
        }
        return pos;
    }


    public static String toStringTwoDim(Integer[][] x){
        String s = "";
        for(int j = 0; j < x.length; j++) s+=("\n "+Arrays.toString(x[j]));
        return s;
    }


    public static void main(String[] args){
        Integer[][] x = new Integer[8][8];
        x = fillOnes(x);
        x[0][1] = 0;
        x[3][4] = 0;
        System.out.println(toStringTwoDim(positionsAttacked(x)));
    }
}
