package google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoinChange {

    //    Main question was simple coin change. Given a set of coins, output number of ways you can make from [1 to sum].
    //    Given coins = [2, 5, 6] and target 10, return output [1 0 1 0 1 1 2 1 2 1 3].
    public static void main(String[] args) {
//        int[] result = coinChange(new int[]{2,5,6}, 10);
        //[2,2,2,2,2],[2,4,4],[8,2][2,2,2,4]
        int[] result = coinChange(new int[]{2, 3, 6, 8, 12, 18}, 100);
        final List<Integer> coins = convertBackToCoin(result);
        for (int e : coins) {
            System.out.println(e);
        }
    }

    private static int[] coinChange(int[] coins, int target) {
        int[] dp = new int[target + 1];
        Arrays.sort(coins);
        dp[0] = 1;

        for (int e : coins) {
            for (int i = e; i <= target; i++) {
                dp[i] += dp[i - e];
            }
        }
        return dp;
    }


    //    Follow up was given the output like [1 0 1 0 1 1 2 1 2 1 3] return the possible set of coins. if it's not possible return -1.
    //O(n^2logn)
    private static List<Integer> convertBackToCoin(int[] dp) {
        List<Integer> list = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        // 0 1 2 3 4 5 6 7 8 9 10
        //[1 0 1 1 1 1 2 1 2 2 2]
        //[0 0 0 0 0 0 0 0   0 0]
        //found:2 [2] + [4,6,8,10]
        //[0 0 0 0 0 1 1 1 1 1 2]
        //found 5 [5] + [7,9]
        //[0 0 0 0 0 0 1 0 1 0 1]
        //found 6 [6] + [8,10]
        int sum = 0;
        for (int e : dp) {
            sum += e;
        }
        sum -= 1;
        dp[0] = 0;

        //O(n^logn)
        for (int i = 1; i < dp.length; i++) {
            if (dp[i] == 0) continue;
            result.add(i);
            List<Integer> temp = new ArrayList<>(list);
            //n/2 + n/3 + n/4 + ... ~= nlogn
            for (int e : list) {
                int current = i;
                while (current + e < dp.length) {
                    if (dp[current + e] > 0) {
                        temp.add(current + e);
                        dp[current + e]--;
                        sum--;
                    }
                    current += i;
                }
            }
            // (1 + 1/2 + 1/3 +... + 1/n)n ~= nlogn
            for (int j = i; j < dp.length; j += i) {
                temp.add(j);
                dp[j]--;
                sum--;
            }
            if (sum == 0) break;
            list = temp;
        }

        return result;
    }
}
