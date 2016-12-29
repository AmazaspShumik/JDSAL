package data_structures;

import interview_problems.*;
import interview_problems.DefaultComparator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Comparator;
/*

Includes solution for problem 8.1 (cyclicShift)
 */


public class SinglyLinkedList<T> implements Iterable<T>,Cloneable{

    private int N;
    private Node head;  // head is automatically set to null
    private Node tail;  // tail is automatically set to null

    private class Node{
        T item;
        Node next;

        public Node(T item, Node next){
            this.item = item;
            this.next = next;
        }

        public Node(T item){
            this(item,null);
        }

    }

    public int size(){ return N;}
    public boolean isEmpty() {return N==0;}

    public void addFirst(T item){
        Node newest = new Node(item,head);
        head        = newest;
        if(isEmpty()) tail = head;
        N++;
    }

    public void addLast(T item){
        Node newest = new Node(item);
        if(isEmpty()){
            head = newest;
        }else{
            tail.next = newest;
        }
        tail = newest;
        N++;
    }

    public T first(){
        if(isEmpty()) throw new NoSuchElementException();
        return head.item;
    }

    public T last(){
        if(isEmpty()) throw new NoSuchElementException();
        return tail.item;
    }

    public T removeFirst() throws NoSuchElementException{
        if(isEmpty()) throw new NoSuchElementException();
        T element = head.item;
        head      = head.next;
        N--;
        if(isEmpty()) tail = null;
        return element;
    }

    /*
    Insert Node b after Node a
     */
    private void insertAfter(Node a, Node b){
        b.next = a.next;
        a.next = b;
    }


    public void cyclicShift(int shift){
        int realShift = shift % N;
        tail.next     = head; // make list circular
        for(int j=0; j<realShift; j++ ){
            tail = tail.next;
        }
        head      = tail.next;
        tail.next = null;
    }

    public void removeDuplicates(){
        Node current = head;
        while(current!=null && current.next!=null){
            if(current.item.equals(current.next.item)){
                current.next = current.next.next;
                N--;
            }
            current      = current.next;
        }
    }

    public SinglyLinkedList<T> clone() throws CloneNotSupportedException {
        // Use inherited Object.clone() to create initial copy
        SinglyLinkedList<T> other = (SinglyLinkedList<T>) super.clone();
        if(N>0){
            other.head = new Node(head.item);
            Node walker = head.next;
            Node otherTail = other.head;
            while(walker!=null){
                otherTail.next = new Node(walker.item);
                walker = walker.next;
                otherTail = otherTail.next;
            }
            other.tail = otherTail;
        }
        return other;
    }


    public void reverseList(){
        if(isEmpty()) return;
        Node prev = null;
        Node curr = head;
        Node next = null;
        while(curr!=null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        tail = head;
        head = prev;
    }


    public boolean checkPalindromic(){
        int left = N/2 - 1;
        int right = (N+1)/2;

        Node leftTail = null;
        Node rightHead = null;
        Node curr = head;
        for(int count = 0; count <=right; count++){
            if(count==left) leftTail = curr;
            if(count==right) rightHead = curr;
            curr = curr.next;
        }


        System.out.println(rightHead.item.toString()+" <---> "+leftTail.item.toString());

        // split Linked List into two sublists
        leftTail.next = null;

        // reverse left sublist
        Node prev = null;
        curr = head; // reassign current
        Node next = null;

        while(curr!=null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // check whether reversed left sublist is the same as right sublist
        while(prev!=null ){
            if(!prev.item.equals(rightHead.item)) return false;
            prev = prev.next;
            rightHead = rightHead.next;
        }
        return true;
    }


    private boolean less(T a, T b, Comparator<T> comp){
        return comp.compare(a,b) <= 0;
    }


    private boolean less(T a, T b){
        return less(a,b,new DefaultComparator<T>());
    }

    // returns last Node after sinking
    private Node sink(Node a, Node start){

        if(start==null) {
            a.next = null;
            return a;
        }
        Node curr = start;

        while(curr!=null && !less(curr.item,a.item)){
            curr = curr.next;
        }
        if(start==curr) {
            a.next = start;
            return a;
        } else {
            insertAfter(curr, a);
            return start;
        }
    }

    public void insertionSort(){
        Node prev = null;
        Node curr = head;
        Node next = null;
        while(curr!=null){
            next      = curr.next;
            prev      = sink(curr,prev);
            curr      = next;
        }
        head = prev;
        for(curr = head; curr.next!=null; curr = curr.next)
        tail = curr;
        reverseList();
    }



    public Iterator<T> iterator(){
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T>{

        private Node current = head;

        public boolean hasNext(){return current!=null;}
        public T next(){
            if (!hasNext()) throw new NoSuchElementException();
            T element = current.item;
            current   = current.next;
            return element;
        }
        public void remove(){ throw new UnsupportedOperationException();}
    }


    public static void main(String[] args){
        int [] x = {0,1,2,7,3,1,0};
        SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
        for(int i=0; i < x.length; i++){
            l.addLast(x[i]);
        }


        System.out.println();
        l.insertionSort();
        for(Integer i: l){
            System.out.println(i);
        }


    }


}
