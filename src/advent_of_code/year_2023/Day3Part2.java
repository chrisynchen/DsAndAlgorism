package advent_of_code.year_2023;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Day3Part2 {
    public static void main(String[] args) {
        Path path = Paths.get("src/advent_of_code/year_2023/test_data/day3part2.txt");
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
        int m = examples.length;
        int n = examples[0].length();
        //count, product
        final Map<Integer, TreeMap<Integer, int[]>> map = new HashMap<>();

        for (int i = 0; i < m; i++) {
            final String s = examples[i];
            map.putIfAbsent(i, new TreeMap<>());
            for (int j = 0; j < n; j++) {
                char c = s.charAt(j);
                if (c == '*') {
                    map.get(i).put(j, new int[]{0, 1});
                }
            }
        }
        long result = 0;
        for (int i = 0; i < m; i++) {
            final String s = examples[i];
            int prevIndex = -1;
            int sum = 0;
            for (int j = 0; j < n; j++) {
                char c = s.charAt(j);
                if (c - '0' >= 0 && c - '0' <= 9) {
                    sum *= 10;
                    sum += c - '0';
                } else {
                    if (sum > 0) {
                        setIfPossible(map, m, i, j, prevIndex, sum);
                    }
                    sum = 0;
                    prevIndex = j;
                }
            }

            if (sum > 0) {
                setIfPossible(map, m, i, n, prevIndex, sum);
            }
        }
        //Map<Integer, TreeMap<Integer, int[]>>
        for(Map.Entry<Integer, TreeMap<Integer, int[]>> e1: map.entrySet()) {
            for(Map.Entry<Integer, int[]> e2: e1.getValue().entrySet()) {
                int[] v = e2.getValue();
                if(v[0] == 2) {
                    result += v[1];
                }
            }
        }

        return result;
    }

    private static void setIfPossible(final Map<Integer, TreeMap<Integer, int[]>> map, int m, int i, int j, int prevIndex, int sum) {
        if (i > 0) {
            TreeMap<Integer, int[]> tempMap = map.get(i - 1);
            int index = j;
            while(tempMap.floorKey(index) != null && prevIndex <= tempMap.floorKey(index)) {
                int pp = tempMap.floorKey(index);
                int[] c = tempMap.get(pp);
                c[0]++;
                c[1] *= sum;
                index = pp - 1;
            }
        }

        if (i + 1 < m) {
            TreeMap<Integer, int[]> tempMap = map.get(i + 1);
            int index = j;
            while(tempMap.floorKey(index) != null && prevIndex <= tempMap.floorKey(index)) {
                int pp = tempMap.floorKey(index);
                int[] c = tempMap.get(pp);
                c[0]++;
                c[1] *= sum;
                index = pp - 1;
            }
        }
        TreeMap<Integer, int[]> tempMap = map.get(i);
        int index = j;
        while(tempMap.floorKey(index) != null && prevIndex <= tempMap.floorKey(index)) {
            int pp = tempMap.floorKey(index);
            int[] c = tempMap.get(pp);
            c[0]++;
            c[1] *= sum;
            index = pp - 1;
        }
    }
}
