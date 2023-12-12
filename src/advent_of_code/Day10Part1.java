package advent_of_code;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Day10Part1 {
    private static final int NA = 0;
    private static final int NORTH = 1;
    private static final int EAST = 2;
    private static final int SOUTH = 3;
    private static final int WEST = 4;

    private static final int[][] stepForward = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) {
        Path path = Paths.get("src/advent_of_code/test_data/day10part1.txt");
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
        long count = 1;
        int m = examples.length;
        int n = examples[0].length();
        char[][] maze = new char[m][n];
        boolean[][] visited = new boolean[m][n];
        final Queue<int[]> queue = new LinkedList<>();


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

        queue.offer(new int[]{start[0] - 1, start[1], NORTH});
        queue.offer(new int[]{start[0] + 1, start[1], SOUTH});
        queue.offer(new int[]{start[0], start[1] + 1, EAST});
        queue.offer(new int[]{start[0], start[1] - 1, WEST});

        long result = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean r = false;
            for (int k = 0; k < size; k++) {
                int[] c = queue.poll();
                r |= goSouth(maze, c, queue, m, n, visited);
                r |= goNorth(maze, c, queue, m, n, visited);
                r |= goEAST(maze, c, queue, m, n, visited);
                r |= goWEST(maze, c, queue, m, n, visited);
            }
            if (r) {
                result = Math.max(result, count);
            }
            count++;
        }

        return result;
    }

    private static boolean goSouth(char[][] maze, int[] c, Queue<int[]> queue, int m, int n, boolean[][] visited) {
        if (c[0] + 1 < m && c[1] < n && c[1] >= 0) {
            char next = maze[c[0] + 1][c[1]];
            boolean last = visited[c[0] + 1][c[1]];
            if (last) return true;
            if (next == 'L') {
                queue.offer(new int[]{c[0] + 1, c[1], EAST});
                visited[c[0] + 1][c[1]] = true;
            } else if (next == 'J') {
                queue.offer(new int[]{c[0] + 1, c[1], WEST});
                visited[c[0] + 1][c[1]] = true;
            } else if (next == '|') {
                queue.offer(new int[]{c[0] + 1, c[1], SOUTH});
                visited[c[0] + 1][c[1]] = true;
            }
        }
        return false;
    }

    private static boolean goNorth(char[][] maze, int[] c, Queue<int[]> queue, int m, int n, boolean[][] visited) {
        if (c[0] - 1 >= 0 && c[1] < n && c[1] >= 0) {
            char next = maze[c[0] - 1][c[1]];
            boolean last = visited[c[0] - 1][c[1]];
            if (last) return true;
            if (next == 'F') {
                queue.offer(new int[]{c[0] - 1, c[1], EAST});
                visited[c[0] - 1][c[1]] = true;
            } else if (next == '7') {
                queue.offer(new int[]{c[0] - 1, c[1], WEST});
                visited[c[0] - 1][c[1]] = true;
            } else if (next == '|') {
                queue.offer(new int[]{c[0] - 1, c[1], NORTH});
                visited[c[0] - 1][c[1]] = true;
            }
        }
        return false;
    }

    private static boolean goEAST(char[][] maze, int[] c, Queue<int[]> queue, int m, int n, boolean[][] visited) {
        if (c[0] < m && c[0] >= 0 && c[1] + 1 < n) {
            char next = maze[c[0]][c[1] + 1];
            boolean last = visited[c[0]][c[1] + 1];
            if (last) return true;
            if (next == '7') {
                queue.offer(new int[]{c[0], c[1] + 1, SOUTH});
                visited[c[0]][c[1] + 1] = true;
            } else if (next == 'J') {
                queue.offer(new int[]{c[0], c[1] + 1, NORTH});
                visited[c[0]][c[1] + 1] = true;
            } else if (next == '-') {
                queue.offer(new int[]{c[0], c[1] + 1, EAST});
                visited[c[0]][c[1] + 1] = true;
            }
        }
        return false;
    }

    private static boolean goWEST(char[][] maze, int[] c, Queue<int[]> queue, int m, int n, boolean[][] visited) {
        if (c[0] < m && c[0] >= 0 && c[1] - 1 >= 0) {
            char next = maze[c[0]][c[1] - 1];
            boolean last = visited[c[0]][c[1] - 1];
            if (last) return true;
            if (next == 'F') {
                queue.offer(new int[]{c[0], c[1] - 1, SOUTH});
                visited[c[0]][c[1] - 1] = true;
            } else if (next == 'L') {
                queue.offer(new int[]{c[0], c[1] - 1, NORTH});
                visited[c[0]][c[1] - 1] = true;
            } else if (next == '-') {
                queue.offer(new int[]{c[0], c[1] - 1, WEST});
                visited[c[0]][c[1] - 1] = true;
            }
        }
        return false;
    }
}
