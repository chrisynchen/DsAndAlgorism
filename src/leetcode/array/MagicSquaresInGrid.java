package leetcode.array;

import java.util.HashSet;
import java.util.Set;

public class MagicSquaresInGrid {
    public static void main(String[] args) {
        int[][] arr = {{7, 7, 7, 9, 1, 4, 6, 7, 2, 5}, {3, 1, 9, 9, 2, 6, 1, 5, 9, 5}, {8, 6, 3, 7, 1, 8, 8, 3, 4, 4}, {3, 6, 9, 8, 3, 3, 8, 1, 4, 8}, {4, 4, 1, 6, 3, 2, 4, 6, 5, 4}, {4, 7, 6, 2, 6, 7, 0, 5, 5, 3}
                , {8, 2, 5, 3, 4, 3, 8, 9, 3, 1}, {5, 6, 1, 4, 9, 5, 1, 7, 2, 8}, {0, 4, 8, 1, 2, 7, 6, 6, 3, 5}, {7, 2, 3, 5, 5, 4, 6, 1, 4, 9}};
        System.out.println(numMagicSquaresInside(arr));
    }

    public static int numMagicSquaresInside(int[][] grid) {
        if (grid == null || grid.length < 3 || grid[0].length < 3) return 0;

        int count = 0;

        for (int i = 0; i < grid.length - 2; i++) {
            for (int j = 0; j < grid[0].length - 2; j++) {
                count += isMagicSquare(grid, i, j) ? 1 : 0;
            }
        }

        return count;
    }

    public static boolean isMagicSquare(int[][] grid, int x, int y) {

        final Set<Integer> hashSet = new HashSet<Integer>();
        final int sum = grid[x][y] + grid[x + 1][y + 1] + grid[x + 2][y + 2];

        if (sum != grid[x + 2][y] + grid[x + 1][y + 1] + grid[x][y + 2]) {
            return false;
        }

        for (int i = x; i < x + 3; i++) {
            int xSum = 0;
            for (int j = y; j < y + 3; j++) {

                if (grid[i][j] >= 10 || grid[i][j] <= 0) return false;

                xSum += grid[i][j];

                if (!hashSet.contains(grid[i][j])) {
                    hashSet.add(grid[i][j]);
                } else {
                    return false;
                }
            }

            if (sum != xSum) {
                return false;
            }
        }

        for (int i = y; i < y + 3; i++) {
            int ySum = 0;
            for (int j = x; j < x + 3; j++) {
                ySum += grid[j][i];
            }

            if (sum != ySum) {
                return false;
            }
        }

        return true;
    }
}
