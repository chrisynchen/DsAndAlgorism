package leetcode.dp;

/**
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 *
 * Note: You can only move either down or right at any point in time.
 *
 * Example:
 *
 * Input:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * Output: 7
 * Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 */
public class MinimumPathSum {
    public static void main(String[] args) {
        final int[][] test = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println("result:" + minPathSum(test));
    }

    private static int minPathSum(int[][] grid) {
        final int[][] dp = new int[grid.length][grid[0].length];

        dp[0][0] = grid[0][0];

        for(int i =0; i<grid.length;i++){
            for(int j =0; j<grid[0].length;j++){
                if (i == 0 && j == 0) continue;

                if (i == 0) {
                    dp[0][j] = dp[0][j - 1] + grid[0][j];
                } else if (j == 0) {
                    dp[i][0] = dp[i - 1][0] + grid[i][0];
                } else {
                    dp[i][j] = Math.min(dp[i][j - 1] + grid[i][j], dp[i - 1][j] + grid[i][j]);
                }
            }
        }

        return dp[grid.length - 1][grid[0].length - 1];
    }
}
