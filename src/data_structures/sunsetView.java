package data_structures;

/**
 * Created by amazaspshaumyan on 11/26/16.
 */
public class sunsetView {


    public static void viewEvaluate(int[] arr){
        LinkedListStack<Integer> st = new LinkedListStack<>();
        st.push(0);
        for(int j=1; j < arr.length; j++){
            while(!st.isEmpty() && arr[st.peek()]<=arr[j]){
                int el = st.pop();
                System.out.println(arr[el]);
            }
            st.push(j);
        }
        for(Integer el: st){
            System.out.print(el+",");
        }
    }

    public static void main(String[] args){
        int[] x = {1,2,3,10,6,4,8,3,1};
        viewEvaluate(x);
    }
}
