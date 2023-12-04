package advent_of_code;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Day4Part2 {
    public static void main(String[] args) {
        Path path = Paths.get("src/advent_of_code/test_data/day4part2.txt");
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

        long[] count = new long[examples.length];
        Arrays.fill(count, 1L);
        for(int i = 0; i < examples.length; i++) {
            String example = examples[i];
            String[] cards = example.split(":");
            String[] partition = cards[1].split("\\|");
            String[] answer = partition[0].trim().split(" ");
            Set<String> set = new HashSet<>();
            for(String ans: answer) {
                if(ans.isEmpty()) continue;
                set.add(ans.trim());
            }
            String[] guess = partition[1].trim().split(" ");

            long copy = 0;
            for(String s: guess) {
                if(s.isEmpty()) continue;
                String temp = s.trim();
                if(set.contains(temp)) {
                    copy++;
                    set.remove(temp.trim());
                }
            }
            result += count[i];
            for(int j = i + 1; j <= i + copy && j < examples.length; j++) {
                count[j] += count[i];
            }
        }
        return result;
    }
}
