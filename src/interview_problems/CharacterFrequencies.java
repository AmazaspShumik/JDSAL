package interview_problems;

import java.util.Arrays;

/**
 * Created by amazaspshaumyan on 12/9/16.
 */
public class CharacterFrequencies {

    public static void sortedCharFrequencises(String str){
        char[] a     = str.toCharArray();
        Arrays.sort(a);
        int counter  = 0;
        for(int i = 0; i < a.length-1; i++){
            counter++;
            if(a[i] != a[i+1]) {
                System.out.println(print(a[i], counter));
                counter = 0; // reset counter
            }
        }
        System.out.println(print(a[a.length-1],counter));

    }

    public static String print(char c, int counts){
        return " char = " + Character.toString(c) + " count = " + Integer.toString(counts);
    }

    public static void main(String[] args){
        String s = "abbbaacdefgaccbggggggg";
        sortedCharFrequencises(s);
    }
}
