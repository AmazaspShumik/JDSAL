package data_structures;

/**
 * Created by amazaspshaumyan on 11/24/16.
 *
 * Checks , whether parentheses are balanced or not
 */
public class Parentheses {

    public static boolean checkBalanced(String str){
        char[] data = str.toCharArray();
        char element,last;
        Stack<Character> ch = new ResizingArrayStack<>();
        for(int j=0; j<data.length; j++){
            System.out.println(ch.size());
            element = data[j];
            if(ch.isEmpty()){
                ch.push(element);
            } else{
                last = ch.peek();
                if(element==')' && last!='(' ) return false;
                if(element=='}' && last!='{' ) return false;
                if(element==']' && last!='[' ) return false;

                if (last=='{' && element=='}') {
                    ch.pop();
                } else if (last=='[' && element==']'){
                    ch.pop();
                } else if (last=='(' && element==')'){
                    ch.pop();
                } else{
                    ch.push(element);
                }
            }
        }
        return ch.isEmpty();
    }

    public static void main(String[] args){
        String str1 = "((({{}}]))";
        String str2 = "((()))[]{{}}";
        boolean balancedOne = checkBalanced(str1);
        boolean balancedTwo = checkBalanced(str2);
        System.out.println(balancedOne);
        System.out.println(balancedTwo);
    }
}
