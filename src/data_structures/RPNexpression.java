package data_structures;

/**
 * Created by amazaspshaumyan on 11/24/16.
 */
public class RPNexpression {

    public static int evaluate(String str){
        String[] st = str.split(",");
        Stack<Integer> ch = new LinkedListStack<>();
        int x,y;
        String element;
        for(int k=0; k<st.length; k++ ){
            element = st[k];
            if (!element.equals("*") && !element.equals("-") && !element.equals("+") && !element.equals("/")){
                ch.push(Integer.parseInt(element));
            } else{
                y = ch.pop();
                x = ch.pop();
                if (element.equals("*")) ch.push(x*y);
                if (element.equals("+")) ch.push(x+y);
                if (element.equals("-")) ch.push(x-y);
                if (element.equals("/")) ch.push(x/y);
            }
            System.out.println(ch.peek());

        }
        if(ch.size()!=1) throw new IllegalArgumentException("Input string not in RPN format");
        return ch.pop();
    }


    public static void main(String[] args){
        // test 1
        String str = "1,2,+,3,40,*,-,10,10,*,+";
        int ev = evaluate(str);
        System.out.println(ev);

        // test 2
        String str1 = "3,4,5,+,*";
        int ev1 = evaluate(str1);
        System.out.println(ev1);
    }
}
