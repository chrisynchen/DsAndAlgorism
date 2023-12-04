package advent_of_code;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day1Part2 {
    public static void main(String[] args) {
        Path path = Paths.get("src/advent_of_code/test_data/day1part2.txt");
        String[] arr = null;
        try {
            final List<String> lines = Files.readAllLines(path, Charset.defaultCharset());
            arr = lines.toArray(new String[lines.size()]);
            Map<String, Integer> validDigits = new HashMap<>();
            validDigits.put("one", 1);
            validDigits.put("two", 2);
            validDigits.put("three", 3);
            validDigits.put("four", 4);
            validDigits.put("five", 5);
            validDigits.put("six", 6);
            validDigits.put("seven", 7);
            validDigits.put("eight", 8);
            validDigits.put("nine", 9);
            System.out.println(solve(arr, validDigits));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private static long solve(String[] examples, Map<String, Integer> validDigits) {
        long result = 0;
        for (String s : examples) {
            int l = 0;
            int r = 0;

            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if(c - '0' >= 0 && c - '0' <= 9) {
                    l = c - '0';
                    break;
                }
                boolean valid = false;
                for(int j = 0; j <= i; j++) {
                    String sub = s.substring(j, i + 1);
                    if(validDigits.containsKey(sub)) {
                        l = validDigits.get(sub);
                        valid = true;
                        break;
                    }
                }
                if(valid) break;
            }

            for(int i = s.length() - 1; i >= 0; i--) {
                char c = s.charAt(i);
                if(c - '0' >= 0 && c - '0' <= 9) {
                    r = c - '0';
                    break;
                }
                boolean valid = false;
                for(int j = i; j < s.length(); j++) {
                    String sub = s.substring(i, j + 1);
                    if(validDigits.containsKey(sub)) {
                        r = validDigits.get(sub);
                        valid = true;
                        break;
                    }
                }
                if(valid) break;
            }

            result += l * 10 + r;
        }
        return result;
    }
}
