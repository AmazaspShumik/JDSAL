package interview_problems;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Map;
import java.util.Comparator;
import java.util.Queue;
import java.util.ArrayDeque;

/**
 * Most Frequent queries, solves problem 13.6 in EPI
 */
public class MostFrequentQueries {

    public static Iterable<String> mostFrequent(String[] arr, int k){

        Map<String,Integer> fq = new HashMap<>();

        for(int j = 0; j < arr.length; j++){
            if(fq.containsKey(arr[j])) fq.put(arr[j],fq.get(arr[j])+1);
            else                       fq.put(arr[j],1);
        }

        class Node{
            Integer counts;
            String  key;

            public Node(String key, Integer counts){
                this.key    = key;
                this.counts = counts;
            }
        }

        class NodeComparator implements Comparator<Node>{

            public int compare(Node a, Node b){
                return a.key.compareTo(b.key);
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(k,new NodeComparator());
        for(String s:fq.keySet()){
            Node newest = new Node(s,fq.get(s));
            if(pq.size()<k) pq.add(newest);
            else{
                if(pq.peek().counts.compareTo(newest.counts)<0) {pq.poll(); pq.add(newest);}
            }
        }

        Queue<String> qs = new ArrayDeque<>();
        for(Node current : pq) qs.add(current.key);

        return qs;
    }


    public static void main(String[] args){
        String[]  s = {"Amazasp","ANA","ANA","Anastasia","Anastasia","Help","curr","curr","aaaa","aa","aa","aa","aa","ANA"};
        Iterable<String> it = mostFrequent(s,3);
        for(String str: it){
            System.out.println(str);
        }
    }



}
