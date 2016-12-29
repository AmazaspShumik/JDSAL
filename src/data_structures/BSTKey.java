package data_structures;


import java.util.Comparator;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;


public class BSTKey<T> implements Iterable<T>{

    private Comparator<T> comp;
    private Node root;

    private class Node{
        T key;
        Node left;
        Node right;

        public Node(T key, Node left, Node right){
            this.key   = key;
            this.left  = left;
            this.right = right;
        }

        public Node(T key){
            this(key,null,null);
        }
    }

    public BSTKey(Comparator<T> comp){
        this.comp = comp;
    }

    public BSTKey(){
        this(new DefaultComparator<>());
    }

    // ============================== Construct BST from sorted array =============================================

    /*
    Construct BST from sorted array
     */
    public BSTKey(T[] arr, Comparator<T> comp){
        Arrays.sort(arr,comp);
        root = recursiveConstruct(arr,0,arr.length-1);
        this.comp = comp;
    }

    public BSTKey(T[] arr){
        this(arr,new DefaultComparator<>());
    }

    private Node recursiveConstruct(T[] arr, int lo, int hi){
        if( hi <  lo ) return null;
        if( hi == lo ) return new Node(arr[lo]);
        int mid = lo + (hi-lo)/2;
        Node newest = new Node(arr[mid]);
        newest.left  = recursiveConstruct(arr,lo,mid-1);
        newest.right = recursiveConstruct(arr,mid+1,hi);
        return newest;
    }

    // ======================================== Order operations =======================================================


    //--------------------------------------------  Min & Max ----------------------------------------------------------
    public T min(){
        Node x = root;
        if( x==null ) return null;
        Node parent = null;
        while(x!=null){
            parent = x;
            x = x.left;
        }
        return parent.key;
    }

    public T minRecursive(){
        if(root==null) return null;
        return minRecursive(root).key;
    }

    private Node minRecursive( Node x){
        if(x.left==null) return x;
        return minRecursive(x.left);
    }


    //-------------------------------------------- Floor & Ceiling -----------------------------------------------------

    /*
    Finds largest element that is smaller or equal to key
     */
    public T floor(T key){
        return floor(root,key).key;
    }

    private Node floor(Node x, T key){
        if(x==null) return null;
        int cmp = comp.compare(key,x.key);
        Node newest = null;
        if(cmp > 0)        {
            newest = floor(x.right,key);
            if(newest!=null) return newest;
            else             return x;
        }
        else if(cmp < 0)   return floor(x.left,key);
        else               return x;
    }

    //------------------------------------------ K largest -------------------------------------------------------------

    public Iterable<T> kLargest(int k){
        Queue<T> q = new ArrayDeque<>();
        kLargestRecursive(q,root,k);
        return q;
    }

    private void kLargestRecursive(Queue<T> q, Node x, int k){
        if(x==null || q.size() >=k ) return;
        kLargestRecursive(q,x.right,k);
        if(q.size()<k) q.add(x.key);
        kLargestRecursive(q,x.left,k);
    }

    public Iterable<T> kLargestIterative(int k){
        Queue<T> q  = new ArrayDeque<>();
        if(root==null) return q;

        Node x         = root;
        Stack<Node> st = new Stack<>();
        while(x!=null) {st.push(x);x = x.right;}

        while(!st.isEmpty()){
            Node cur = st.pop();
            q.add(cur.key);
            if(q.size()==k) break;
            if(cur.left!=null){
                cur = cur.left;
                while(cur!=null){ st.push(cur); cur = cur.right;}
            }
        }
        return q;
    }


    public Iterable<T> kSmallestIterative(int k){
        Queue<T> q = new ArrayDeque<>();
        if(root == null) return q;

        Node x = root;
        Stack<Node> st = new Stack<>();
        while(x!=null){ st.push(x); x = x.left;}

        while(!st.isEmpty()) {
            Node cur = st.pop();
            q.add(cur.key);
            if(q.size()==k) break;
            if (cur.right != null) {
                cur = cur.right;
                while (cur != null) {
                    st.push(cur);
                    cur = cur.left;
                }
            }
        }
        return q;
    }


    // ======================================== BST Certification  =====================================================

    public boolean isBST(){
        return isBSTRecursive(root,null,null);
    }

    private boolean isBSTRecursive(Node x, T min, T max){
        if(x==null) return true;
        if(min!=null && comp.compare(x.key,min)<0) return false;
        if(max!=null && comp.compare(x.key,max)>0) return false;
        return isBSTRecursive(x.left,min,x.key) && isBSTRecursive(x.right,x.key,max);
    }


    /*
    Iterative solution to certifying Binary Search Tree
     */
    public boolean isBSTIterative(){
        Node x = root;
        if(x==null) return true;


        Node nill = new Node(null);
        Stack<Node> st = new Stack<>();
        Stack<Node> minSt = new Stack<Node>();
        Stack<Node> maxSt = new Stack<Node>();

        st.push(x); minSt.push(nill); maxSt.push(nill);
        while(!st.isEmpty()){
            x = st.pop();
            Node min = minSt.pop();
            Node max = maxSt.pop();
            if( min.key!=null && comp.compare(x.key, min.key) < 0) return false;
            if( max.key!=null && comp.compare(x.key, max.key) > 0) return false;
            if(x.left!=null)   { st.push(x.left); minSt.push(min); maxSt.push(x);}
            if(x.right!=null)  { st.push(x.right); minSt.push(x); maxSt.push(max);}
        }
        return true;
    }


