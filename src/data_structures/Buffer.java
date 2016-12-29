package data_structures;

import java.util.NoSuchElementException;

/**
 * Created by amazaspshaumyan on 11/27/16.
 */
public class Buffer{

    private Stack<Character> left;
    private Stack<Character> right;

    public Buffer(){
        left  = new LinkedListStack<>();
        right = new LinkedListStack<>();
    }

    public int size(){ return left.size() + right.size();}

    public boolean isEmpty(){ return left.isEmpty() && right.isEmpty();}

    public void insert(char c){
        left.push(c);
    }

    public char get(){
        return left.peek();
    }

    public char delete(){
        if(left.isEmpty()) throw new NoSuchElementException();
        return left.pop();
    }

    public void left(int k){
        int leftPos = Math.min(k,left.size());
        for(int j=0; j<leftPos; j++) {
            right.push(left.pop());
        }
    }

    public void right(int k){
        int rightPos = Math.min(k,right.size());
        for(int j=0; j<rightPos; j++){
            left.push(right.pop());
        }
    }

    public static void main(String[] args){
        char[] h = {'s','c','h','e','k','a','s','t','i','k'};
        Buffer b = new Buffer();
        for(int m=0; m<5; m++){
            b.insert(h[m]);
        }


    }

}
