package interview_problems;

/**
 * Created by amazaspshaumyan on 12/11/16.
 */
import java.util.Comparator;


public class LastIndexComparator implements Comparator<Interval>{

    public int compare(Interval a, Interval b){
        return a.right - b.right;
    }
}


