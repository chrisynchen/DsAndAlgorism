package leetcode.dp;

/**
 * There are n stairs, a person standing at the bottom wants to reach the top. The person can climb 1 ~ m stairs at a time.
 * Count the number of ways, the person can reach the top stairs
 * <p>
 * Input: n = 4, m = 2
 * Output: 5
 * (1, 1, 1, 1), (1, 1, 2), (2, 1, 1), (1, 2, 1), (2, 2)
 * <p>
 * Input: n = 1, m = 3
 * Output: 1
 * (1)
 * <p>
 * Input: n = 2, m = 3
 * Output: 2
 * (1,1) (2)
 * <p>
 * Input: n = 3, m = 3
 * Output: 4
 * (1, 1, 1), (1, 2), (2, 1), (3)
 * <p>
 * Input: n = 4, m = 3
 * Output: 7
 * (1, 1, 1, 1), (3,1), (1,3), (1, 1, 2), (2, 1, 1), (1, 2, 1), (2, 2)
 * <p>
 * Input: n = 5, m = 3
 * Output: 13
 * (1, 1, 1, 1, 1), (3,2), (2,3), (1, 1, 1, 2), (1, 1, 2, 1), (1, 2, 1, 1), (2,1,1,1),
 * (1,2,2), (2,1,2), (2,2,1), (1,1,3) (1,3,1), (3,1,1)
 */
public class NStepStair {
    public static void main(String[] args) {
        int N = 5;
        int maxStep = 3;
        System.out.println(fibonacci(N + 1, maxStep));
        System.out.println(stairDP(N + 1, maxStep));
    }

    private static long fibonacci(int n, int maxStep) {
        if (n <= 0) return 0;
        if (n == 1) return 1;

        int result = 0;
        for (int i = 1; i <= maxStep; i++) {
            result += fibonacci(n - i, maxStep);
        }

        return result;
    }

    //Time complexity is O(M * N)
    private static long stairDP(int n, int maxStep) {
        long[] a = new long[n];
        a[0] = 1;
        a[1] = 1;
        for (int i = 2; i < n; i++) {
            for (int j = 1; j <= i && j <= maxStep; j++) {
                a[i] += a[i - j];
            }
        }

        return a[n - 1];
    }
}
