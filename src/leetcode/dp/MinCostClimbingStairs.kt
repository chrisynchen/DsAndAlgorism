package leetcode.dp

import leetcode.dp.MinCostClimbingStairs.minCostClimbingStairs
import kotlin.math.min

/**
 * 746. Min Cost Climbing Stairs
 * Easy
 * On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).

 * Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top of the floor, and you can either start from the step with index 0, or the step with index 1.

 * Example 1:
 * Input: cost = [10, 15, 20]
 * Output: 15
 * Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
 * Example 2:
 * Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
 * Output: 6
 * Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].
 * Note:
 * cost will have a length in the range [2, 1000].
 * Every cost[i] will be an integer in the range [0, 999].
 */

fun main(args: Array<String>){
    println(minCostClimbingStairs(intArrayOf(1, 100, 1, 1, 1, 100, 1, 1, 100, 1)))
}

object MinCostClimbingStairs {
    fun minCostClimbingStairs(cost: IntArray): Int {
        val a = IntArray(cost.size + 1)

        a[0] = cost[0]
        a[1] = cost[1]

        for (i in 2..cost.size) {
            val currentCost = if (i == cost.size) 0 else cost[i]
            a[i] = min(currentCost + a[i - 1], currentCost + a[i - 2])
        }

        return a[cost.size]
    }
}