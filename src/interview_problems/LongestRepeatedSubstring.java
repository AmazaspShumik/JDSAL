package interview_problems;

import java.util.Arrays;

/**
 * Created by amazaspshaumyan on 12/14/16.
 */
public class LongestRepeatedSubstring {

    public static String lcp(String s, String t){
        int N = Math.min(s.length(), t.length());
        for(int j = 0; j < N; j++){
            if(s.charAt(j)!=t.charAt(j)) return s.substring(0,j);
        }
        return s.substring(0,N);
    }


    public static String lrs(String s){
        int N        = s.length();

        // construct array of strings
        String[] str = new String[N-1];
        for(int j = 0; j < s.length()-1; j++){
            str[j] = s.substring(j,N);
        }

        // sort in lexicographic order
        Arrays.sort(str);
        System.out.println(Arrays.toString(str));

        // find longest common prefix for nearby strings
        String lrs = "", temp = "";
        for(int j = 0; j < str.length-1; j++){
            temp = lcp(str[j],str[j+1]);
            if(temp.length() >= lrs.length()) lrs = temp;
        }
        return lrs;
    }


    public static void main(String[] args){

        String s = "ABABABAABABABACDCDCDCD";
        System.out.println(lrs(s));

    }
}
