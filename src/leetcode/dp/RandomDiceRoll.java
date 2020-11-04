package leetcode.dp;

/**
 * Imagine you are playing a board game. You roll a 6-faced dice and move forward the same number of spaces that you rolled.
 * If the finishing point is “n” spaces away from the starting point,
 * please implement a program that calculates how many possible ways there are to arrive exactly at the finishing point.
 */
public class RandomDiceRoll {
    public static void main(String[] args) {
        System.out.println(dpString(1));
        System.out.println(dpString(2));
        System.out.println(dpString(3));
        System.out.println(dpString(4));
        System.out.println(dpString(5));
        System.out.println(dpString(6));
        System.out.println(dpString(7));
        System.out.println(dpString(610));
    }

    //Time complexity is O(N), space complexity is O(N)
    private static long dp(int n) {

        if (n <= 0) return 0;

        if (n == 1 || n == 2 || n == 3 || n == 4 || n == 5 || n == 6) return (long) Math.pow(2, n - 1);

        long[] dp = new long[n];
        for (int i = 0; i < 6; i++) {
            dp[i] = (long) Math.pow(2, i);
        }
        //dp[0]
        //dp[1]
        //dp[2]
        //dp[3]
        //dp[4]
        //dp[5]
        for (int i = 6; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3] + dp[i - 4] + dp[i - 5] + dp[i - 6];
        }

        return dp[n - 1];
    }

    private static String dpString(int n) {

        if (n <= 0) return "0";

        if (n == 1 || n == 2 || n == 3 || n == 4 || n == 5 || n == 6) return String.valueOf((int)Math.pow(2, n - 1));

        String[] dp = new String[n];
        for (int i = 0; i < 6; i++) {
            dp[i] = String.valueOf((int)Math.pow(2, i));
        }
        //dp[0]
        //dp[1]
        //dp[2]
        //dp[3]
        //dp[4]
        //dp[5]
        for (int i = 6; i < n; i++) {
            String temp = dp[i - 6];
            for (int j = 1; j < 6; j++) {
                temp = findSum(dp[i - j], temp);
            }
            dp[i] = temp;
        }

        return dp[n - 1];
    }

    private static String findSum(String str1, String str2) {
        // Before proceeding further, make sure length
        // of str2 is larger.
        if (str1.length() > str2.length()) {
            String t = str1;
            str1 = str2;
            str2 = t;
        }

        // Take an empty String for storing result
        String str = "";

        // Calculate length of both String
        int n1 = str1.length(), n2 = str2.length();

        // Reverse both of Strings
        str1 = new StringBuilder(str1).reverse().toString();
        str2 = new StringBuilder(str2).reverse().toString();

        int carry = 0;
        for (int i = 0; i < n1; i++) {
            // Do school mathematics, compute sum of
            // current digits and carry
            int sum = ((int) (str1.charAt(i) - '0') +
                    (int) (str2.charAt(i) - '0') + carry);
            str += (char) (sum % 10 + '0');

            // Calculate carry for next step
            carry = sum / 10;
        }

        // Add remaining digits of larger number
        for (int i = n1; i < n2; i++) {
            int sum = ((int) (str2.charAt(i) - '0') + carry);
            str += (char) (sum % 10 + '0');
            carry = sum / 10;
        }

        // Add remaining carry
        if (carry > 0)
            str += (char) (carry + '0');

        // reverse resultant String
        str = new StringBuilder(str).reverse().toString();

        return str;
    }
}
