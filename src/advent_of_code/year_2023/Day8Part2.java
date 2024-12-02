package advent_of_code.year_2023;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day8Part2 {
    public static void main(String[] args) {
        Path path = Paths.get("src/advent_of_code/year_2023/test_data/day8part2.txt");
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

    private static long solve(String[] examples) {
        char[] steps = examples[0].toCharArray();
        int n = steps.length;

        //tag vs next [left, right]
        Map<String, String[]> map = new HashMap<>();
        List<String> tags = new ArrayList<>();

        for (int i = 2; i < examples.length; i++) {
            String from = examples[i].substring(0, 3);
            String left = examples[i].substring(7, 10);
            String right = examples[i].substring(12, 15);
            map.put(from, new String[]{left, right});
            if (from.charAt(2) == 'A') {
                tags.add(from);
            }
        }

        return cal(map, tags, n, steps);
    }

    private static long cal(Map<String, String[]> map, List<String> tags, int n, char[] steps) {
        long[] result = new long[tags.size()];
        for (int i = 0; i < tags.size(); i++) {
            String t = tags.get(i);
            int l = count(map, t, n, steps);
            result[i] = l;
        }

//        lcm
        return lcmArr(result, 0, result.length);
    }

    private static long lcmArr(long[] arr, int start, int end) {
        if (start >= end) return 1;
        return lcm(arr[start], lcmArr(arr, start + 1, end));
    }

    private static long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }

    private static long gcd(long a, long b) {
        long max = Math.max(a, b);
        long min = Math.min(a, b);

        while (min > 0) {
            long temp = max % min;
            max = min;
            min = temp;
        }
        return max;
    }

    private static int count(Map<String, String[]> map, String current, int n, char[] steps) {
        int result = 0;
        int index = 0;
        String c = current;
        while (c.charAt(2) != 'Z') {
            index %= n;
            char currentStep = steps[index++];
            c = map.get(c)[currentStep == 'L' ? 0 : 1];
            result++;
        }

        return result;
    }
}
