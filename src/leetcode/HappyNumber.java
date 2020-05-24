package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Write an algorithm to determine if a number n is "happy".
 *
 * A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.
 *
 * Return True if n is a happy number, and False if not.
 *
 * Example:
 *
 * Input: 19
 * Output: true
 * Explanation:
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 */
public class HappyNumber {
    public static void main(String[] args) {
        System.out.println(isHappy(1));
        System.out.println(isHappy(19));
        System.out.println(isHappy(2));
    }

    private static boolean isHappy(int n) {

        if(n == 0) return false;

        if(n == 1) return true;

        final Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();

        while(n != 1) {
            final int result = sum(n);
            if (map.get(result) == null) {
                map.put(result, true);
            } else {
                return false;
            }
            n = result;
        }

        return true;
    }

    private static int sum(int x) {
        if(x / 10 == 0) {
            return x * x;
        }
        int y= x % 10;
        return y * y + sum((x - y) / 10);
    }
}
