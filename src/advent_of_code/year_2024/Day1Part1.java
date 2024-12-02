package advent_of_code.year_2024;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day1Part1 {
    public static void main(String[] args) {
        Path path = Paths.get("src/advent_of_code/year_2024/test_data/day1part1.txt");
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
        final List<Integer> left = new ArrayList<>();
        final List<Integer> right = new ArrayList<>();
        for(String s: examples) {
            final String[] sp = s.split("   ");
            left.add(Integer.valueOf(sp[0]));
            right.add(Integer.valueOf(sp[1]));
        }
        Collections.sort(left);
        Collections.sort(right);
        for(int i = 0; i < left.size(); i++) {
            result += Math.abs(left.get(i) - right.get(i));
        }
        return result;
    }
}

