package data_structures;

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Created by amazaspshaumyan on 11/25/16.
 */
public class MaxQueue<T> implements Queue<T>{

    private MaxStack<T> stack1;
    private MaxStack<T> stack2;
    private Comparator<T> comp;

    public MaxQueue(Comparator<T> comp){
        stack1 = new MaxStack<>();
        stack2 = new MaxStack<>();
        this.comp = comp;
    }

    public MaxQueue(){
        this(new DefaultComparator<>());
    }

    public int size(){return stack1.size() + stack2.size();}

    public boolean isEmpty(){ return stack1.isEmpty() && stack2.isEmpty();}

    public void enqueue(T item){ stack1.push(item); }

    public T dequeue(){
        if(stack2.isEmpty()){
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    public T max(){
        if (stack1.isEmpty() && stack2.isEmpty()){
            throw new NoSuchElementException();
        } else if (!stack1.isEmpty() && stack2.isEmpty()){
            return stack1.max();
        } else if (stack1.isEmpty() && !stack2.isEmpty()){
            return stack2.max();
        } else{
            T one = stack1.max();
            T two = stack2.max();
            if(comp.compare(one,two)<=0) return two;
            return one;
        }
    }

    public static void main(String[] args) {
        MaxQueue<Integer> ms = new MaxQueue<>();
        int[] arr = {9, 8, 1, 6, -1, -2, 3, 5, 5, 1, 1, 4};
        for (int j = 0; j < arr.length; j++) {
            ms.enqueue(arr[j]);
        }

        for (int j = 0; j < arr.length; j++) {
            System.out.println(ms.max());
            int x = ms.dequeue();
        }
    }
}
