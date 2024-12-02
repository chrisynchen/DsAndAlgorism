package advent_of_code.year_2023;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day5Part1 {
    public static void main(String[] args) {
        Path path = Paths.get("src/advent_of_code/year_2023/test_data/day5part1.txt");
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
        final Map<String, List<long[]>> map = extractViaMap(examples);
        final long[] seeds = getSeeds(examples[0]);
        long result = Long.MAX_VALUE;
        for (long seed : seeds) {
            seed = cal(map, "seed-to-soil", seed);
            seed = cal(map, "soil-to-fertilizer", seed);
            seed = cal(map, "fertilizer-to-water", seed);
            seed = cal(map, "water-to-light", seed);
            seed = cal(map, "light-to-temperature", seed);
            seed = cal(map, "temperature-to-humidity", seed);
            seed = cal(map, "humidity-to-location", seed);
            result = Math.min(result, seed);
        }
        return result;
    }

    private static long cal(final Map<String, List<long[]>> map, String tag, Long seed) {
        for (long[] list : map.get(tag)) {
            long source = list[1];
            long dest = list[0];
            long range = list[2];

            if (isInRange(seed, source, range)) {
                seed = dest + seed - source;
                break;
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
    private static Map<String, List<long[]>> extractViaMap(String[] examples) {
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
            } else {
                final String[] s = examples[index].split(" ");
                long[] temp = new long[s.length];
                for (int i = 0; i < s.length; i++) {
                    temp[i] = Long.parseLong(s[i].trim());
                }
                map.get(tag).add(temp);
            }
            index++;
        }

        return map;
    }
}
