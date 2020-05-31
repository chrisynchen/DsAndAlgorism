package leetcode.array;

/**
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * <p>
 * Example:
 * <p>
 * Input: [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * Note:
 * <p>
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 */
public class MoveZeroes {
    public static void main(String[] args) {
        final int[] nums = new int[]{0, 1, 0, 3, 12};
        moveZeroesBest(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    //best case with O(n)
    private static void moveZeroesBest(int[] nums) {
        int insertIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != 0) {
                nums[insertIndex] = nums[i];
                insertIndex++;
            }
        }

        for (int i = insertIndex; i <nums.length; i++) {
            nums[i] = 0;
        }
    }

    //bad case with O(n^2)
    private static void moveZeroes(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) continue;
            boolean isSwap = false;
            // i == 0;
            for (int j = i + 1; j < nums.length; j++) {
                if (i == j || nums[j] == 0) continue;
                // j != 0
                final int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                break;
            }
        }
    }
}
