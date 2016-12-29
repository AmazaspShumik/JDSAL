package data_structures;

import java.util.Comparator;

/**
 * Created by amazaspshaumyan on 11/27/16.
 */
public class SortStack {

    public static <T> Stack<T> sort(Stack<T> s, Comparator<T> comp){
        if(s.size()==1) return s;
        T elOne = s.pop();
        s = sort(s,comp);
        Stack<T> acc = new LinkedListStack<>();
        while(!s.isEmpty() && comp.compare(elOne,s.peek())<=0) {
            acc.push(s.pop());
        }
        s.push(elOne);
        while(!acc.isEmpty()) s.push(acc.pop());
        return s;
    }

    public static <T> void sortRecursive(Stack<T> s, Comparator<T> comp){
        if(s.size()==1) return;
        T elOne = s.pop();
        sortRecursive(s,comp);
        insertElement(elOne,s,comp);
    }

    public static <T> void insertElement(T element, Stack<T> s, Comparator<T> comp){
        if(s.isEmpty()){
            s.push(element);
        }else{
            if(comp.compare(element,s.peek())<=0){
                T el = s.pop();
                insertElement(element,s,comp);
                s.push(el);
            } else{
                s.push(element);
            }
        }
    }


    public static void main(String[] args){
        int[] x = {1,3,7,4,2,0,6,5};
        Stack<Integer> s = new LinkedListStack<>();
        for(int j=0; j<x.length; j++){
            s.push(x[j]);
        }

        SortStack.sortRecursive(s,new DefaultComparator<Integer>());

        while(!s.isEmpty()){
            Integer a = s.pop();
            System.out.println(a);

        }
    }
}
