package leetcode;

import java.util.Arrays;

/**
 * 473. Matchsticks to Square
 * Remember the story of Little Match Girl? By now, you know exactly what matchsticks the little match girl has, please find out a way you can make one square by using up all those matchsticks. You should not break any stick, but you can link them up, and each matchstick must be used exactly one time.
 * <p>
 * Your input will be several matchsticks the girl has, represented with their stick length. Your output will either be true or false, to represent whether you could make one square using all the matchsticks the little match girl has.
 * <p>
 * Example 1:
 * Input: [1,1,2,2,2]
 * Output: true
 * <p>
 * Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
 * Example 2:
 * Input: [3,3,3,3,4]
 * Output: false
 * <p>
 * Explanation: You cannot find a way to form a square with all the matchsticks.
 * Note:
 * The length sum of the given matchsticks is in the range of 0 to 10^9.
 * The length of the given matchstick array will not exceed 15.
 *
 * DFS solution
 * time complexity: O(n)
 * space complexity: O(n)
 */
public class MatchsticksToSquare {

    public static void main(String[] args) {
        System.out.println(makesquare(new int[]{1, 1, 2, 2, 2}));
        System.out.println(makesquare(new int[]{2, 2, 2, 2, 2}));
        System.out.println(makesquare(new int[]{3, 3, 3, 4}));
        System.out.println(makesquare(new int[]{3, 3, 3, 3, 3, 3, 3, 3}));
    }

    private static boolean makesquare(int[] nums) {
        if (nums == null || nums.length < 4) return false;

        int sum = 0;
        for (int element : nums) {
            sum += element;
        }

        if (sum % 4 != 0) return false;

        // key thing to reduce the runtime.
        Arrays.sort(nums);
        reverse(nums);

        return isSquare(nums, new int[4], sum / 4, 0);
    }

    private static boolean isSquare(int[] nums, int[] sideLength, int length, int numsIndex) {

        if(numsIndex == nums.length) {
            return sideLength[0] == length && sideLength[0] == sideLength[1] && sideLength[0] == sideLength[2];
        }

        for (int i = 0; i < sideLength.length; i++) {
            if (sideLength[i] + nums[numsIndex] > length) continue;
            sideLength[i] += nums[numsIndex];
            if (isSquare(nums, sideLength, length, numsIndex + 1)) return true;
            sideLength[i] -= nums[numsIndex];
        }

        return false;
    }

    private static void reverse(int[] nums) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++; j--;
        }
    }
}
