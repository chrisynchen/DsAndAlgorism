package advent_of_code.year_2024;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Day2Part2 {
    public static void main(String[] args) {
        Path path = Paths.get("src/advent_of_code/year_2024/test_data/day2part2.txt");
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
        for (String s : examples) {
            final String[] sp = s.split(" ");
            final int[] temp = new int[sp.length];
            for (int i = 0; i < sp.length; i++) {
                temp[i] = Integer.parseInt(sp[i]);
            }
            boolean valid = validate(temp);
            if (valid) {
                result++;
            } else {
                //try remove one element
                for (int j = 0; j < temp.length; j++) {
                    int[] cutItems = new int[temp.length - 1];
                    int index = 0;
                    int cIndex = 0;
                    while (index < cutItems.length) {
                        if (cIndex == j) {
                            cIndex++;
                            continue;
                        }
                        cutItems[index] = temp[cIndex];
                        cIndex++;
                        index++;
                    }
                    boolean v = validate(cutItems);
                    if (v) {
                        result++;
                        break;
                    }
                }
            }
        }
        return result;
    }

    private static boolean validate(int[] temp) {
        int firstDiff = temp[1] - temp[0];
        int firstAbs = Math.abs(firstDiff);
        if (firstAbs == 0 || firstAbs > 3) return false;
        boolean valid = true;
        final boolean firstIncreasing = firstDiff > 0;
        for (int i = 2; i < temp.length; i++) {
            int diff = temp[i] - temp[i - 1];
            int abs = Math.abs(diff);
            final boolean increasing = diff > 0;
            if (increasing != firstIncreasing || abs == 0 || abs > 3) {
                valid = false;
                break;
            }
        }
        return valid;
    }
}
