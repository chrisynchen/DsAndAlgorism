package google;

import java.util.Arrays;

public class MinEmptySpaceInChessboard {

    /**
     *
     * Given n * n chessboard and pos array means position (i, j) has chess.
     * Calculate min distance from each chess to the empty space.
     * ex: n = 3, pos = {{0, 0}, {0, 1}, {0, 2}, {1, 1}, {1, 2}, {2, 0}}
     * ans: [1, 2, 2, 1, 1, 1]
     *
     * follow up: if n = ∞ , pos has just few chess. How to improve?
     */
    public static void main(String[] args) {
        //O,O,O,
        //X,O,O
        //O,X,X

        int n = 3;
        int[][] pos = new int[][]{{0, 0}, {0, 1}, {0, 2}, {1, 1}, {1, 2}, {2, 0}};
        int[] result = new int[pos.length];

        char[][] c = new char[n][n];
        int[][] dp = new int[n][n];
        for(int i = 0; i < c.length; i++) {
            Arrays.fill(c[i], 'X');
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        for(int i = 0; i < pos.length; i++) {
            c[pos[i][0]][pos[i][1]] = 'O';
        }
        boolean[][] isVisited = new boolean[n][n];
        for (int i = 0; i < pos.length; i++) {
            result[i] = minEmptySpaceInChessboard(c, pos[i][0], pos[i][1], dp, isVisited);
            if(result[i] == Integer.MAX_VALUE) {
                result[i] = 0;
            }
        }

        for(int e: result) {
            System.out.print(e + ",");
        }
    }

    private static int minEmptySpaceInChessboard(char[][] c, int i, int j, int[][] dp, boolean[][] isVisited) {
        if (i < 0 || j < 0 || i >= c.length || j >= c[0].length || isVisited[i][j]) return Integer.MAX_VALUE;

        if (c[i][j] == 'X') return 0;

        if (dp[i][j] < Integer.MAX_VALUE) return dp[i][j];
        isVisited[i][j] = true;
        int result = Integer.MAX_VALUE;
        int bottom = minEmptySpaceInChessboard(c, i + 1, j, dp, isVisited);
        int top = minEmptySpaceInChessboard(c, i - 1, j, dp, isVisited);
        int right = minEmptySpaceInChessboard(c, i, j + 1, dp, isVisited);
        int left = minEmptySpaceInChessboard(c, i, j - 1, dp, isVisited);
        isVisited[i][j] = false;

        if (bottom < Integer.MAX_VALUE) {
            result = Math.min(result, bottom + 1);
        }
        if (top < Integer.MAX_VALUE) {
            result = Math.min(result, top + 1);
        }
        if (left < Integer.MAX_VALUE) {
            result = Math.min(result, left + 1);
        }
        if (right < Integer.MAX_VALUE) {
            result = Math.min(result, right + 1);
        }
        dp[i][j] = result;
        return result;
    }
}
