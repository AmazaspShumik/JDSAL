package interview_problems;

/**
 * Created by amazaspshaumyan on 11/30/16.
 */

import java.util.Comparator;

/**
 * Created by amazaspshaumyan on 11/25/16.
 */
public class DefaultComparator<T> implements Comparator<T> {

    public int compare(T a, T b) throws ClassCastException{

        return ((Comparable<T>) a).compareTo(b);
    }
}
