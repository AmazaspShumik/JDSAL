package interview_problems;

/**
 * Simple Helper class that will be used in other applications
 */
public class Interval {


    int left;
    int right;

    public Interval(int left, int right){
        this.left  = left;
        this.right = right;
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("[");
        str.append( ((Integer)left).toString());
        str.append( ((Integer)right).toString());
        str.append("] ");
        return str.toString();
    }

}
