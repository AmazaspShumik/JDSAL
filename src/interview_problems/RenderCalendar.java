package interview_problems;

import data_structures.Stack;
import data_structures.LinkedListStack;
import java.util.Comparator;
import java.util.Arrays;

/**
 * This solves problem 14.5 from Elements of Programming Interviews
 */
public class RenderCalendar {


    public static int maxConcurrent(Interval[] a){
        int N        = a.length;
        Interval[] b = Arrays.copyOf(a,N);

        Arrays.sort(a, new FirstIndexComparator());
        Arrays.sort(b, new LastIndexComparator());

        int i = 0, j = 0, current = 0;
        int max = 0;

        while(i < N && j < N){
            if(a[i].left <= b[j].right){
                current++;
                i++;
            } else{
                current--;
                j++;
            }
            max = Math.max(max,current);
        }

        // One of the arrays is finished
        while( i < N ) { current++; i++;}
        while( j < N ) { current--; j++;}

        // final part
        max = Math.max(max,current);
        return max;
    }


    // ================================  Unit Tests ===============================================


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

        int concurr = maxConcurrent(a);
        System.out.println(concurr);

    }
}