    // ======================================== BSTKey Iterator ========================================================

    public Iterator<T> iterator(){
        Queue<T> q = new ArrayDeque<>();
        constructRecursiveSortedIterator(q,root);
        return q.iterator();
    }


    public Queue<T> constructIterativeSortedIterator(){
        Stack<Node> st = new Stack<>();
        Queue<T>     q = new ArrayDeque<>();
        Node curr = root;
        while(curr!=null) {
            st.push(curr); curr = curr.left;
        }

        while(!st.isEmpty()){
            curr = st.pop();
            q.add(curr.key);
            if(curr.right!=null){
                curr = curr.right;
                if(curr.left==null) st.push(curr);
                else{
                    while(curr!=null){
                        st.push(curr);
                        curr = curr.left;
                    }
                }
            }
        }
        return q;
    }


    public Queue<T> constructSortedIterator(){
        Node curr  = root;
        Queue<T> q = new ArrayDeque<>();
        Stack<Node> s = new Stack<>();

        while(!s.isEmpty() || curr!=null){
            if(curr!=null){
                s.push(curr);
                curr = curr.left;
            }else{
                curr = s.pop();
                q.add(curr.key);
                curr = curr.right;
            }
        }
        return q;
    }

    public void constructRecursiveSortedIterator(Queue<T> q, Node x){
        if(x==null) return;
        if(x.left!=null) constructRecursiveSortedIterator(q,x.left);
        q.add(x.key);
        if(x.right!=null) constructRecursiveSortedIterator(q,x.right);
    }


    public void printSorted(){
        if(root==null) return;
        printSorted(root);
    }

    private void printSorted(Node x){
        if(x.left!=null) printSorted(x.left);
        System.out.print(x.key.toString()+", ");
        if(x.right!=null) printSorted(x.right);
    }


    // ================================ Print BST Level by level =======================================================


    public Iterable<T> levelOrderKeys(){
        Queue<Node> surfer = new ArrayDeque<>();
        Queue<T> ans       = new ArrayDeque<>();
        if(root==null) return ans;
        surfer.add(root);

        while(!surfer.isEmpty()){
            Node current = surfer.remove();
            if(current.left !=null) surfer.add(current.left);
            if(current.right!=null) surfer.add(current.right);
            ans.add(current.key);
        }
        return ans;
    }


    public void printLevelByLevel(){
        Queue<Node> surfer    = new ArrayDeque<>();
        Queue<Integer> levels = new ArrayDeque<>();

        if(root==null) return;
        surfer.add(root); levels.add(1);
        int currLevel = 1;
        System.out.print("\n Level 1"+" : ");
        while(!surfer.isEmpty()){
            Node current   = surfer.poll();
            int level      = levels.poll();
            if(current.left!=null)  { surfer.add(current.left);  levels.add(level+1);}
            if(current.right!=null) { surfer.add(current.right); levels.add(level+1);}
            if(level!=currLevel) {
                System.out.print("\n Level "+Integer.toString(level)+" : ");
                currLevel = level;
            }
            System.out.print(current.key.toString()+", ");
        }
    }


    // ====================================== Unit Tests ===============================================================



    public static void main(String[] args){
        Integer[] x = {2,1,3,4,5,6,7,9};

        // ------------------- Test construction from array & printing by level --------------
        BSTKey<Integer> bst = new BSTKey<>(x);
        bst.printLevelByLevel();

        // -----------------------  Print in sorted sequence ---------------------------------

        /*
        System.out.println("\nPrint in sorted order");
        for(Integer it: bst){
            System.out.print(it.toString()+", ");
        }
        */

        System.out.println("\nPrint iteratively");

        Queue<Integer> q = bst.constructIterativeSortedIterator();
        System.out.println(q.size());
        for(Integer ite: q){
            System.out.print(ite.toString()+", ");
        }


        // ------------------------- Order based operations ---------------------------------

        System.out.println();
        System.out.println("\nOrder based operations");

        System.out.println("Min : "+bst.min().toString());


        System.out.println();
        System.out.println("\nFloor operation");

        System.out.println(bst.floor(9));


        // ------------------------ Binary Search Tree Certification --------------------------

        System.out.println("\nIs tree a BST?");

        //System.out.println(bst.isBST());
        System.out.println(bst.isBSTIterative());

        // ----------------------- Testing k largest elements ----------------------------------

        System.out.println("Top k largest elements\n");

        for(Integer it: bst.kLargestIterative(4)){
            System.out.print(it.toString()+", ");
        }

        System.out.println();

        for(Integer it: bst.kSmallestIterative(4)){
            System.out.print(it.toString()+", ");
        }


    }



}
