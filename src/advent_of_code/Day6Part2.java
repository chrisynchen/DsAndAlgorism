package advent_of_code;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Day6Part2 {
    public static void main(String[] args) {
        Path path = Paths.get("src/advent_of_code/test_data/day6part2.txt");
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

        StringBuilder timesSb = new StringBuilder();
        StringBuilder distsSb = new StringBuilder();

        for(String s: timePartitions) {
            String temp = s.trim();
            if(temp.isEmpty()) continue;
            timesSb.append(temp);
        }

        for(String s: distPartitions) {
            String temp = s.trim();
            if(temp.isEmpty()) continue;
            distsSb.append(temp);
        }

        long time = Long.parseLong(timesSb.toString());
        long distance = Long.parseLong(distsSb.toString());

        long l = 0;
        long r = time;

        while(l < r) {
            if(l * r > distance) {
                break;
            }
            l++;
            r--;
        }
        // no any one can achieve
        if(l * r <= distance) return 0;

        return r - l + 1;
    }
}
