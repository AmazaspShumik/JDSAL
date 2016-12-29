package codefights;

/**
 * Created by amazaspshaumyan on 12/23/16.
 */
public class MaxDiff {

    int largestDistance(int[] A) {

        int[] mx = new int[] {A[0], A[1]};
        int[] mn = new int[] {A[0], A[1]};
        for (int i = 0; i < A.length; i++) {
            int k = i % 2;
            if (A[i] > mx[k]) {
                mx[k] = A[i];
            } else if (A[i] < mx[k]) {
                mn[k] = A[i];
            }
        }
        return Math.max(mx[0] - mn[0], mx[1] - mn[1]);
    }
}
