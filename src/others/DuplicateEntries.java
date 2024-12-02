package others;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class DuplicateEntries {
    public static void main(String[] args) {
        final List<Integer> input = new LinkedList<>();
        input.add(1);
        input.add(2);
        input.add(3);
        input.add(3);
        input.add(4);
        input.add(5);

        final List<Integer> result = getDuplicateEntries(input);
        for (int r : result) {
            System.out.println(r);
        }
    }

    private static List<Integer> getDuplicateEntries(List<Integer> input) {

        final Set<Integer> set = new HashSet<>();
        final List<Integer> results = new LinkedList<>();
        for (int e : input) {
            if (set.contains(e)) {
                results.add(e);
            } else {
                set.add(e);
            }
        }

        return results;
    }
}
