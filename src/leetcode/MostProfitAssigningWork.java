package leetcode;

import java.util.TreeMap;

/**
 * 826. Most Profit Assigning Work
 * Medium
 * We have jobs: difficulty[i] is the difficulty of the ith job, and profit[i] is the profit of the ith job.
 * <p>
 * Now we have some workers. worker[i] is the ability of the ith worker, which means that this worker can only complete a job with difficulty at most worker[i].
 * <p>
 * Every worker can be assigned at most one job, but one job can be completed multiple times.
 * <p>
 * For example, if 3 people attempt the same job that pays $1, then the total profit will be $3.  If a worker cannot complete any job, his profit is $0.
 * <p>
 * What is the most profit we can make?
 * <p>
 * Example 1:
 * <p>
 * Input: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
 * Output: 100
 * Explanation: Workers are assigned jobs of difficulty [4,4,6,6] and they get profit of [20,20,30,30] separately.
 * Notes:
 * <p>
 * 1 <= difficulty.length = profit.length <= 10000
 * 1 <= worker.length <= 10000
 * difficult
 */
public class MostProfitAssigningWork {

    public static void main(String[] args) {
//        System.out.println(maxProfitAssignment(new int[]{85,47,57}, new int[]{24,66,99}, new int[]{40,25,25}));
//        System.out.println(maxProfitAssignment(new int[]{2,4,6,8,10}, new int[]{10,20,30,40,50}, new int[]{4,5,6,7}));

        //324
        System.out.println(maxProfitAssignment(new int[]{68,35,52,47,86}, new int[]{67,17,1,81,3}, new int[]{92,10,85,84,82}));
    }

    //Good method with Time complexity O(JLogJ+WLogJ), J number of jobs, W number of workers.
    private static int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        final TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        // To prevent same difficulty with different profit. We choose the higher one.
        for (int i = 0; i < difficulty.length; i++) {
            final int currentProfit = treeMap.get(difficulty[i]) == null ? 0 : treeMap.get(difficulty[i]);
            treeMap.put(difficulty[i], Math.max(currentProfit, profit[i]));
        }

        int currentMaxProfit = 0;
        for (int key : treeMap.keySet()) {
            treeMap.put(key, Math.max(treeMap.get(key), currentMaxProfit));
            currentMaxProfit = treeMap.get(key);
        }

        int result = 0;
        for (int i = 0; i <worker.length; i++) {
            if(treeMap.floorEntry(worker[i]) != null) {
                result += treeMap.floorEntry(worker[i]).getValue();
            }
        }

        return result;
    }


    //Bad method with Time complexity O(n^2) and space O(m)
    public int maxProfitAssignmentBad(int[] difficulty, int[] profit, int[] worker) {
        int[] workerMaxProfit = new int[worker.length];

        for (int i = 0; i < worker.length; i++) {
            for (int j = 0; j < difficulty.length; j++) {
                if (worker[i] >= difficulty[j]) {
                    if (workerMaxProfit[i] < profit[j]) {
                        workerMaxProfit[i] = profit[j];
                    }
                }
            }
        }

        int maxProfit = 0;

        for (int i = 0; i < workerMaxProfit.length; i++) {
            maxProfit += workerMaxProfit[i];
        }

        return maxProfit;
    }
}
