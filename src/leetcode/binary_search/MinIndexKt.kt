package leetcode.binary_search

fun main(args: Array<String>) {
    println(bs(intArrayOf(1,1,2,2,2,3,3,3,3,4), 2))
}

private fun bs(nums: IntArray, target: Int): Int {
    var headIndex = 0;
    var tailIndex = nums.size

    while (headIndex < tailIndex) {
        val mid = headIndex + (tailIndex - headIndex) / 2;
        if (nums[mid] >= target) {
            tailIndex = mid
        } else {
            headIndex = mid + 1;
        }
    }

    return if (nums[tailIndex] == target) {
        tailIndex
    } else {
        -1
    }
}