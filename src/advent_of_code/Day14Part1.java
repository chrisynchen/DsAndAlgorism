package advent_of_code;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Day14Part1 {
    public static void main(String[] args) {
        Path path = Paths.get("src/advent_of_code/test_data/day14part1.txt");
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

    private static long solve(String[] examples) {
        long result = 0;
        int m = examples.length;
        int n = examples[0].length();
        char[][] platform = new char[m][n];

        for(int i = 0; i < examples.length; i++) {
            String s = examples[i];
            for(int j = 0; j < s.length(); j++) {
                platform[i][j] = s.charAt(j);
            }
        }

        //formula = (length to south - 1) * count - count * (count - 1) / 2
        for(int j = 0; j < n; j++) {
            int count = 0;
            int colSum = 0;
            for(int i = m - 1; i >= 0; i--) {
                if(platform[i][j] == 'O') {
                    count++;
                }

                if(platform[i][j] == '#' || i == 0) {
                    int start = platform[i][j] == '#' ? i : -1;
                    if(count > 0) {
                        int temp = (m - start - 1) * count - count * (count - 1) / 2;
                        colSum += temp;
                    }
                    count = 0;
                }
            }
            result += colSum;
        }

        return result;
    }
}
