package google;

import java.util.PriorityQueue;

public class ValidTask {
    //    one thread and sorted schedule [a, b] a = start time, b = duration
//    check the program can insert to run on the thread
//    ex: schedule = [[1, 5], [8, 3]], program = [3,2]
//
    public static void main(String[] args) {
        int[][] schedules = new int[][]{{1, 4}, {8, 3}};
        int[] newSchedule = new int[]{11, 10};

//        System.out.println(isValidTask(schedules, newSchedule));
        int[][] schedules2 = new int[][]{{1, 6}, {4, 8}, {5, 3}};
        int[] newSchedule2 = new int[]{2, 3};
        System.out.println(isMultipleProcessValidTask2(schedules2, newSchedule2, 3));
    }

    //O(logN)
    private static boolean isValidTask(int[][] schedules, int[] newSchedule) {
        int l = 0;
        int r = schedules.length - 1;

        while (l <= r) {
            int mid = l + r >> 1;

            int start = schedules[mid][0];
            int end = start + schedules[mid][1];
            if (start >= newSchedule[0] + newSchedule[1]) {
                r = mid - 1;
            } else if (end <= newSchedule[0]) {
                l = mid + 1;
            } else {
                return false;
            }
        }

        return true;
    }

    //assume max is 1000, O(N)
    private static boolean isMultipleProcessValidTask(int[][] schedules, int[] newSchedule, int k) {
        int[] array = new int[1000 + 1];
        for (int[] e : schedules) {
            int start = e[0];
            int end = start + e[1];
            array[start]++;
            array[end]--;
        }

        array[newSchedule[0]]++;
        array[newSchedule[0] + newSchedule[1]]--;

        int check = 0;
        for (int e : array) {
            check += e;
            if (check > k) return false;
        }

        return true;
    }

    //assume max exceed Integer.MAX_VALUE, O(NlogN)
    //0 start, 1 end
    private static boolean isMultipleProcessValidTask2(int[][] schedules, int[] newSchedule, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if(a[1] == b[1]) {
                return b[0] - a[0];
            } else {
                return a[1] - b[1];
            }
        });

        for(int[] e: schedules) {
            int start = e[0];
            int end = start + e[1];
            pq.offer(new int[]{0, start});
            pq.offer(new int[]{1, end});
        }
        pq.offer(new int[]{0, newSchedule[0]});
        pq.offer(new int[]{1, newSchedule[1]});

        int current = 0;
        while(!pq.isEmpty()) {
            int[] e = pq.poll();
            if(e[0] == 0) {
                current++;
            } else {
                current--;
            }

            if(current > k) return false;
        }

        return true;
    }
}
