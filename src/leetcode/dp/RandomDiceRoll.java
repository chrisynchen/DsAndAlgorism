package leetcode.dp;

/**
 * Imagine you are playing a board game. You roll a 6-faced dice and move forward the same number of spaces that you rolled.
 * If the finishing point is “n” spaces away from the starting point,
 * please implement a program that calculates how many possible ways there are to arrive exactly at the finishing point.
 */
public class RandomDiceRoll {
    public static void main(String[] args) {
        System.out.println(dp(1));
        System.out.println(dp(2));
        System.out.println(dp(3));
        System.out.println(dp(4));
        System.out.println(dp(5));
        System.out.println(dp(6));
        System.out.println(dp(7));
        System.out.println(dp(610));
    }

    //Time complexity is O(N), space complexity is O(N)
    private static long dp(int n) {

        if (n <= 0) return 0;

        if (n == 1 || n == 2 || n == 3 || n == 4 || n == 5 || n == 6) return (long) Math.pow(2, n - 1);

        long[] dp = new long[n];
        for (int i = 0; i < 6; i++) {
            dp[i] = (long) Math.pow(2, n);
        }
        //dp[0]
        //dp[1]
        //dp[2]
        //dp[3]
        //dp[4]
        //dp[5]
        for (int i = 6; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3] + dp[i - 4] + dp[i - 5] + dp[i - 6];
        }

        return dp[n - 1];
    }
}
