package interview_problems;

import java.util.Comparator;


public class FirstIndexComparator implements Comparator<Interval>{

        @Override
        public int compare(Interval a, Interval b){
            return a.left - b.left;
        }
}

