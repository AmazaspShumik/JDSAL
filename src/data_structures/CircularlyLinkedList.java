package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularlyLinkedList<T> implements Iterable<T>{

    private int N;
    private Node tail;

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

    public int size(){
        return N;
    }

    public boolean isEmpty(){
        return N==0;
    }

    public T first(){
        if(isEmpty()) throw new NoSuchElementException();
        return tail.next.item;
    }

    public T last(){
        if(isEmpty()) throw new NoSuchElementException();
        return tail.item;
    }

    public void addFirst(T item){
        Node newest = new Node(item);
        if(isEmpty()){
            tail = newest;
            tail.next = tail;
        } else{
            newest.next = tail.next;
            tail.next   = newest;
        }
        N++;
    }

    public void addLast(T item){
        addFirst(item);
        rotate();
    }

    /*
    Moves first element of linked list to the end
     */
    public void rotate(){
        if(isEmpty()) throw new NoSuchElementException("No elements in linked list");
        tail = tail.next;
    }

    public T removeFirst(){
        if(isEmpty()) throw new NoSuchElementException();
        T element = tail.next.item;
        if(size()==1) {
            tail = null;
        } else {
            tail.next = tail.next.next;
        }
        N--;
        return element;
    }

    public Iterator<T> iterator(){
        return new CircularLinkedListIterator();
    }

    private class CircularLinkedListIterator implements Iterator<T>{

        private Node current = tail;
        private int nodeCount;

        public boolean hasNext(){ return nodeCount < N;}
        public T next(){
            if(!hasNext()) throw new NoSuchElementException();
            T element = current.next.item;
            nodeCount++;
            current   = current.next;
            return element;
        }
        public void remove(){throw new UnsupportedOperationException();}
    }


    public static void main(String[] args){
        int [] x = new int[4];
        x[1] = 1;
        x[2] = 2;
        x[3] = 3;
        SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
        for(int i=0; i < 4; i++){
            l.addLast(x[i]);
        }

        for(Integer it: l){
            System.out.println(it);
        }

        for(int i=0; i < 4; i++){
            Integer testint = l.removeFirst();
            System.out.println(testint);
        }
    }





}
