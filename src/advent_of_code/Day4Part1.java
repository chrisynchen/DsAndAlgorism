package advent_of_code;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Day4Part1 {
    public static void main(String[] args) {
        Path path = Paths.get("src/advent_of_code/test_data/day4part1.txt");
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

        for(String example: examples) {
            String[] cards = example.split(":");
            String[] partition = cards[1].split("\\|");
            String[] answer = partition[0].trim().split(" ");
            final Set<String> set = new HashSet<>(Arrays.asList(answer));
            String[] guess = partition[1].trim().split(" ");

            long point = 0;
            for(String s: guess) {
                if(s.isEmpty()) continue;
                String temp = s.trim();
                if(set.contains(temp)) {
                    point++;
                    set.remove(temp);
                }
            }
            if(point > 0) {
                result += 1 << (point - 1);
            }
        }

        return result;
    }
}
