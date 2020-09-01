package leetcode.graph;

/**
 * 1020. Number of Enclaves (Medium)
 * <p>
 * Given a 2D array A, each cell is 0 (representing sea) or 1 (representing land)
 * <p>
 * A move consists of walking from one land square 4-directionally to another land square, or off the boundary of the grid.
 * <p>
 * Return the number of land squares in the grid for which we cannot walk off the boundary of the grid in any number of moves.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
 * Output: 3
 * Explanation:
 * There are three 1s that are enclosed by 0s, and one 1 that isn't enclosed because its on the boundary.
 * Example 2:
 * <p>
 * Input: [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
 * Output: 0
 * Explanation:
 * All 1s are either on the boundary or can reach the boundary.
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= A.length <= 500
 * 1 <= A[i].length <= 500
 * 0 <= A[i][j] <= 1
 * All rows have the same size.
 */
public class NumberOfEnclaves {
    public static void main(String[] args) {
        System.out.println(numEnclaves(new int[][]{{0, 0, 0, 0}, {1, 0, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}}));
    }

    // use DFS O(N^2)
    public static int numEnclaves(int[][] A) {
        if (A.length < 3 || A[0].length < 3) return 0;

        for (int i = 0; i < A.length; i++) {
            // if(A[i][0] == 1) {
            removeNonEnclaves(A, i, 0);
            // }

            // if(A[i][A[0].length - 1] == 1) {
            removeNonEnclaves(A, i, A[0].length - 1);
            // }
        }

        for (int j = 0; j < A[0].length; j++) {
            // if(A[0][j] == 1) {
            removeNonEnclaves(A, 0, j);
            // }

            // if(A[A.length - 1][j] == 1) {
            removeNonEnclaves(A, A.length - 1, j);
            // }
        }

        int sum = 0;
        for (int x = 0; x < A.length; x++) {
            for (int y = 0; y < A[0].length; y++) {
                sum += A[x][y];
            }
        }

        return sum;
    }

    private static void removeNonEnclaves(int[][] A, int i, int j) {
        if (i < 0 || i >= A.length || j < 0 || j >= A[0].length ||
                A[i][j] == 0) return;
        A[i][j] = 0;
        removeNonEnclaves(A, i + 1, j);
        removeNonEnclaves(A, i - 1, j);
        removeNonEnclaves(A, i, j + 1);
        removeNonEnclaves(A, i, j - 1);
    }
}
