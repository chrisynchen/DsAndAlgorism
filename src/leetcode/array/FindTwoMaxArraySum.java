package leetcode.array;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * 1031. Maximum Sum of Two Non-Overlapping Subarrays
 *
 * Given an array A of non-negative integers, return the maximum sum of elements in two non-overlapping (contiguous) subarrays, which have lengths L and M.  (For clarification, the L-length subarray could occur before or after the M-length subarray.)
 *
 * Formally, return the largest V for which V = (A[i] + A[i+1] + ... + A[i+L-1]) + (A[j] + A[j+1] + ... + A[j+M-1]) and either:
 *
 * 0 <= i < i + L - 1 < j < j + M - 1 < A.length, or
 * 0 <= j < j + M - 1 < i < i + L - 1 < A.length.
 *
 * Example:
 * Given an array apple tree, you have A and B. A can collection m apple tree
 * and B can collection n apple tree. Each of them cannot collect same tree.
 * Please find the max sum of A collection apples
 * and B collection apples. If cannot find valid tree. Please return -1.
 * <p>
 * For instance1, given m = 3, n = 2 and array = [1,2,3,2,1,1,1,5,7]
 * the answer will be  A = 2 + 3 + 2, B = 5 + 7, sum = 7 + 12 = 19.
 * <p>
 * For instance2, given m = 2, n = 2 and array = [1,2,1]
 * the answer will be -1, since they will collect overlapped tree.
 *
 */
public class FindTwoMaxArraySum {
    public static void main(String[] args) {
        int[] appleTrees = {1, 2, 3, 2, 1, 1, 1, 5, 7};
        System.out.println(maxSumTwoNoOverlap(appleTrees, 3, 2));
    }

    private static int maxSumTwoNoOverlap(int[] A, int L, int M) {

        for(int i = 1; i<A.length; i++) {
            A[i] = A[i] + A[i-1];
        }

        int leftM = A[M - 1];
        int leftL = A[L - 1];
        int result = A[L + M - 1];

        for(int i = L + M; i < A.length; i++) {
            //A[M] - A[0]
            leftM = Math.max(leftM, A[i - L] - A[i - L - M]);

            //A[L] - A[0]
            leftL = Math.max(leftL, A[i - M] - A[i - L - M]);

            //A[M] + (A[L + M] - A[M])
            result = Math.max(result, Math.max(leftM + A[i] - A[i - L], leftL + A[i] - A[i - M]));
        }

        return result;
    }
}
