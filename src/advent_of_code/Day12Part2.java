package advent_of_code;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Day12Part2 {
    public static void main(String[] args) {
        Path path = Paths.get("src/advent_of_code/test_data/day12part2.txt");
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

    //dp
    private static long solve(String[] examples) {
        long result = 0;
        for (String example : examples) {
            String[] sp = example.split(" ");
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < 5; i++) {
                sb.append(sp[0]).append('?');
            }
            sb.deleteCharAt(sb.length() - 1);
            String guess = sb.toString();
            String[] ansString = sp[1].split(",");
            int[] ans = new int[ansString.length * 5];
            int[] presum = new int[guess.length()];
            for (int i = 0; i < guess.length(); i++) {
                presum[i] = guess.charAt(i) == '#' ? 1 : 0;
                if (i > 1) presum[i] += presum[i - 1];
            }

            for (int i = 0; i < ans.length; i++) {
                ans[i] = Integer.parseInt(ansString[i % ansString.length]);
            }
            long[][][] dp = new long[guess.length()][ans.length][2];
            for(long[][] e: dp) {
                for(long[] e1 : e)
                    Arrays.fill(e1, -1);
            }
            result += dfs(guess, 0, 0, ans, dp, presum, false);
        }

        return result;
    }

    private static long dfs(String guess, int i, int j, int[] ans, long[][][] dp, int[] presum, boolean mustDot) {
        if (j == ans.length) {
            return presum[presum.length - 1] - presum[i - 1] == 0 ? 1 : 0;
        }

        if (i >= guess.length()) return 0;
        int mustDotIndex = mustDot ? 1 : 0;
        if (dp[i][j][mustDotIndex] >= 0) return dp[i][j][mustDotIndex];

        char current = guess.charAt(i);

        long result = 0;
        if(current == '.') {
            result += dfs(guess, i + 1, j, ans, dp, presum, false);
        } else if(current == '?') {
            // ? = .
            result += dfs(guess, i + 1, j, ans, dp, presum, false);

            // ? = #
            if(!mustDot) {
                int c = ans[j];

                if(i + c > guess.length()) {
                    dp[i][j][mustDotIndex] = result;
                    return result;
                }

                boolean valid = true;
                for(int k = i; k < i + c; k++) {
                    char cc = guess.charAt(k);
                    if(cc == '.') {
                        valid = false;
                        break;
                    }
                }

                if(valid) {
                    result += dfs(guess, i + c, j + 1, ans, dp, presum, true);
                }
            }
        } else {
            //#
            if(mustDot) {
                dp[i][j][mustDotIndex] = result;
                return result;
            }

            int c = ans[j];

            if(i + c > guess.length()) {
                dp[i][j][mustDotIndex] = result;
                return result;
            }

            boolean valid = true;
            for(int k = i; k < i + c; k++) {
                char cc = guess.charAt(k);
                if(cc == '.') {
                    valid = false;
                    break;
                }
            }

            if(valid) {
                result += dfs(guess, i + c, j + 1, ans, dp, presum, true);
            }

        }
//        System.out.println("i:" + i + ", j:" + j + ", mustDot:" + mustDot + ", result:" + result);
        dp[i][j][mustDotIndex] = result;
        return result;
    }

    private static long countArrangements(char[] chars, boolean[] springs){
        int n = chars.length;
        int m = springs.length;
        long[][] dp = new long[n+1][m+1];
        dp[n][m] = 1;

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                boolean damaged = false, operational = false;
                switch (chars[i]){
                    case '#': {
                        damaged = true;
                        break;
                    }
                    case '.':{
                        operational = true;
                        break;
                    }
                    default:{
                        operational = true;
                        damaged = true;
                    }
                }
                long sum = 0;
                if(damaged && springs[j]){
                    sum += dp[i+1][j+1];
                } else if (operational && !springs[j]) {
                    sum += dp[i+1][j+1] + dp[i+1][j];
                }
                dp[i][j] = sum;
            }
        }
        return dp[0][0];
    }
    //38181545406 too low
}
