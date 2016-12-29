package interview_problems;

import java.util.Arrays;

/**
 * Created by amazaspshaumyan on 12/11/16.
 */
public class PhotoTeam {


    public static boolean photoTeams(int[] a, int[] b){

        Arrays.sort(a);
        Arrays.sort(b);
        boolean firstTeam = true, secondTeam = true;

        for(int j=0; j < a.length; j++){
            if(a[j] > b[j]) firstTeam   = false;
            if(b[j] > a[j]) secondTeam  = false;

            if(!firstTeam  && !secondTeam) return false;
        }

        return true;
    }


    public static void main(String[] args){
        int[] x = {6,3,1,5,10};
        int[] y = {2,1,0,4,11};
        System.out.println(photoTeams(x,y));
    }
}
