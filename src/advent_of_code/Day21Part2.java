package advent_of_code;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Day21Part2 {
    private static final int TARGET_STEP = 26501365;
    private static final int[][] OFFSET = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) {
        Path path = Paths.get("src/advent_of_code/test_data/day21part2.txt");
        String[] arr = null;
        try {
            final List<String> lines = Files.readAllLines(path, Charset.defaultCharset());
            arr = lines.toArray(new String[lines.size()]);
            long start = System.currentTimeMillis();
            System.out.println(solve(arr));
            long runTime = System.currentTimeMillis() - start;
            System.out.println("Run Time: " + (runTime));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    //double linked list with map
    private static long solve(String[] examples) {
        int m = examples.length;
        int n = examples[0].length();
        char[][] garden = new char[m][n];
        int[] startPoint = new int[2];
        for (int i = 0; i < m; i++) {
            String s = examples[i];
            for (int j = 0; j < n; j++) {
                char c = s.charAt(j);
                garden[i][j] = c;
                if (c == 'S') {
                    startPoint[0] = i;
                    startPoint[1] = j;
                }
            }
        }

        final Map<Integer, Set<Integer>> visited = new HashMap<>();
        visited.put(startPoint[0], new HashSet<>());
        visited.get(startPoint[0]).add(startPoint[1]);
        long[] dp = new long[TARGET_STEP + 1];
        dp[0] = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startPoint[0], startPoint[1]});

        int step = 0;
        while (!queue.isEmpty() && step < TARGET_STEP) {
            int size = queue.size();
            long count = 0;
            for (int i = 0; i < size; i++) {
                int[] c = queue.poll();
                for (int[] e : OFFSET) {
                    int nextI = c[0] + e[0];
                    int nextJ = c[1] + e[1];
                    int mappingI = nextI % m;
                    int mappingJ = nextJ % m;
                    if(mappingI < 0) {
                        mappingI += m;
                    }
                    if(mappingJ < 0) {
                        mappingJ += n;
                    }
                    if ((visited.containsKey(nextI) && visited.get(nextI).contains(nextJ))
                            || garden[mappingI][mappingJ] == '#')
                        continue;
                    visited.putIfAbsent(nextI, new HashSet<>());
                    visited.get(nextI).add(nextJ);
                    queue.offer(new int[]{nextI, nextJ});
                    count++;
                }
            }
            dp[++step] = count;
            if(step - 2 >= 0) {
                dp[step] += dp[step - 2];
            }
        }

        return dp[TARGET_STEP];
    }
}

//3615
