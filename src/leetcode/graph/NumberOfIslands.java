package leetcode.graph;

/**
 * 200. Number of Islands (Medium)
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * Output: 1
 * Example 2:
 *
 * Input: grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * Output: 3
 */
public class NumberOfIslands {
    // use DFS O(N^2)
    public int numIslands(char[][] grid) {

        if(grid == null || grid.length == 0) return 0;

        int sum = 0;
        for(int i = 0; i<grid.length; i++) {
            for(int j = 0; j<grid[0].length; j++) {
                sum += markAsReadAndGetIslandCount(grid, i, j);
            }
        }

        return sum;
    }

    public int markAsReadAndGetIslandCount(char[][] grid, int i, int j) {
        if(i < 0 || i >= grid.length || j < 0 || j>= grid[0].length || grid[i][j] == '0') return 0;
        grid[i][j] = '0';
        markAsReadAndGetIslandCount(grid, i+1, j);
        markAsReadAndGetIslandCount(grid, i-1, j);
        markAsReadAndGetIslandCount(grid, i, j+1);
        markAsReadAndGetIslandCount(grid, i, j-1);

        return 1;
    }
}
