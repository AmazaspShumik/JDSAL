package data_structures;

import java.util.Iterator;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Created by amazaspshaumyan on 11/25/16.
 */
public class MaxStack<T> implements Stack<T>,Iterable<T>{

    private LinkedListStack<T> stack;
    private LinkedListStack<T> stackMax;
    private Comparator<T> comp;


    public MaxStack(Comparator<T> comp){
        stack = new LinkedListStack<>();
        stackMax = new LinkedListStack<>();
        this.comp = comp;
    }

    public MaxStack(){
        this(new DefaultComparator<>());
    }

    public int size() { return stack.size();}
    public boolean isEmpty(){ return stack.isEmpty();}
    public T peek() {return stack.peek();}

    public void push(T item){
        if(stack.isEmpty()){
            stackMax.push(item);
        } else{
            if( comp.compare(max(),item) <= 0){
                stackMax.push(item);
            }
        }
        stack.push(item);
    }

    public T pop(){
        if(stack.isEmpty()) throw new NoSuchElementException();
        T element = stack.pop();
        if(comp.compare(max(),element)==0){
            stackMax.pop();
        }
        return element;
    }

    public T max(){
        return stackMax.peek();
    }

    public Iterator<T> iterator(){
        return stack.iterator();
    }

    public static void main(String[] args) {
        MaxStack<Integer> ms = new MaxStack<>();
        int[] arr = {1, 1, 1, 2, -1, -2, 3, 6, 1, 1, 1, 6};
        for (int j = 0; j < arr.length; j++) {
            ms.push(arr[j]);
        }

        for (int j = 0; j < arr.length; j++) {
            System.out.println(ms.max());
            int x = ms.pop();
        }
    }

}
