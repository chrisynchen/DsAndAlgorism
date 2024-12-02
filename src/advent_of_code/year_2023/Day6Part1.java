package advent_of_code.year_2023;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Day6Part1 {
    public static void main(String[] args) {
        Path path = Paths.get("src/advent_of_code/year_2023/test_data/day6part1.txt");
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
        final String[] timePartitions = examples[0].split(":")[1].split(" ");
        final String[] distPartitions = examples[1].split(":")[1].split(" ");

        List<Integer> times = new ArrayList<>();
        List<Integer> dists = new ArrayList<>();

        for(String s: timePartitions) {
            String temp = s.trim();
            if(temp.isEmpty()) continue;
            times.add(Integer.valueOf(temp));
        }

        for(String s: distPartitions) {
            String temp = s.trim();
            if(temp.isEmpty()) continue;
            dists.add(Integer.valueOf(temp));
        }

        int result = 1;
        for(int i = 0; i < times.size(); i++) {
            int time = times.get(i);
            int distance = dists.get(i);

            int l = 0;
            int r = time;

            while(l < r) {
                if(l * r > distance) {
                    break;
                }
                l++;
                r--;
            }
            // no any one can achieve
            if(l * r <= distance) continue;

            result *= r - l + 1;
        }
        return result;
    }
}
