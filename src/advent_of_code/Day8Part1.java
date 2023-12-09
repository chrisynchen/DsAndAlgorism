package advent_of_code;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Day8Part1 {
    public static void main(String[] args) {
        Path path = Paths.get("src/advent_of_code/test_data/day8part1.txt");
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

        for(int i = 2; i < examples.length; i++) {
            String from = examples[i].substring(0, 3);
            String left = examples[i].substring(7, 10);
            String right = examples[i].substring(12, 15);
            map.put(from, new String[]{left, right});
        }
        long result = 0;
        String current = "AAA";
        int index = 0;
        while(!"ZZZ".equals(current)) {
            index %= n;
            char currentStep = steps[index++];
            current = map.get(current)[currentStep == 'L' ? 0 : 1];
            result++;
        }

        return result;
    }
}
