package advent_of_code.year_2023;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Day9Part2 {
    public static void main(String[] args) {
        Path path = Paths.get("src/advent_of_code/year_2023/test_data/day9part2.txt");
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
        long result = 0;
        for(String line: examples) {
            String[] numbers = line.split(" ");
            List<long[]> list = new ArrayList<>();
            long[] temp = new long[numbers.length];
            long sum = 0;
            for(int i = 0; i < numbers.length; i++) {
                long c = Long.parseLong(numbers[i]);
                temp[i] = c;
                sum += c;
            }
            list.add(temp);

            while(sum != 0) {
                long[] prevArray = list.get(list.size() - 1);
                long[] tempArray = new long[prevArray.length - 1];
                long tempSum = 0;
                for(int i = 1; i < prevArray.length; i++) {
                    long diff = prevArray[i] - prevArray[i - 1];
                    tempArray[i - 1] = diff;
                    tempSum += diff;
                }
                list.add(tempArray);
                sum = tempSum;
            }

            int index = list.size() - 2;
            long l = 0;
            while(index >= 0) {
                long[] t = list.get(index);
                l = t[0] - l;
                index--;
            }
            result += l;
        }

        return result;
    }
}
