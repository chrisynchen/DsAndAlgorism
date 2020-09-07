package leetcode.array;

import java.util.TreeSet;

/**
 * Given an array of integers, find out whether there are two distinct indices i and j in the array such that the absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,1], k = 3, t = 0
 * Output: true
 * Example 2:
 * <p>
 * Input: nums = [1,0,1,1], k = 1, t = 2
 * Output: true
 * Example 3:
 * <p>
 * Input: nums = [1,5,9,1,5,9], k = 2, t = 3
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= nums.length <= 2 * 104
 * -231 <= nums[i] <= 231 - 1
 * 0 <= k <= 104
 * 0 <= t <= 231 - 1
 */

class ContainsDuplicateIII {

    //0<= abs(nums[i] - nums[j]) <= t
    //0<= abs(i-j) <= k

    public static void main(String[] args) {
        System.out.println(containsNearbyAlmostDuplicate(new int[]{1, 2, 3, 1}, 3, 0));
        System.out.println(containsNearbyAlmostDuplicate(new int[]{1,0,1,1}, 1, 2));
        System.out.println(containsNearbyAlmostDuplicate(new int[]{1,5,9,1,5,9}, 2, 3));
    }

    //O(nlogk)
    private static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {

        if (nums.length < 2 || k < 1) return false;

        final TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {

            long l = (long) nums[i];

            final Long floor = set.floor(l);
            final Long ceil = set.ceiling(l);

            if ((floor != null && l - floor <= t) ||
                    (ceil != null && ceil - l <= t)) {
                return true;
            }

            set.add(l);

            if (i >= k)
                set.remove((long) nums[i - k]);
        }

        return false;
    }
}
