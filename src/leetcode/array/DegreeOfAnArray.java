package leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 697. Degree of an Array (Easy)
 * Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.
 *
 * Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,2,3,1]
 * Output: 2
 * Explanation:
 * The input array has a degree of 2 because both elements 1 and 2 appear twice.
 * Of the subarrays that have the same degree:
 * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 * The shortest length is 2. So return 2.
 * Example 2:
 *
 * Input: nums = [1,2,2,3,1,4,2]
 * Output: 6
 * Explanation:
 * The degree is 3 because the element 2 is repeated 3 times.
 * So [2,2,3,1,4,2] is the shortest subarray, therefore returning 6.
 *
 *
 * Constraints:
 *
 * nums.length will be between 1 and 50,000.
 * nums[i] will be an integer between 0 and 49,999.
 */
public class DegreeOfAnArray {


    // Time:O(n) Space: O(n)
    public int findShortestSubArray(int[] nums) {
        if (nums.length == 1) return 1;
        // element, appear times
        Map<Integer, ElementInfo> map = new HashMap<>();
        int maxDegree = 1;
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], new ElementInfo(1, i, i));
            } else {
                final ElementInfo elementInfo = map.get(nums[i]);
                elementInfo.degree++;
                elementInfo.endIndex = i;
                if (maxDegree < elementInfo.degree) {
                    maxDegree = elementInfo.degree;
                }
            }
        }

        int minLength = Integer.MAX_VALUE;
        for (ElementInfo e : map.values()) {
            if (e.degree == maxDegree && e.endIndex - e.startIndex + 1 < minLength) {
                minLength = e.endIndex - e.startIndex + 1;
            }
        }

        return minLength;
    }
}

class ElementInfo {
    int degree;
    int startIndex;
    int endIndex;

    ElementInfo(int degree, int startIndex, int endIndex) {
        this.degree = degree;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }
}
