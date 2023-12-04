package advent_of_code;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Day1Part1 {
    public static void main(String[] args) {
        Path path = Paths.get("src/advent_of_code/test_data/day1part1.txt");
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
        for (String s : examples) {
            int l = 0;
            int r = s.length() - 1;

            while (!validNumber(s, l)) {
                l++;
            }

            while (!validNumber(s, r)) {
                r--;
            }
            if (r >= l) {
                result += (s.charAt(l) - '0') * 10 + s.charAt(r) - '0';
            }
        }
        return result;
    }

    private static boolean validNumber(String s, int i) {
        char c = s.charAt(i);
        return c - '0' >= 0 && c - '0' <= 9;
    }
}
