package leetcode.binary_search;

public class SearchInRotatedSortedArray {

    public static void main(String[] args) {
        final int[] test = {4, 5, 6, 7, 8, 1, 2, 3};
        System.out.println("result:" + search(test, 8));
    }

    private static int search(int[] nums, int target) {

        int startIndex = 0;
        int endIndex = nums.length - 1;

        while (startIndex <= endIndex) {
            if(nums[startIndex] == target) return startIndex;

            if(nums[endIndex] == target) return endIndex;

            int mid = (startIndex + endIndex) / 2;
            if (startIndex != endIndex) {
                System.out.println("mid:" + mid +", startIndex:" + startIndex + ", endIndex:" + endIndex);
            }

            if (target == nums[mid]) return mid;

            if (target > nums[mid]) {
                if (target > nums[endIndex]) {
                    endIndex = mid - 1;
                } else {
                    startIndex = mid + 1;
                }
            } else if (target < nums[mid]) {
                if (target > nums[startIndex]) {
                    endIndex = mid - 1;
                } else {
                    startIndex = mid + 1;
                }
            }
        }

        return -1;
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
