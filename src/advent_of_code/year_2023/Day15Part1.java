package advent_of_code.year_2023;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Day15Part1 {
    public static void main(String[] args) {
        Path path = Paths.get("src/advent_of_code/year_2023/test_data/day15part1.txt");
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
        String[] steps = examples[0].split(",");
        long result = 0;
        for(String s: steps) {
            long temp = 0;
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                temp += c;
                temp *= 17;
                temp %= 256;
            }
            result += temp;
        }

        return result;
    }
}
