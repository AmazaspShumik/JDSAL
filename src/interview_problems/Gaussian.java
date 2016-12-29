package interview_problems;

/**
 * Created by amazaspshaumyan on 12/14/16.
 */
public class Gaussian {

    /*
    Computes pdf for standard gaussian distribution
     */
    public static double pdf( double x){
        return Math.exp(-x*x / 2) / Math.sqrt(2*Math.PI);
    }

    /*
    Computes pdf of normal with mean - mu, and standard deviation - sigma
     */
    public static double pdf(double x, double mu, double sigma){
        double z = (x - mu) / sigma;
        return pdf(z);
    }

    public static double cdf(double z){
        if(z < -8.) return 0;
        if(z > 8. ) return 1;
        double sum = 0.0, term = z;
        for(int i=3; sum!=sum+term; i+=2){
            sum  = sum + term;
            term = term*z*z / i;
        }
        return 0.5 + pdf(z)*sum;
    }

    public static double cdf(double z, double mu, double sigma){
        return cdf((z-mu)/sigma);
    }

    // computes x value, such that cdf(x) = p
    public static double inverseCDF(double p){
        double lo = -8.0, hi = 8.0, eps = 1e-5;
        double xMid = lo + (hi-lo)/2;
        System.out.println(cdf(xMid));
        double diff = cdf(xMid) - p;
        System.out.println(diff);
        while(Math.abs(diff) > eps){
            if      ( diff > 0) hi = xMid;
            else if ( diff < 0) lo = xMid;
            else     return xMid;
            xMid = lo + (hi - lo) / 2;
            diff = cdf(xMid) - p;
            System.out.println(xMid);
        }
        return xMid;
    }

    public static double inverseCDF(double p, double mu, double sigma){
        double lo   = -8.0, hi = 8.0, eps = 1e-10;
        double mid  = 0;
        double diff = p - cdf(mid,mu,sigma);
        while( Math.abs(diff) < eps){
            if(diff > 0)       lo = mid;
            else if(diff < 0)  hi = mid;
            else               return mid;
            mid  = lo + (hi-lo)/2;
            diff = p - cdf(mid,mu,sigma);
        }
        return mid;
    }

    public static void main(String[] args){
        System.out.println(cdf(1.96));
        System.out.println(inverseCDF(0.975));

    }
}
