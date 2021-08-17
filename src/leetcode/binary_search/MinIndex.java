package leetcode.binary_search;

public class MinIndex {
    public static void main(String[] args) {
        final int[] nums = {2,2,2,3};
        System.out.println("result:" + bs(nums, 2));
    }

    private static int bs(int[] nums, int target) {
        int headIndex = 0;
        int tailIndex = nums.length - 1;

        while(headIndex < tailIndex) {
            int mid = headIndex + (tailIndex - headIndex) / 2;

            if(nums[mid] >= target) {
                tailIndex = mid;
            } else {
                //nums[mid] < target
                headIndex = mid + 1;
            }
        }

        return nums[tailIndex] == target ? tailIndex: -1;
    }
}
