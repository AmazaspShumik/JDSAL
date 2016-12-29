package data_structures;

/**
 * Created by amazaspshaumyan on 11/28/16.
 */
public class LongesSubstringMatchingParens {

    public static int longestSubstring(String str){
        char[] ch = str.toCharArray();
        int left  = 0;
        int right = 0;
        int max   = 0;
        Stack<Character> s = new LinkedListStack<>();
        Stack<Integer> l = new LinkedListStack<>();
        for(int curr=0; curr<ch.length; curr++){
            if(ch[curr]=='('){
                s.push('(');
                if(left!=right){
                    if(!l.isEmpty() && left==l.peek()) {
                        l.pop();
                    } else {
                        l.push(left);
                    }
                    l.push(right);
                }
                left = curr;
            } else{
                if(!s.isEmpty()){
                    left--;
                }
            }
            right = curr;
        }
        while(!l.isEmpty()){
            right = l.pop();
            left = l.pop();
            if(right-left>max) max = right-left;
        }
        return max;

    }

    public static void main(String[] args){
        String str = "((())()(()(";
        int st = longestSubstring(str);
        System.out.println(st);

    }
}
