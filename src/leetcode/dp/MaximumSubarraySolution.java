package leetcode.dp;

/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 * <p>
 * Example:
 * <p>
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * Follow up:
 * <p>
 * If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */
public class MaximumSubarraySolution {
    public static void main(String[] args) {
        System.out.println("maxSubArray result:" + maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        System.out.println("maxSubArrayDp result:" + maxSubArrayDp(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    private static int maxSubArray(int[] nums) {
        if(nums == null || nums.length == 0) return 0;

        int maxSum = nums[0];
        int currentSum = nums[0];
        for (int i = 1; i< nums.length; i++) {

            if(nums[i] > currentSum + nums[i]) {
                currentSum = nums[i];
            } else {
                currentSum += nums[i];
            }

            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
        }

        return maxSum;
    }

    //dp
    private static int maxSubArrayDp(int[] nums) {
        if(nums == null || nums.length == 0) return 0;

        int maxSum = nums[0];
        int[] sum =  new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i< nums.length; i++) {
            if(nums[i] > sum[i - 1] + nums[i]) {
                sum[i] = nums[i];
            } else {
                sum[i] = sum[i - 1] + nums[i];
            }

            if (sum[i] > maxSum) {
                maxSum = sum[i];
            }
        }

        return maxSum;
    }
}
