package leetcode.binary_search;

/**
 * 81. Search in Rotated Sorted Array II (Medium)
 *
 *
 * Add to List
 *
 * Share
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).
 *
 * You are given a target value to search. If found in the array return true, otherwise return false.
 *
 * Example 1:
 *
 * Input: nums = [2,5,6,0,0,1,2], target = 0
 * Output: true
 * Example 2:
 *
 * Input: nums = [2,5,6,0,0,1,2], target = 3
 * Output: false
 * Follow up:
 *
 * This is a follow up problem to Search in Rotated Sorted Array, where nums may contain duplicates.
 * Would this affect the run-time complexity? How and why?
 */
public class SearchInRotatedSortedArray {

    public static void main(String[] args) {
        final int[] test = {4, 5, 6, 7, 8, 1, 2, 3};
        System.out.println("result:" + search(test, 8));
    }

    private static boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;

        int startIndex = 0;
        int endIndex = nums.length - 1;
        while (startIndex <= endIndex) {
            int mid = startIndex + (endIndex - startIndex) / 2;

            if (nums[mid] == target) return true;

            if (nums[mid] > nums[startIndex] || nums[mid] > nums[endIndex]) {
                // left sorted or right unsorted
                if (target >= nums[startIndex] && target < nums[mid]) {
                    endIndex = mid - 1;
                } else {
                    startIndex = mid + 1;
                }
            } else if (nums[mid] < nums[startIndex] || nums[mid] < nums[endIndex]) {
                // right sorted or left unsorted
                if (target > nums[mid] && target <= nums[endIndex]) {
                    startIndex = mid + 1;
                } else {
                    endIndex = mid - 1;
                }
            } else {
                endIndex--;
            }
        }

        return false;
    }

    private static int binarySearchForSortedArray(int[] sortedArray, int target, int start, int end) {
        if (start < 0 || end >= sortedArray.length) {
            return -1;
        }
        while (start <= end) {
            int mid = (start + end) / 2;
            if (sortedArray[mid] == target) {
                return mid;
            } else if (target > sortedArray[mid]) {
                start = mid + 1;
            } else if (target < sortedArray[mid]) {
                end = mid - 1;
            }
        }
        return -1;
    }
}
