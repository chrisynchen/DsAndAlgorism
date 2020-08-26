package leetcode.dp;

/**
 * Imagine you are playing a board game. You roll a 6-faced dice and move forward the same number of spaces that you rolled.
 * If the finishing point is “n” spaces away from the starting point,
 * please implement a program that calculates how many possible ways there are to arrive exactly at the finishing point.
 */
public class RandomDiceRoll {
    public static void main(String[] args) {
        System.out.println(dp(610));
    }

    //Time complexity is O(6*N), space complexity is O(N)
    private static long dp(int n) {

        if (n <= 0) return 0;

        if (n == 1) return 1;

        long[] a = new long[n];
        a[0] = 1;
        a[1] = 1;
        for (int i = 2; i < n; i++) {
            for (int j = 1; j <= i && j <= 6; j++) {
                a[i] += a[i - j];
            }
        }

        return a[n - 1];
    }
}
