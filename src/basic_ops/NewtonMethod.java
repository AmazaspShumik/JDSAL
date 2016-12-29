package basic_ops;

/**
 * Created by amazaspshaumyan on 12/13/16.
 */
public class NewtonMethod {

    /*
    Computes square root for
     */
    public static double sqrt(double val, double eps){
        double x   = val, diff = val*val - val;
        while(diff>eps){
            x    -= (x/2 - val / (2*x));
            diff  = x*x - val;
        }
        return x;
    }


    public static void main(String[] args){
        System.out.println(sqrt(2, 1e-3));
    }
}
