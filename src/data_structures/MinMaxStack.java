package data_structures;

import interview_problems.*;
import interview_problems.DefaultComparator;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Iterator;


public class MinMaxStack<T> implements Stack<T>, Iterable<T>{

    private LinkedListStack<T> st    = new LinkedListStack<>();
    private Stack<T> minSt = new LinkedListStack<>();
    private Stack<T> maxSt = new LinkedListStack<>();
    private Comparator<T> comp;

    public MinMaxStack(Comparator<T> comp){
        this.comp = comp;
    }

    public MinMaxStack(){
        this(new DefaultComparator<>());
    }

    public void push(T item){
        if(st.isEmpty()) {
            minSt.push(item);
            maxSt.push(item);
        }
        st.push(item);

        if( comp.compare(minSt.peek(),item) >= 0 ) minSt.push(item);
        if( comp.compare(maxSt.peek(),item) <= 0 ) maxSt.push(item);
    }

    public T pop(){
        if(st.isEmpty()) throw new NoSuchElementException();

        T element = st.pop();
        if( comp.compare( element, minSt.peek()) == 0) minSt.pop();
        if( comp.compare( element, maxSt.peek()) == 0) maxSt.pop();
        return element;
    }

    public T peek(){
        return st.peek();
    }

    public T max(){
        return maxSt.peek();
    }

    public T min(){
        return minSt.peek();
    }

    public int size(){ return st.size();}
    public boolean isEmpty() {return st.isEmpty();}

    public Iterator<T> iterator(){
        return st.iterator();
    }

    public static void main(String[] args){
        Integer[] x = {2,1,0,-1,4,5,6,7,8};
        MinMaxStack<Integer> mms = new MinMaxStack<>();

        for(int j=0; j < x.length; j++){
            mms.push(x[j]);
            System.out.println("Elemen = " + x[j].toString() + ", MAX = "+mms.max().toString() + ", MIN = "+mms.min().toString());
        }
    }
}
