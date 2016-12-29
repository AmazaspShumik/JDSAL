package interview_problems;

import data_structures.LinkedListStack;
import data_structures.Stack;
import java.util.Arrays;
import java.util.Comparator;


/**
 * Solves problem 2.5.20 from Sedgewick
 */
public class IdleTime {


    private static class Job{
        int start;
        int finish;

        public Job(int start, int finish){
            this.start  = start;
            this.finish = finish;
        }
    }


    private static class FirstIndexComparator implements Comparator<Job>{

        public int compare( Job a, Job b){
            return a.start - b.start;
        }
    }


    public static int[] idleCompute(Job[] arr){
        Stack<Integer> st = new LinkedListStack<>();
        Arrays.sort(arr,new FirstIndexComparator());

        // put first element
        st.push(arr[0].start);
        st.push(arr[0].finish);

        for(int j=1; j<arr.length; j++){

            // check whether works overlap
            if(st.peek() < arr[j].start)  st.push(arr[j].start);
            else                          st.pop();

            // push finish time
            st.push(arr[j].finish);
        }

        int maxWork    = 0, maxIdle = 0;
        int prevStart  = st.peek();
        int start,finish;
        while(!st.isEmpty()){
            finish     = st.pop();
            start      = st.pop();
            maxWork    = Math.max(finish-start,maxWork);
            maxIdle    = Math.max(prevStart - finish,maxIdle);
            prevStart  = start;
        }
        int[] workIdleArray = {maxWork,maxIdle};
        return workIdleArray;
    }


    public static void main(String[] args){
        Integer[] a = {1,4,3,9,8,22,23,26,58,78};
        Integer[] x = {1,4,58,78,3,9,23,26,8,22};
        Job[]   arr = new Job[5];
        int k = 0;
        for(int j = 0; j < a.length; j+=2){
            arr[k++] = new Job(x[j],x[j+1]);
        }
        System.out.println(Arrays.toString(idleCompute(arr)));
    }
}
