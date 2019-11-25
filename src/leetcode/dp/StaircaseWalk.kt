package leetcode.dp

import leetcode.dp.StaircaseWalk.maxCost
import leetcode.dp.StaircaseWalk.minCost
import kotlin.math.max
import kotlin.math.min

fun main(args: Array<String>) {

    val twoDimisionArray = arrayOf(
    intArrayOf(0, 1, 3, 2, 3, 4, 5, 2),
    intArrayOf(4, 2, 1, 4, 6, 2, 0, 3),
    intArrayOf(1, 9, 9, 9, 9, 9, 9, 1),
    intArrayOf(8, 7, -3, 2, 1, -2, 1, 3),
    intArrayOf(9, 8, 7, 0, 1, 2, 7, 3),
    intArrayOf(-3, 2, 2, 3, 1, -3, 1, 9),
    intArrayOf(4, 5, 4, 0, 4, 5, 4, 5),
    intArrayOf(9, 8, 7, 6, 5, 4, 3, 2)
    )
    val twoDimisionArray1 = arrayOf(
            intArrayOf(0, 1, 3, 2, 3, 4, 5, 2),
            intArrayOf(4, 2, 1, 4, 6, 2, 0, 3),
            intArrayOf(1, 9, 9, 9, 9, 9, 9, 1),
            intArrayOf(8, 7, -3, 2, 1, -2, 1, 3),
            intArrayOf(9, 8, 7, 0, 1, 2, 7, 3),
            intArrayOf(-3, 2, 2, 3, 1, -3, 1, 9),
            intArrayOf(4, 5, 4, 0, 4, 5, 4, 5),
            intArrayOf(9, 8, 7, 6, 5, 4, 3, 2)
    )
    println(minCost(twoDimisionArray))
    println(maxCost(twoDimisionArray1))
}

object StaircaseWalk {
    fun minCost(cost: Array<IntArray>): Int {
        for( i in 1 until cost[0].size) {
            cost[0][i] += cost[0][i - 1]
        }

        for( j in 1 until cost.size) {
            cost[j][0] += cost[j - 1][0]
        }

        for( a in 1 until cost.size) {
            for( b in 1 until cost[0].size) {
                cost[a][b] = min(cost[a - 1][b] + cost[a][b], cost[a][b - 1] + cost[a][b])
            }
        }

        return cost[cost.size - 1][cost[0].size - 1]
    }

    fun maxCost(cost: Array<IntArray>): Int {
        for( i in 1 until cost[0].size) {
            cost[0][i] += cost[0][i - 1]
        }

        for( j in 1 until cost.size) {
            cost[j][0] += cost[j - 1][0]
        }

        for( a in 1 until cost.size) {
            for( b in 1 until cost[0].size) {
                cost[a][b] = max(cost[a - 1][b] + cost[a][b], cost[a][b - 1] + cost[a][b])
            }
        }

        return cost[cost.size - 1][cost[0].size - 1]
    }
}