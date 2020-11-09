package leetcode.knapsack;

/**
 * 416. Partition Equal Subset Sum (Medium)
 *
 * Given a non-empty array nums containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 *
 * Example 1:
 *
 * Input: nums = [1,5,11,5]
 * Output: true
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 * Example 2:
 *
 * Input: nums = [1,2,3,5]
 * Output: false
 * Explanation: The array cannot be partitioned into equal sum subsets.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 */
public class PartitionEqualSubsetSum {

    public static void main(String[] args) {
//        System.out.println(canPartitionDP(new int[]{3, 3, 3, 3}));
        System.out.println(canPartitionRecursion(new int[]{3, 4, 3, 3}));
    }

    private static boolean canPartitionRecursion(int[] nums) {
        if (nums == null || nums.length <= 1) return false;

        int total = 0;
        for (int value : nums) {
            total += value;
        }

        if (total % 2 != 0) {
            return false;
        }

        int targetValue = total / 2;

        return recursion(nums, targetValue, nums.length) == targetValue;
    }

    private static int recursion(int[] nums, int targetValue, int index) {
        if (index == 0) return 0;
        if (nums[index - 1] > targetValue) {
            return recursion(nums, targetValue, index - 1);
        }
        return Math.max(nums[index - 1] + recursion(nums, targetValue - nums[index - 1], index - 1),
                recursion(nums, targetValue, index - 1));
    }

    private static boolean canPartitionDP(int[] nums) {
        if (nums == null || nums.length <= 1) return false;

        int total = 0;
        for (int value : nums) {
            total += value;
        }

        if (total % 2 != 0) {
            return false;
        }

        int targetValue = total / 2;

        int dp[][] = new int[nums.length + 1][targetValue + 1];

        for (int i = 0; i <= nums.length; i++) {
            for (int j = 0; j <= targetValue; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (nums[i - 1] <= j) {
                    dp[i][j] = Math.max(nums[i - 1] + dp[i - 1][j - nums[i - 1]], dp[i - 1][j]);
                    if (dp[i][j] == targetValue) {
                        return true;
                    }
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return false;
    }
}
