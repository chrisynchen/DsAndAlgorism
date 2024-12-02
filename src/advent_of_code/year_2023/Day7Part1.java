package advent_of_code.year_2023;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Day7Part1 {
    public static void main(String[] args) {
        Path path = Paths.get("src/advent_of_code/year_2023/test_data/day7part1.txt");
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

    public static String solve(String[] examples) {
        long answer = 0;
        List<Hand> hands = new ArrayList<>();
        for (String e : examples) {
            hands.add(new Hand(e.split(" ")[0], Integer.parseInt(e.split(" ")[1])));
        }
        Collections.sort(hands);
        for (int i = 0; i < hands.size(); i++) {
//            System.out.println(hands.get(i).bid);
            answer += hands.get(i).bid * (i + 1);
        }
        return answer + "";
    }
}

class Hand implements Comparable {
    static final char[] ranks = new char[]{'A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2'};
    int bid;
    int strength;
    int[] freqs = new int[13];
    int[] cards = new int[5];

    public Hand(String s, int bid) {
        this.bid = bid;
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < ranks.length; j++) {
                if (s.charAt(i) == ranks[j]) {
                    freqs[j]++;
                    cards[i] = j;
                }
            }
        }
        for(int e: cards) {
            System.out.println("bid:" + bid + "e:" + e);
        }
        Arrays.sort(freqs);
        strength = 2 * freqs[freqs.length - 1];
        if (freqs[freqs.length - 2] == 2) {
            strength += 1; //for full house and two pair
        }
    }

    @Override
    public int compareTo(Object o) {
        Hand other = (Hand) o;
        if (strength != other.strength) {
            return strength - other.strength;
        } else {
            for (int i = 0; i < cards.length; i++) {
                if (cards[i] != other.cards[i]) {
                    System.out.println("i:" + i + ", bid:" + bid + ", " + cards[i] + ", other:" + other.cards[i]);
                    return other.cards[i] - cards[i];
                }
            }
            return 0;
        }
    }
}
