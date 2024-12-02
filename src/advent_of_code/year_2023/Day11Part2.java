package advent_of_code.year_2023;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day11Part2 {
    public static void main(String[] args) {
        Path path = Paths.get("src/advent_of_code/year_2023/test_data/day11part2.txt");
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
        int m = examples.length;
        int n = examples[0].length();
        final List<int[]> galaxies = new ArrayList<>();
        boolean[] rows = new boolean[m];
        boolean[] cols = new boolean[n];
        long[] rowPreSum = new long[m];
        long[] colPreSum = new long[n];
        for (int i = 0; i < m; i++) {
            String s = examples[i];
            for (int j = 0; j < n; j++) {
                if (s.charAt(j) == '#') {
                    galaxies.add(new int[]{i, j});
                    rows[i] = true;
                    cols[j] = true;
                }
            }
        }

        for(int i = 0; i < m; i++) {
            if(!rows[i]) {
                rowPreSum[i] = 1;
            }
            if(i > 0) rowPreSum[i] += rowPreSum[i - 1];
        }
        for(int i = 0; i < n; i++) {
            if(!cols[i]) {
                colPreSum[i] = 1;
            }
            if(i > 0) colPreSum[i] += colPreSum[i - 1];
        }

        long result = 0;
        for(int i = 0; i < galaxies.size(); i++) {
            int[] c1 = galaxies.get(i);
            for(int j = i + 1; j < galaxies.size(); j++) {
                int[] c2 = galaxies.get(j);
                long iFlat = Math.abs(rowPreSum[c1[0]] -  rowPreSum[c2[0]]);
                long jFlat = Math.abs(colPreSum[c1[1]] -  colPreSum[c2[1]]);
                result += Math.abs(c1[0] - c2[0]) - iFlat + Math.abs(c1[1] - c2[1]) - jFlat + (iFlat +jFlat) * 1000000;
            }
        }

        return result;
    }
    //9166712
}
