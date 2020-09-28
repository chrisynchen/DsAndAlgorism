package leetcode.array;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * 239. Sliding Window Maximum (Hard)
 * You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 * <p>
 * Return the max sliding window.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * Example 2:
 * <p>
 * Input: nums = [1], k = 1
 * Output: [1]
 * Example 3:
 * <p>
 * Input: nums = [1,-1], k = 1
 * Output: [1,-1]
 * Example 4:
 * <p>
 * Input: nums = [9,11], k = 2
 * Output: [11]
 * Example 5:
 * <p>
 * Input: nums = [4,-2], k = 2
 * Output: [4]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * 1 <= k <= nums.length
 */
public class SlidingWindowMaximum {
    public static void main(String[] args) {
        int[] results = maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3);
        for (int r : results) {
            System.out.println(r);
        }
    }

    // use deque
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) {
            return new int[0];
        }
        int[] result = new int[nums.length - k + 1];
        int index = 0;
        // store index
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            // remove numbers out of range k
            while (!deque.isEmpty() && deque.peek() < i - k + 1) {
                deque.poll();
            }
            // remove smaller numbers in k range as they are useless
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            // q contains index... r contains content
            deque.offer(i);
            if (i >= k - 1) {
                result[index++] = nums[deque.peek()];
            }
        }
        return result;
    }

    // BAD O(N*K)
    public int[] maxSlidingWindowBad(int[] nums, int k) {

        if(k == 1) {
            return nums;
        }

        int[] result = new int[nums.length - k + 1];

        for(int i = k - 1;i < nums.length;i++) {
            int max = Integer.MIN_VALUE;
            for(int j = i - k + 1; j <= i; j++) {
                max = Math.max(max, nums[j]);
            }
            result[i - k + 1] = max;
        }

        return result;
    }
}
