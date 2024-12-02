package advent_of_code.year_2023;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Day10Part2 {
    private static final int NA = 0;
    private static final int NORTH = 1;
    private static final int EAST = 2;
    private static final int SOUTH = 3;
    private static final int WEST = 4;

    private static final int[][] stepForward = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) {
        Path path = Paths.get("src/advent_of_code/year_2023/test_data/day10part2.txt");
        String[] arr = null;
        try {
            final List<String> lines = Files.readAllLines(path, Charset.defaultCharset());
            arr = lines.toArray(new String[lines.size()]);
            long start = System.currentTimeMillis();
            System.out.println(solve(arr));
            long runTime = System.currentTimeMillis() - start;
            System.out.println("Run Time: " + (runTime / (1000 * 60.0)));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    //dfs + bfs
    private static long solve(String[] examples) {
        int m = examples.length;
        int n = examples[0].length();
        char[][] maze = new char[m][n];
        boolean[][] visited = new boolean[m][n];
        boolean[][] resultMaz = new boolean[m][n];

        int[] start = new int[2];
        for (int i = 0; i < m; i++) {
            String s = examples[i];
            for (int j = 0; j < n; j++) {
                char c = s.charAt(j);
                maze[i][j] = c;
                if (c == 'S') {
                    start[0] = i;
                    start[1] = j;
                }
            }
        }
        visited[start[0]][start[1]] = true;
        boolean first = false;
        first |= dfs(maze, start, m, n, SOUTH, visited, resultMaz);
        first |= dfs(maze, start, m, n, NORTH, visited, resultMaz);
        first |= dfs(maze, start, m, n, EAST, visited, resultMaz);
        first |= dfs(maze, start, m, n, WEST, visited, resultMaz);
        resultMaz[start[0]][start[1]] = first;

        final Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            queue.offer(new int[]{i, 0});
            queue.offer(new int[]{i, n - 1});
        }

        for (int i = 0; i < n; i++) {
            queue.offer(new int[]{0, i});
            queue.offer(new int[]{m - 1, i});
        }

        while (!queue.isEmpty()) {
            int[] c = queue.poll();
            for (int[] e : stepForward) {
                int nextI = c[0] + e[0];
                int nextJ = c[1] + e[1];
                if (nextI < 0 || nextI >= m || nextJ < 0 || nextJ >= n || resultMaz[nextI][nextJ]) continue;
                resultMaz[nextI][nextJ] = true;
                queue.offer(new int[]{nextI, nextJ});
            }
        }

        int result = 0;
        for (boolean[] e1 : resultMaz) {
            for (boolean e2 : e1) {
                if (!e2) result++;
            }
        }

        return result;
    }

    private static boolean dfs(char[][] maze, int[] c, int m, int n, int direction, boolean[][] visited, boolean[][] resultMaz) {
        if (direction == SOUTH) {
            return goSouth(maze, c, m, n, visited, resultMaz);
        } else if (direction == NORTH) {
            return goNorth(maze, c, m, n, visited, resultMaz);
        } else if (direction == EAST) {
            return goEAST(maze, c, m, n, visited, resultMaz);
        } else {
            //WEST
            return goWEST(maze, c, m, n, visited, resultMaz);
        }
    }

    private static boolean goSouth(char[][] maze, int[] c, int m, int n, boolean[][] visited, boolean[][] resultMaz) {
        boolean result = false;
        if (c[0] + 1 < m && c[1] < n && c[1] >= 0) {
            char next = maze[c[0] + 1][c[1]];
            boolean last = visited[c[0] + 1][c[1]];
            if (last) {
                resultMaz[c[0]][c[1]] = true;
                return true;
            }
            visited[c[0] + 1][c[1]] = true;
            if (next == 'L') {
                result |= dfs(maze, new int[]{c[0] + 1, c[1]}, m, n, EAST, visited, resultMaz);
            } else if (next == 'J') {
                result |= dfs(maze, new int[]{c[0] + 1, c[1]}, m, n, WEST, visited, resultMaz);
            } else if (next == '|') {
                result |= dfs(maze, new int[]{c[0] + 1, c[1]}, m, n, SOUTH, visited, resultMaz);
            }
        }
        resultMaz[c[0]][c[1]] = result;
        return result;
    }

    private static boolean goNorth(char[][] maze, int[] c, int m, int n, boolean[][] visited, boolean[][] resultMaz) {
        boolean result = false;
        if (c[0] - 1 >= 0 && c[1] < n && c[1] >= 0) {
            char next = maze[c[0] - 1][c[1]];
            boolean last = visited[c[0] - 1][c[1]];
            if (last) {
                resultMaz[c[0]][c[1]] = true;
                return true;
            }
            visited[c[0] - 1][c[1]] = true;
            if (next == 'F') {
                result |= dfs(maze, new int[]{c[0] - 1, c[1]}, m, n, EAST, visited, resultMaz);
            } else if (next == '7') {
                result |= dfs(maze, new int[]{c[0] - 1, c[1]}, m, n, WEST, visited, resultMaz);
            } else if (next == '|') {
                result |= dfs(maze, new int[]{c[0] - 1, c[1]}, m, n, NORTH, visited, resultMaz);
            }
        }
        resultMaz[c[0]][c[1]] = result;
        return result;
    }

    private static boolean goEAST(char[][] maze, int[] c, int m, int n, boolean[][] visited, boolean[][] resultMaz) {
        boolean result = false;
        if (c[0] < m && c[0] >= 0 && c[1] + 1 < n) {
            char next = maze[c[0]][c[1] + 1];
            boolean last = visited[c[0]][c[1] + 1];
            if (last) {
                resultMaz[c[0]][c[1]] = true;
                return true;
            }
            visited[c[0]][c[1] + 1] = true;
            if (next == '7') {
                result |= dfs(maze, new int[]{c[0], c[1] + 1}, m, n, SOUTH, visited, resultMaz);
            } else if (next == 'J') {
                result |= dfs(maze, new int[]{c[0], c[1] + 1}, m, n, NORTH, visited, resultMaz);
            } else if (next == '-') {
                result |= dfs(maze, new int[]{c[0], c[1] + 1}, m, n, EAST, visited, resultMaz);
            }
        }
        resultMaz[c[0]][c[1]] = result;
        return result;
    }

    private static boolean goWEST(char[][] maze, int[] c, int m, int n, boolean[][] visited, boolean[][] resultMaz) {
        boolean result = false;
        if (c[0] < m && c[0] >= 0 && c[1] - 1 >= 0) {
            char next = maze[c[0]][c[1] - 1];
            boolean last = visited[c[0]][c[1] - 1];
            if (last) {
                resultMaz[c[0]][c[1]] = true;
                return true;
            }
            visited[c[0]][c[1] - 1] = true;
            if (next == 'F') {
                result |= dfs(maze, new int[]{c[0], c[1] - 1}, m, n, SOUTH, visited, resultMaz);
            } else if (next == 'L') {
                result |= dfs(maze, new int[]{c[0], c[1] - 1}, m, n, NORTH, visited, resultMaz);
            } else if (next == '-') {
                result |= dfs(maze, new int[]{c[0], c[1] - 1}, m, n, WEST, visited, resultMaz);
            }
        }
        resultMaz[c[0]][c[1]] = result;
        return result;
    }

    //862
    //5
}
