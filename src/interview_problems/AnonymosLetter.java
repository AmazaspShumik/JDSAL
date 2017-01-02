package interview_problems;

import java.util.Map;
import java.util.HashMap;

/**
 * Solution to problem 13.3 in EPI
 */
public class AnonymosLetter {

    public static boolean anonymosLetterConstructibel(String letter, String text){

        // Correction from solution
        if(letter.length()!=text.length()) return false;

        // contruct frequency maps & check
        Map<Character,Integer> mapLetter = charFrequency(letter);

        for(int j=0; j<text.length();j++){
            Character ch = text.charAt(j);
            if(mapLetter.containsKey(ch)){
                if(mapLetter.get(ch)==1) mapLetter.remove(ch);
                else                     mapLetter.put(ch,mapLetter.get(ch)-1);
            }
            if(mapLetter.size()==0) return true;
        }
        return mapLetter.size()==0;
    }

    public static Map<Character,Integer> charFrequency(String s){
        Map<Character,Integer> map = new HashMap<>();
        for(int j=0; j<s.length(); j++){
            Character ch = s.charAt(j);
            if(!map.containsKey(ch)) map.put(ch,1);
            else map.put(ch, map.get(ch)+1);
        }
        return map;
    }
}
