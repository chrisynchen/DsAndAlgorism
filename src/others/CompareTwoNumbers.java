package others;

import java.util.*;

public class CompareTwoNumbers {
    public static void main(String[] args) {
        final List<int[]> result1 = cal(new int[][]{{2, 3}, {3, 7}, {2, 8}, {4, 5}, {4, 8}, {4, 8}});
        for (int[] r : result1) {
            System.out.println("first:" + r[0] + ", second:" + r[1]);
        }
    }

    // two dimension array
    // [[2,3], [3,7], [2, 8], [4, 5], [4, 8], [4, 8]]
    // result: [4,8], size = 1

    //[[2,8], [3,7], [5,5]]
    // result = [2,8], [3,7], [5,5], size = 3
    private static List<int[]> cal(int[][] input) {

        final Set<Integer> set = new HashSet<>();
        //[exam1 value, index]
        final PriorityQueue<int[]> pq1 = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        //[exam2 value, index]
        final PriorityQueue<int[]> pq2 = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));


        for (int i = 0; i < input.length; i++) {
            int score1 = input[i][0];
            int score2 = input[i][1];

            while (!pq1.isEmpty() && (set.contains(pq1.peek()[1]) ||
                    input[pq1.peek()[1]][0] <= score1 && input[pq1.peek()[1]][1] <= score2)) {
                set.add(pq1.poll()[1]);
            }

            while (!pq2.isEmpty() && (set.contains(pq2.peek()[1]) ||
                    input[pq2.peek()[1]][0] <= score1 && input[pq2.peek()[1]][1] <= score2)) {
                set.add(pq2.poll()[1]);
            }

            if (!pq1.isEmpty() && input[pq1.peek()[1]][0] >= score1 && input[pq1.peek()[1]][1] >= score2) {
                continue;
            }

            if (!pq2.isEmpty() && input[pq2.peek()[1]][0] >= score1 && input[pq2.peek()[1]][1] >= score2) {
                continue;
            }

            pq1.offer(new int[]{score1, i});
            pq2.offer(new int[]{score2, i});
        }

        final List<int[]> result = new LinkedList<>();
        while (!pq1.isEmpty()) {
            int index = pq1.poll()[1];
            if (set.contains(index)) continue;
            result.add(input[index]);
        }
        return result;
    }
}
