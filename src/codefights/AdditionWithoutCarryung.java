package codefights;

/**
 * Created by amazaspshaumyan on 12/12/16.
 */


import java.util.Stack;


public class AdditionWithoutCarryung {

    static int additionWithoutCarrying(int param1, int param2) {

        int paramCurrent1 = param1, paramCurrent2 = param2;
        Stack<Integer> st = new Stack<>();
        StringBuilder str = new StringBuilder();


        while(paramCurrent1 > 0 && paramCurrent2 > 0){
            st.push( (paramCurrent1 % 10 + paramCurrent2 % 10) % 10);
            paramCurrent1/=10;
            paramCurrent2/=10;
        }

        while(paramCurrent1 > 0) {
            st.push(paramCurrent1 % 10);
            paramCurrent1/=10;
        }

        while(paramCurrent2 > 0) {
            st.push(paramCurrent2 % 10);
            paramCurrent2/=10;
        }

        while(!st.isEmpty()) str.append(st.pop());
        return Integer.parseInt(str.toString());
    }


    static int additionWithoutCarryingVersionTwo(int param1, int param2){
        int answer = 0;
        int d;
        for( d = 1; param1>0 && param2>0; param1/=10, param2/=10, d*=10){
            answer += (param1+param2)%10 * d;
        }

        answer += (param1+param2)*d;
        return answer;
    }


    public static void main(String[] args){
        int x = additionWithoutCarrying(1734,456);
        int j = additionWithoutCarryingVersionTwo(1734,456);
        System.out.println(x);
        System.out.println(j);
    }
}
