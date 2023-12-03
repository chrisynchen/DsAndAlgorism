import java.util.TreeSet;

public class MaxSumOfSubArrayNoLargerThanK {
    public static void main(String[] args) {
//        System.out.println("maxSubArrayNoLargerThanK result:" + maxSubArrayNoLargerThanK(new int[]{-1, 2, 2}, 4));
//        System.out.println("maxSubArrayNoLargerThanK result:" + maxSubArrayNoLargerThanK(new int[]{5, -2, 6, 3, -5}, 15));
//        System.out.println("maxSubArrayNoLargerThanK result:" + maxSubArrayNoLargerThanK(new int[]{1, 2, 3, 4, 5}, 11));
//        System.out.println("maxSubArrayNoLargerThanK result:" + maxSubArrayNoLargerThanK(new int[]{2, 4, 6, 8, 10}, 7));
//
//        System.out.println("maxSubArrayNoLargerThanK result:" + maxSubArrayNoLargerThanKWithPresum(new int[]{-1, 2, 2}, 4));
//        System.out.println("maxSubArrayNoLargerThanK result:" + maxSubArrayNoLargerThanKWithPresum(new int[]{5, -2, 6, 3, -5}, 15));
        System.out.println("maxSubArrayNoLargerThanK result:" + maxSubArrayNoLargerThanKWithPresum(new int[]{1, 2, 3, 4, 5}, 11));
//        System.out.println("maxSubArrayNoLargerThanK result:" + maxSubArrayNoLargerThanKWithPresum(new int[]{2, 4, 6, 8, 10}, 7));
    }

    //O(n), only works for non-negative numbers
    private static int maxSubArrayNoLargerThanK(int[] nums, int k) {
        if (nums == null || nums.length == 0) return Integer.MIN_VALUE;

        //sliding window
        int result = Integer.MIN_VALUE;
        int current = 0;
        int prevIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            current += nums[i];
            while (current > k) {
                current -= nums[prevIndex++];
            }
            result = Math.max(current, result);
        }

        return result;
    }

    private static int maxSubArrayNoLargerThanKWithPresum(int[] nums, int k) {
        int result = Integer.MIN_VALUE;
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0); // add 0 to handle sub arrays with negative sum
        int sum = 0;
        //[1,2,3,4,5], k=11
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            Integer ceiling = set.ceiling(sum - k);
            System.out.println(ceiling);
            if (ceiling != null) {
                result = Math.max(result, sum - ceiling);
            }
            set.add(sum);
        }
        return result;
    }
}
