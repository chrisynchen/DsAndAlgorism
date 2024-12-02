package others;

public class LongestPathIn2DArray {
    public static void main(String[] args) {
        char[][] test = new char[][]{
                {'#', '.', '#', '#', '.', '.', '#',},
                {'#', '.', '.', '.', '.', '.', '#',},
                {'#', '.', '.', '#', '#', '.', '#',},
                {'#', '.', '.', '#', '#', '.', '#',},
                {'#', '.', '.', '#', '#', '.', '#',},
        };
        int result = 0;
        int[][] dp = new int[test.length][test[0].length];
        for (int i = 0; i < test[0].length; i++) {
            result = Math.max(result, dfs(test, 0, 5, dp));
        }

        System.out.println(result);
    }

    private static int dfs(char[][] test, int i, int j, int[][] dp) {
        if (i >= test.length || j < 0 || j >= test[0].length || test[i][j] == '#') return 0;

        if (dp[i][j] > 0) return dp[i][j];

        int result = 1 + Math.max(dfs(test, i + 1, j, dp), Math.max(dfs(test, i, j + 1, dp), dfs(test, i, j - 1, dp)));
        dp[i][j] = result;
        System.out.println("i:" + i + ",j:" + j + "result:" + result);

        return dp[i][j];
    }
}
