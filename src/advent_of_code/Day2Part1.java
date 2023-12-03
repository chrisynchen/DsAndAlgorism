package advent_of_code;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Day2Part1 {
    public static void main(String[] args) {
        Path path = Paths.get("src/advent_of_code/day2part1.txt");
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
        for(String s: examples) {
            String[] part1 = s.split(":");
            int id = Integer.parseInt(String.valueOf(part1[0].split(" ")[1]));
            String[] part2 = part1[1].split(";");
            boolean valid = true;
            for(String p: part2) {
                String[] colorsParts = p.split(",");
                for(String colorsPart: colorsParts) {
                    String[] cp = colorsPart.trim().split(" ");
                    int count = Integer.parseInt(String.valueOf(cp[0].split(" ")[0]));
                    String color = cp[1];
                    if(color.equals("red")) {
                        if(count > 12) {
                            valid = false;
                        }
                    } else if(color.equals("green")) {
                        if(count > 13) {
                            valid = false;
                        }
                    } else {
                        //blue
                        if(count > 14) {
                            valid = false;
                        }
                    }
                }
            }
            if(valid) {
                result += id;
            }
        }
        return result;
    }
}
