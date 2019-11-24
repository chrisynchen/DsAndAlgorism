package leetcode.dp;

/**
 * There are n stairs, a person standing at the bottom wants to reach the top. The person can climb either 1 stair or 2 stairs at a time.
 * Count the number of ways, the person can reach the top stairs
 * <p>
 * Consider the example shown in diagram. The value of n is 3. There are 3 ways to reach the top. The diagram is taken from Easier Fibonacci puzzles
 * <p>
 * EX:
 * Input: n = 1
 * Output: 1
 * There is only one way to climb 1 stair
 * <p>
 * Input: n = 2
 * Output: 2
 * There are two ways: (1, 1) and (2)
 * <p>
 * Input: n = 4
 * Output: 5
 * (1, 1, 1, 1), (1, 1, 2), (2, 1, 1), (1, 2, 1), (2, 2)
 */
public class TwoStepStair {
    public static void main(String[] args) {
        int N = 30;
        System.out.println(fibonacci(N + 1));
        System.out.println(stairDP(N));
    }

    private static long fibonacci(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    private static long stairDP(int n) {
        long[] a = new long[n];
        a[0] = 1;                // Ways to reach 1 stair is 1.
        a[1] = 2;                 // Ways to reach 2 stairs are 2. (1+1) , (2)
        for (int i = 2; i < n; i++) {   // Pre computation till 10^5 for all the numbers.
            a[i] = (a[i - 1] + a[i - 2]);
        }

        return a[n - 1];
    }
}
