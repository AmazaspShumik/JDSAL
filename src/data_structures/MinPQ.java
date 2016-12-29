package data_structures;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class MinPQ<T> implements Iterable<T>{

    private T[] pq;
    private int N;
    Comparator<T> comp;
    private T maxItem;


    public MinPQ(int cap, Comparator<T> comp){
        pq        = (T[]) new Object[cap];
        this.comp = comp;
    }

    public MinPQ(int cap){
        this(cap,new DefaultComparator<>());
    }

    public MinPQ(T[] arr){
        this(arr,new DefaultComparator<>());
    }


    public MinPQ(T[] arr, Comparator<T> comp){
        this.comp = comp;
        heapify(arr);
        pq        = arr;
        N         = arr.length;
    }


    private void heapify(T[] arr){
        int start = arr.length/2 -1;
        for(int j=start; j>=0 ;j--){
            sink(arr,j,arr.length);
        }

        maxItem = arr[0];
        for(int j=0; j<arr.length; j++){
            if(less(maxItem,arr[j])) maxItem = arr[j];
        }
    }

    private boolean less(int i, int j){
        return less(pq[i], pq[j]);
    }

    private boolean less(T a, T b){
        return comp.compare(a,b) < 0;
    }

    private void exch(int i, int j){
        exch(pq,i,j);
    }

    private void exch(Object[] a, int i, int j){
        Object key  = a[i];
        a[i]   = a[j];
        a[j]   = key;
    }

    private void swim(int k){
        while( k > 0 && less(k,(k-1)/2)){
            exch(k,(k-1)/2);
            k = (k-1)/2;
        }
    }

    private void sink(int k){
        sink(pq,k,N);
    }

    private void sink(T[] a, int k, int N){
        int j;
        while(2*k + 1 < N){
            j = 2*k + 1;
            if(j < N-1 && less(a[j+1],a[j])) j++;
            if(!less(a[j],a[k])) break;
            exch(a,j,k);
            k = j;
        }
    }


    private void resize(int cap){
        T[] temp = (T[]) new Object[cap];
        for(int j=0; j<pq.length; j++){
            temp[j] = pq[j];
        }
        pq = temp;
    }

    // =================    Public Methods   ===========================

    public int size() { return N; }

    public boolean isEmpty() { return N==0;}

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[");
        for(int j=0; j < N-1; j++){
            str.append(pq[j].toString());
            str.append(", ");
        }
        str.append(pq[N-1].toString());
        str.append(" ]");
        return str.toString();
    }

    public void insert(T key){
        if(pq.length==N) resize(2*pq.length);
        pq[N++] = key;
        swim(N-1);
        if(maxItem==null) {
            maxItem = key;
        } else {
            if (less(maxItem, key)) maxItem = key;
        }
    }


    public T deleteMin(){
        if(N==0) throw new NoSuchElementException();
        T min = pq[0];
        exch(0,N-1);
        pq[--N] = null;
        sink(0);
        if(pq.length < N/4) resize(pq.length/2);
        if(N==0) maxItem = null;
        return min;
    }


    public T min(){
        if(N==0) throw new NoSuchElementException();
        return pq[0];
    }


    public T max(){
        if(isEmpty()) throw new NoSuchElementException();
        return maxItem;
    }


    /*
    Tests whether item is smaller than k-th smallest
    */
    public boolean smallerK(T item , int k){
        // check is heap is empty
        if(isEmpty()) throw new NoSuchElementException("Heap is empty, no element to compare");
        // check whether number of elements in in heap is enough
        if(size() < k) throw new IndexOutOfBoundsException("Priority Queue is smaller than k = "+((Integer)k).toString());

        // if item is smaller than head => item is smaller than all elements of collection
        if(comp.compare(item, pq[0]) < 0) return true;

        // in case all previous checks are passed traverse heap
        Queue<Integer> q = new ResizingArrayQueue<>();
        int count, node, left, right;
        q.enqueue(0);
        count = 1 ;
        while(!q.isEmpty()){
            node = q.dequeue();
            left = 2*node + 1;
            right = left+1;
            System.out.println("ITEM = "+item.toString()+", LEFT = "+pq[left].toString());
            if(comp.compare(item, pq[left])>=0){
                if(++count > k) return false;
                q.enqueue(left);
            }
            if(comp.compare(item, pq[right])>=0){
                if(++count > k) return false;
                q.enqueue(right);
            }
        }
        return true;
    }

    /*
    Computes k-th smallest element in heap. Time complexity O( k*log(k) )
     */
    public T smallestK(int k){
        if(k>N) throw new NoSuchElementException();
        NodeComparator nc  = new NodeComparator(comp);
        MinPQ<Node> mp = new MinPQ<>(k,nc);
        int counter = 1, leftNode , rightNode;
        Node newest;
        mp.insert( new Node(0,pq[0]) );

        while( counter<k ){
            newest    = mp.deleteMin();
            leftNode  = 2*newest.index + 1;
            rightNode = leftNode + 1;
            if(leftNode  < N) mp.insert(new Node(leftNode,pq[leftNode]));
            if(rightNode < N) mp.insert(new Node(rightNode,pq[rightNode]));
            counter++;
        }
        return mp.min().item;
    }


    private class Node{
        int index;
        T   item;
        public Node(int index, T item){
            this.index = index;
            this.item  = item;
        }
    }

    private class NodeComparator implements Comparator<Node>{

        private Comparator<T> comp;

        public NodeComparator(Comparator<T> comp){
            this.comp = comp;
        }

        public int compare(Node a, Node b){
            return comp.compare(a.item, b.item);
        }

    }


    // =======================  iterator ==============================


    public Iterator<T> iterator(){
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<T>{

        int counter;

        public boolean hasNext(){ return counter < N;}
        public T next() {return pq[counter++];}
        public void remove(){ throw new UnsupportedOperationException();}
    }


    // ========================  TESTS ==================================


    public static void main(String[] args){
        Integer[] x = {2,6,1,7,0,3};
        MinPQ<Integer> mpq = new MinPQ<>(x);
        mpq.insert(10);
        mpq.insert(1);
        mpq.insert(-1);

        System.out.println(mpq.toString());

        System.out.println();

        //mpq.heapSort(x);

        //System.out.println(Arrays.toString(x));

        int R = mpq.size();
        for(int k=0; k < R; k++){
            System.out.println("Max = " + mpq.max().toString());
            Integer el = mpq.deleteMin();
            System.out.println(el);

        }


        Integer[] y = {-1,2,3,4,5,6,7,8,8,8,9,10,100,72,45,34};
        MinPQ<Integer> mp = new MinPQ<>(y);
        Integer z   = 7;
        System.out.println(mp.smallerK(z,10));

        System.out.println(mp.smallestK(10));


    }

}
