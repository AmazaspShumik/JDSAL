package data_structures;

import java.util.Iterator;

/**
 * Created by amazaspshaumyan on 11/25/16.
 */
public class TwoStackQueue<T> implements Queue<T>{

    private Stack<T> stackOne;
    private Stack<T> stackTwo;

    public TwoStackQueue(){
        stackOne = new LinkedListStack<>();
        stackTwo = new LinkedListStack<>();
    }

    public int size(){return stackOne.size() + stackTwo.size();}

    public boolean isEmpty(){return stackOne.isEmpty() && stackTwo.isEmpty();}

    public void enqueue(T item){
        stackOne.push(item);
    }

    public T dequeue(){
        if(stackTwo.isEmpty()){
            while(!stackOne.isEmpty()){
                stackTwo.push(stackOne.pop());
            }
        }
        return stackTwo.pop();
    }



    public static void main(String[] args){
        Queue<Integer> qstack = new TwoStackQueue<>();
        int[] arr = {0,1,2,3};
        for(int j=0; j < arr.length; j++){
            qstack.enqueue(arr[j]);
        }

        for(int j=0; j < arr.length; j++){
            int x = qstack.dequeue();
            System.out.println(x);
        }

    }



}
