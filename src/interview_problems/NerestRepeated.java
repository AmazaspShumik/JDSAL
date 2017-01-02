package interview_problems;

import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.ArrayDeque;

/**
 * Solves problem 13.8 in EPI
 */
public class NerestRepeated {

    public static int[] nearestRepeated(Object[] a){
        Map<Object,Integer> map = new HashMap<>();
        int min = a.length; int[] closestPair = new int[2];
        for(int j=0; j<a.length; j++){
            if(!map.containsKey(a[j])) map.put(a[j],j);
            else{
                if(j-map.get(a[j])<min){
                    min = j-map.get(a[j]);
                    closestPair[0] = map.get(a[j]);
                    closestPair[1] = j;
                }
                map.put(a[j],j);
            }
        }
        return closestPair;
    }

    public static void main(String[] args){
        String[] input = {"All","work","and" ,"no", "play", "makes", "for", "no", "work", "no", "fun", "and" ,"no" ,"result"};
        int[] ans = nearestRepeated(input);
        System.out.println(ans[0]);
        System.out.println(ans[1]);

    }
}
