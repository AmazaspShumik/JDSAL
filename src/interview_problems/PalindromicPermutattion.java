package interview_problems;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Created by amazaspshaumyan on 12/22/16.
 */
public class PalindromicPermutattion {


    public static boolean palindromicPermutation(String s){
        Character cur;
        Map<Character,Integer> map = new HashMap<>();
        for(int j=0; j < s.length(); j++){
            cur = s.charAt(j);
            if(map.containsKey(cur)) map.put(cur,map.get(cur)+1);
            else                     map.put(cur,1);
        }
        int oddCounts = 0;
        for(Character c: map.keySet()){
            if( map.get(c)%2==1){
                oddCounts++;
                if(oddCounts>1) return false;
            }
        }
        return true;
    }


    public static void main(String[] args){
        String s = "amazaspzasm";
        String k = "xryuyrx";
        System.out.println(palindromicPermutation(k));
        Character c = 'a';
    }

}
