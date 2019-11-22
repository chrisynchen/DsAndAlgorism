package leetcode.knapsack;

/**
 * Given weights and values of n items, put these items in a knapsack of capacity W to get the maximum total value in the knapsack.
 * In other words, given two integer arrays val[0..n-1] and wt[0..n-1] which represent values and weights associated with n items respectively.
 * Also given an integer W which represents knapsack capacity,
 * find out the maximum value subset of val[] such that sum of the weights of this subset is smaller than or equal to W.
 * You cannot break an item, either pick the complete item, or don’t pick it (0-1 property).
 * <p>
 * ex: given value[60,100,120], weight[10,20,30], W = 50
 * The answer is 220
 */
public class KnapsackProblem {

    // Driver program to test above function
    public static void main(String[] args) {
        int[] value = new int[]{60, 100, 120};
        int[] weight = new int[]{10, 20, 30};
        int knapsackWeight = 50;
        int n = value.length;
        System.out.println(knapSackRecursion(knapsackWeight, weight, value, n));
        System.out.println(knapSackDP(knapsackWeight, weight, value, n));
    }

    // A utility function that returns maximum of two integers
    private static int max(int a, int b) {
        return Math.max(a, b);
    }

    // Returns the maximum value that can be put in a knapsack of capacity W
    // bad performance O(2^n)
    private static int knapSackRecursion(int knapsackWeight, int[] weight, int[] value, int index) {
        // Base Case
        if (index == 0 || knapsackWeight == 0)
            return 0;

        // If weight of the nth item is more than Knapsack capacity W, then
        // this item cannot be included in the optimal solution
        if (weight[index - 1] > knapsackWeight) {
            return knapSackRecursion(knapsackWeight, weight, value, index - 1);
        } else {
            // Return the maximum of two cases:
            // (1) nth item included
            // (2) not included
            return max(value[index - 1] + knapSackRecursion(knapsackWeight - weight[index - 1], weight, value, index - 1),
                    knapSackRecursion(knapsackWeight, weight, value, index - 1)
            );
        }
    }

    // Returns the maximum value that can be put in a knapsack of capacity W
    static int knapSackDP(int knapsackWeight, int[] weight, int[] value, int index) {
        //index and capacity
        int dp[][] = new int[index + 1][knapsackWeight + 1];

        // Build table K[][] in bottom up manner
        for (int i = 0; i <= index; i++) {
            for (int capacity = 0; capacity <= knapsackWeight; capacity++) {
                if (i == 0 || capacity == 0) {
                    dp[i][capacity] = 0;
                } else if (weight[i - 1] <= capacity) {
                    dp[i][capacity] = max(value[i - 1] + dp[i - 1][capacity - weight[i - 1]], dp[i - 1][capacity]);
                } else {
                    // copy previous one
                    dp[i][capacity] = dp[i - 1][capacity];
                }
            }
        }

        return dp[index][knapsackWeight];
    }
}
