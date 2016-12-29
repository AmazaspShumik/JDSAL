package data_structures;

import java.util.Comparator;
import java.util.Iterator;

/**
 * This is client code for priority queue. It reads (x,y,z) from input and then finds
 * top M points closest to origin
 *
 */
public class TopM {

    private int M; // number of top elements that it should hold
    private MaxPQ<Point> mpq; // MaxPQ to process inputs
    private Comparator<Point> comp;


    public TopM(int M){
        this.M = M;
        comp   = new PositionComparator();
        mpq    = new MaxPQ<>(M,comp);
    }


    private class Point{
        private double x,y,z;

        public Point(double x, double y, double z){
            this.x = x; this.y = y; this.z = z;
        }

        public double distOrigin(){
            // no need to compute square
            return Math.abs(x)+ Math.abs(y) + Math.abs(z);
        }

        public String toString(){
            StringBuilder str = new StringBuilder();
            str.append("( ");
            str.append(((Double) x).toString() + ", ");
            str.append(((Double) y).toString() + ", ");
            str.append(((Double) z).toString());
            str.append(" )");
            return str.toString();
        }
    }


    private class PositionComparator implements Comparator<Point>{

        public int compare(Point a, Point b){
            double diff = a.distOrigin() - b.distOrigin();
            if(diff>0) return 1;
            else if (diff<0) return -1;
            else return 0;
        }
    }


    public void processPoint(double x, double y, double z){
        Point newest = new Point(x,y,z);
        if(mpq.size() < M) mpq.insert(newest);
        else{
            if(comp.compare(mpq.max(),newest)>0) {
                mpq.deleteMax();
                mpq.insert(newest);
            }
        }
    }

    public String toString(){
        return mpq.toString();
    }

    // ================================== Tests ======================================

    public static void main(String[] args){

        double[] x = {0.1,0.2,0.3,1.,2.};
        double[] y = {0.2,0.1,0.3,2.,1.};
        double[] z = {-0.2,-0.1,0.3,-2.,1.};

        TopM top = new TopM(3);

        for(int j=0; j < x.length; j++){
            top.processPoint(x[j],y[j],z[j]);
        }

        System.out.println(top.toString());
    }


}
