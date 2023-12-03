package advent_of_code;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class Day3Part1 {
    public static void main(String[] args) {
        Path path = Paths.get("src/advent_of_code/day3part1.txt");
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
        int m = examples.length;
        int n = examples[0].length();
        final Map<Integer, TreeSet<Integer>> map = new HashMap<>();

        for (int i = 0; i < m; i++) {
            final String s = examples[i];
            map.putIfAbsent(i, new TreeSet<>());
            for (int j = 0; j < n; j++) {
                char c = s.charAt(j);
                if (c == '.' || (c - '0' >= 0 && c - '0' <= 9)) continue;
                //specials
                map.get(i).add(j);
            }
        }
        long result = 0;
        for (int i = 0; i < m; i++) {
            final String s = examples[i];
            int prevIndex = -1;
            int sum = 0;
            for (int j = 0; j < n; j++) {
                char c = s.charAt(j);
                if (c - '0' >= 0 && c - '0' <= 9) {
                    sum *= 10;
                    sum += c - '0';
                } else {
                    if (sum > 0 && valid(map, n, i, j, prevIndex)) {
                        result += sum;
                    }
                    sum = 0;
                    prevIndex = j;
                }
            }

            if(sum > 0 && valid(map, n, i, n, prevIndex)) {
                result += sum;
            }
        }

        return result;
    }

    private static boolean valid(final Map<Integer, TreeSet<Integer>> map, int n, int i, int j, int prevIndex) {
        if (i > 0) {
            final Integer p = map.get(i - 1).floor(j);
            if (p != null && p >= prevIndex) return true;
        }

        if (i + 1 < n) {
            final Integer p = map.get(i + 1).floor(j);
            if (p != null && p >= prevIndex) return true;
        }
        final Integer p = map.get(i).floor(j);
        return p != null && p >= prevIndex;
    }
}
