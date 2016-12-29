package data_structures;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Merges sorted queues
 */
public class MergeSortedQueue {


    private static class Node<T>{

        public int streamID;
        public T item;

        public Node(int streamID, T item){
            this.streamID = streamID;
            this.item     = item;
        }
    }


    private static class NodeComparator<T> implements Comparator<Node<T>>{

        private Comparator<T> compar;

        public NodeComparator(Comparator<T> comp){
            compar = comp;
        }

        public int compare(Node<T> a, Node<T> b){
            return compar.compare(a.item,b.item);
        }
    }


    public static<T> void merge(ArrayList<Queue<T>> streams, Comparator<T> comp){

        int nStreams = streams.size();
        Comparator<Node<T>> compar = new NodeComparator<>(comp);
        MinPQ<Node<T>> mpq = new MinPQ<>(nStreams,compar);
        for(int j=0; j < nStreams; j++){
            mpq.insert(new Node(j,streams.get(j).dequeue()));
        }

        while(!mpq.isEmpty()){
            Node<T> node = mpq.deleteMin();
            int streamID = node.streamID;
            if(!streams.get(streamID).isEmpty()) mpq.insert(new Node( streamID, streams.get(streamID).dequeue() ));
            System.out.println(node.item.toString());
        }
    }


    public static<T> void merge(ArrayList<Queue<T>> streams){
        merge(streams,new DefaultComparator<T>());
    }


    public static void main(String[] args){
        Integer[] x = {1,4,10,17};
        Integer[] y = {2,3,6,7};
        Integer[] z = {5,8,9,11};
        ArrayList<Queue<Integer>> q = new ArrayList<>(3);
        q.add(0,new ResizingArrayQueue<>());
        q.add(1,new ResizingArrayQueue<>());
        q.add(2,new ResizingArrayQueue<>());


        for (int k= 0; k < x.length; k++){
            q.get(0).enqueue(x[k]);
            q.get(1).enqueue(y[k]);
            q.get(2).enqueue(z[k]);
        }

        merge(q);

    }
}
