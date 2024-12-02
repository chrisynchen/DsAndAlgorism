package advent_of_code.year_2024;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day1Part2 {
    public static void main(String[] args) {
        Path path = Paths.get("src/advent_of_code/year_2024/test_data/day1part2.txt");
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
        final Map<Integer, Integer> map = new HashMap<>();
        final List<Integer> left = new ArrayList<>();
        for (String s : examples) {
            final String[] sp = s.split("   ");
            int r = Integer.parseInt(sp[1]);
            left.add(Integer.valueOf(sp[0]));
            map.put(r, map.getOrDefault(r, 0) + 1);
        }

        for(int e: left) {
            result += e * map.getOrDefault(e, 0);
        }

        return result;
    }
}

