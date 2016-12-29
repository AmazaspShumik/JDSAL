package data_structures;

/**
 * Created by amazaspshaumyan on 11/25/16.
 */
public class Josephus {

    public static void josephusQueue(int n, int k){

        CircularLinkedQueue<Integer> clq = new CircularLinkedQueue<>();
        int out;

        // populate queue
        for(int j=0; j < n; j++){
            clq.enqueue(j);
        }

        while(clq.size()>1){
            for(int l=0; l<k-1; l++){
                clq.rotate();
            }
            out = clq.dequeue();
            System.out.print(out+",");
        }
        out = clq.dequeue();
        System.out.print(out);
    }


    public static void main(String[] args){
        Josephus.josephusQueue(7,2);
    }

}
