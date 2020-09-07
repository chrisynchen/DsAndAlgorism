package leetcode.array;

/**
 * Given an array of 4 digits, return the largest 24 hour time that can be made.
 *
 * The smallest 24 hour time is 00:00, and the largest is 23:59.  Starting from 00:00, a time is larger if more time has elapsed since midnight.
 *
 * Return the answer as a string of length 5.  If no valid time can be made, return an empty string.
 *
 *
 *
 * Example 1:
 *
 * Input: [1,2,3,4]
 * Output: "23:41"
 * Example 2:
 *
 * Input: [5,5,5,5]
 * Output: ""
 *
 *
 * Note:
 *
 * A.length == 4
 * 0 <= A[i] <= 9
 */
class LargestTimeForGivenDigits {
    //1st available : 0, 1, 2
    //2st available : if 1st == 0 or 1 => 0 ~ 9 else 0 ~3

    // 3rd available: 0 ~ 5
    // 4rd available: 0 ~ 9


    public static void main(String[] args) {
        System.out.println(largestTimeFromDigits(new int[]{1,2,3,4}));
        System.out.println(largestTimeFromDigits(new int[]{5,5,5,5}));
    }

    //between 0000 ~ 2359
    private static String largestTimeFromDigits(int[] A) {
        int maxValidNum = 0;
        boolean isValid = false;
        for(int i = 0; i< A.length; i++) {
            for(int j = 0; j< A.length; j++) {
                for(int k = 0; k< A.length; k++) {
                    if(i != j && i != k && j != k) {
                        if(A[i] <= 2 && A[k] <= 5) {
                            if(A[i] == 2 && A[j] >= 4) continue;
                            //i == 0 or 1
                            isValid = true;
                            maxValidNum = Math.max(maxValidNum, A[i] * 1000 + A[j] * 100 + A[k]* 10 + A[6-i-j-k]);
                        }
                    }
                }
            }
        }

        if(isValid) {
            return getTimeString(maxValidNum);
        } else {
            return "";
        }
    }

    private static String getTimeString(int maxValidNum) {
        final String first = String.valueOf(maxValidNum / 1000);
        final String second = String.valueOf((maxValidNum % 1000) / 100);
        final String third = String.valueOf((maxValidNum % 100) / 10);

        return first + second + ":" + third + String.valueOf(maxValidNum % 10);
    }
}
