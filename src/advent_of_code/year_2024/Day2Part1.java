package advent_of_code.year_2024;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Day2Part1 {
    public static void main(String[] args) {
        Path path = Paths.get("src/advent_of_code/year_2024/test_data/day2part1.txt");
        String[] arr = null;
        try {
            final List<String> lines = Files.readAllLines(path, Charset.defaultCharset());
            arr = lines.toArray(new String[lines.size()]);
            System.out.println(solve(arr));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private static long solve(String[] examples) {
        long result = 0;
        for(String s: examples) {
            final String[] sp = s.split(" ");
            final int[] temp = new int[sp.length];
            for (int i = 0; i < sp.length; i++) {
                temp[i] = Integer.parseInt(sp[i]);
            }
            int firstDiff = temp[1] - temp[0];
            int firstAbs = Math.abs(firstDiff);
            if(firstAbs == 0 || firstAbs > 3) continue;
            boolean valid = true;
            final boolean firstIncreasing = firstDiff > 0;
            for(int i = 2; i < temp.length; i++) {
                int diff = temp[i] - temp[i - 1];
                int abs = Math.abs(diff);
                final boolean increasing = diff > 0;
                if(increasing != firstIncreasing || abs == 0 || abs > 3) {
                    valid = false;
                    break;
                }
            }
            if(valid) {
                result++;
            }
        }
        return result;
    }
}
