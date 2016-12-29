package data_structures;

import java.util.Comparator;
import java.util.Queue;
import java.util.ArrayDeque;


public class BinarySearch<Key,Val> {

    private Node[] nodes;
    private Comparator<Node> comp;
    private int N;

    private class Node{
        Key key;
        Val val;

        public Node(Key key, Val val){
            this.key = key; this.val = val;
        }
    }


    private class NodeComparator implements Comparator<Node>{

        private Comparator<Key> comp;

        public NodeComparator( Comparator<Key> comp){
            this.comp = comp;
        }

        public int compare(Node a, Node b){
            return comp.compare(a.key,b.key);
        }
    }

    public BinarySearch(Key[] keys, Val[] vals, Comparator<Key> comp){
        Node[] nodes = (Node[]) new Object[keys.length];
        for(int j=0;j<keys.length; j++) nodes[j] = new Node(keys[j],vals[j]);
        this.comp = new NodeComparator(comp);
        this.N    = keys.length;
    }

    public BinarySearch(Key[] keys, Val[] vals){
        this(keys, vals, new DefaultComparator<Key>());
    }

    public int size(){ return N;}
    public boolean isEmpty() { return N==0; }

    // get value of element corresponding to key
    public Val get(Key key){
        if(isEmpty()) return null;
        int i = rank(key);
        if(i<N && comp.compare(new Node(key,null),nodes[i])==0) return nodes[i].val;
        else                                                    return null;
    }

    public int rankRecursive(Key key){
        Node newest = new Node(key,null);
        return rankRecursive(newest,0,nodes.length-1);
    }

    private int rankRecursive(Node newest, int lo, int hi){
        if(hi < lo) return lo;
        int mid = lo + (hi-lo)/2;
        int cmp = comp.compare(newest,nodes[mid]);
        if( cmp > 0)        return rankRecursive(newest,mid+1,hi);
        else if( cmp < 0)   return rankRecursive(newest,lo, mid-1);
        else                return mid;
    }


    public int rank(Key key){
        Node newest = new Node(key,null);
        int lo = 0, hi = N-1;
        while(hi>=lo){
            int mid = lo + (hi-lo)/2;
            int cmp = comp.compare(newest,nodes[mid]);
            if( cmp < 0 )       lo = mid-1;
            else if( cmp > 0)   hi = mid+1;
            else                return mid;
        }
        return lo;
    }


    public Key floor(Key key){
        if(isEmpty()) return null;
        int i       = rank(key);
        if(i<N && comp.compare(new Node(key,null),nodes[i])==0) return nodes[i].key;
        if(i==0) return null; // no elements smaller than key
        else     return nodes[i-1].key;
    }

    public Key ceiling(Key key){
        if(isEmpty()) return null;
        int i       = rank(key);
        if(i<N && comp.compare(new Node(key,null),nodes[i])==0) return nodes[i].key;
        if(i==N) return null;
        else     return nodes[i+1].key;
    }


    public Iterable<Key> keys(){
        Queue<Key> q = new ArrayDeque<>();
        for(int j=0; j<N; j++) q.add(nodes[j].key);
        return q;
    }


    public Iterable<Key> range(Key first, Key last){
        int f = rank(first);
        int l = rank(last);
        Queue<Key> q = new ArrayDeque<>();
        for(int j = f; j<N && j<l; j++) q.add(nodes[j].key);
        if(comp.compare(nodes[l],new Node(last,null))==0) q.add(nodes[l].key);
        return q;
    }











}
