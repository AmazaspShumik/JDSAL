package interview_problems;

import java.util.Arrays;
import java.util.Queue;
import java.util.ArrayDeque;


/**
 * Created by amazaspshaumyan on 12/11/16.
 */


public class IntervalCovering {


    public static Queue<Integer> visitTimes(Interval[] a){

        Interval[] b = Arrays.copyOf(a,a.length);

        // sort by first index , then by last
        Arrays.sort(a,new FirstIndexComparator());
        Arrays.sort(b,new LastIndexComparator());

        int unChecked = 0,i = 0,j=0,lastCheck = 0;
        Queue<Integer> q = new ArrayDeque<>();

        while(i < a.length && j < b.length){
            if(a[i].left <= b[j].right){
                unChecked++;
                i++;
            } else{
                if( lastCheck < b[j].left ) {
                    unChecked = 0;
                    lastCheck = b[j].right;
                    q.add(lastCheck);
                }
                j++;
            }
        }

        while( i < a.length ){
            unChecked++;
            i++;
        }
        if( unChecked > 0){
            lastCheck = a[a.length-1].left;
            q.add(lastCheck);
        }

        return q;

    }


    public static void main(String[] args){
        Interval[] a = new Interval[9];
        a[0]         = new Interval(1,5);
        a[1]         = new Interval(2,7);
        a[2]         = new Interval(4,5);
        a[3]         = new Interval(6,10);
        a[4]         = new Interval(8,9);
        a[5]         = new Interval(9,17);
        a[6]         = new Interval(11,13);
        a[7]         = new Interval(12,15);
        a[8]         = new Interval(14,15);
        Queue<Integer> q = visitTimes(a);
        for(Integer i: q){
            System.out.println(i);
        }
    }


}
