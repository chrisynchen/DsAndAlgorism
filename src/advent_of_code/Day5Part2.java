package advent_of_code;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Day5Part2 {
    public static void main(String[] args) {
        Path path = Paths.get("src/advent_of_code/test_data/day5part2.txt");
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
        final Map<String, long[]> boundaryMap = new HashMap<>();
        final Map<String, List<long[]>> map = extractViaMap(examples, boundaryMap);
        final long[] seeds = getSeeds(examples[0]);
        long result = Long.MAX_VALUE;
        for (int i = 1; i < seeds.length; i += 2) {
            for (long j = seeds[i - 1]; j < seeds[i - 1] + seeds[i]; j++) {
                long seed = j;
                seed = cal(map, "seed-to-soil", seed, boundaryMap);
                seed = cal(map, "soil-to-fertilizer", seed, boundaryMap);
                seed = cal(map, "fertilizer-to-water", seed, boundaryMap);
                seed = cal(map, "water-to-light", seed, boundaryMap);
                seed = cal(map, "light-to-temperature", seed, boundaryMap);
                seed = cal(map, "temperature-to-humidity", seed, boundaryMap);
                seed = cal(map, "humidity-to-location", seed, boundaryMap);
                result = Math.min(result, seed);
            }
        }
        return result;
    }

    private static long cal(final Map<String, List<long[]>> map, String tag, long seed, Map<String, long[]> boundaryMap) {
        long[] boundary = boundaryMap.get(tag);
        if (boundary[0] > seed || seed > boundary[1]) return seed;
        List<long[]> list = map.get(tag);
        int l = 0;
        int r = list.size() - 1;

        while (l <= r) {
            int mid = l + r >> 1;
            long[] current = list.get(mid);
            long source = current[1];
            long dest = current[0];
            long range = current[2];
            if (seed >= source && seed < source + range) {
                return dest + seed - source;
            } else if (seed < source) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return seed;
    }

    private static boolean isInRange(long target, long source, long range) {
        return target >= source && target < source + range;
    }

    private static long[] getSeeds(final String firstLine) {
        String newString = firstLine.replace("seeds: ", "");
        String[] seeds = newString.split(" ");
        long[] temp = new long[seeds.length];
        for (int i = 0; i < seeds.length; i++) {
            temp[i] = Long.parseLong(seeds[i].trim());
        }
        return temp;
    }

    //tag vs List<long[destination, source, range length]>
    private static Map<String, List<long[]>> extractViaMap(String[] examples, final Map<String, long[]> boundaryMap) {
        final Map<String, List<long[]>> map = new HashMap<>();
        //start from 2
        int index = 2;
        String tag = "";
        while (index < examples.length) {
            if (examples[index].isEmpty()) {
                tag = "";
                index++;
                continue;
            }

            if (examples[index].contains(" map:")) {
                tag = examples[index].replace(" map:", "");
                map.put(tag, new ArrayList<>());
                boundaryMap.put(tag, new long[]{Long.MAX_VALUE, Long.MIN_VALUE});
            } else {
                final String[] s = examples[index].split(" ");
                long[] temp = new long[s.length];
                for (int i = 0; i < s.length; i++) {
                    temp[i] = Long.parseLong(s[i].trim());
                }
                long[] boundary = boundaryMap.get(tag);
                boundary[0] = Math.min(boundary[0], temp[1]);
                //inclusive
                boundary[1] = Math.max(boundary[1], temp[1] + temp[2] - 1);
                map.get(tag).add(temp);
            }
            index++;
        }

        for (List<long[]> e : map.values()) {
            Collections.sort(e, Comparator.comparingLong(a -> a[1]));
        }

        return map;
    }
}
