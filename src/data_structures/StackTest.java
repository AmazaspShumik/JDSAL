package data_structures;

/**
 * Created by amazaspshaumyan on 11/24/16.
 */
public class StackTest {

    public static void main(String[] args){
        int [] x = new int[4];
        x[1] = 1;
        x[2] = 2;
        x[3] = 3;
        Stack<Integer> fs = new LinkedListStack<>();
        for(int j=0; j < 4; j++){
            fs.push(x[j]);
            System.out.println(fs.size());
        }

        System.out.println();
        System.out.println(fs.peek());
        System.out.println();

        for(int j=0; j < 4; j++){
            int current = fs.pop();
            System.out.println(current);
        }

    }
}
