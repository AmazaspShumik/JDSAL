package data_structures;

import java.util.Iterator;
/**
 * Created by amazaspshaumyan on 11/24/16.
 *
 *  Implements Stack using Linked List, shows adapter pattern
 */
public class LinkedListStack<T> implements Iterable<T>,Stack<T>{

    private SinglyLinkedList<T> stack;

    public LinkedListStack(){
        stack = new SinglyLinkedList<>();
    }

    public boolean isEmpty(){ return stack.isEmpty(); }
    public int size(){ return stack.size(); }
    public void push(T item){ stack.addFirst(item); }
    public T pop(){ return stack.removeFirst(); }
    public T peek(){ return stack.first(); }

    public Iterator<T> iterator(){
        return stack.iterator();
    }

}
