public class Containing7s {
    public static void main(String[] args) {

        //Test case
        System.out.println("1:" + countNumberWithDp(1));
        System.out.println("7:" + countNumberWithDp(7));
        System.out.println("8:" + countNumberWithDp(8));
        System.out.println("60:" + countNumberWithDp(60));
        System.out.println("70:" + countNumberWithDp(70));
        System.out.println("77:" + countNumberWithDp(77));
        System.out.println("80:" + countNumberWithDp(80));
        System.out.println("800:" + countNumberWithDp(800));
        System.out.println("876:" + countNumberWithDp(876));
        System.out.println("87:" + countNumberWithDp(87));
        System.out.println("201:" + countNumberWithDp(201));
        System.out.println("601:" + countNumberWithDp(601));
        System.out.println("1000:" + countNumberWithDp(1000));
        System.out.println("10000:" + countNumberWithDp(10000));
        System.out.println("77777:" + countNumberWithDp(77777));

        System.out.println("=============");

        //Test case
        System.out.println("1:" + countNumberWithString(1));
        System.out.println("7:" + countNumberWithString(7));
        System.out.println("8:" + countNumberWithString(8));
        System.out.println("60:" + countNumberWithString(60));
        System.out.println("77:" + countNumberWithString(77));
        System.out.println("70:" + countNumberWithString(70));
        System.out.println("80:" + countNumberWithString(80));
        System.out.println("800:" + countNumberWithString(800));
        System.out.println("876:" + countNumberWithString(876));
        System.out.println("87:" + countNumberWithString(87));
        System.out.println("201:" + countNumberWithString(201));
        System.out.println("601:" + countNumberWithString(601));
        System.out.println("1000:" + countNumberWithString(1000));
        System.out.println("10000:" + countNumberWithString(10000));
        System.out.println("77777:" + countNumberWithString(77777));
    }

    /**
     * O(ln(n))
     * @param input
     * @return
     */
    private static int countNumberWithDp(final int input) {
        if (input < 10) {
            if (input / 7 >= 1) {
                return 1;
            } else {
                return 0;
            }
        }

        int count = 0;
        int tempInput = input;
        while (tempInput > 0) {
            tempInput = tempInput / 10;
            count++;
        }

        int[] dp = new int[count - 1];
        dp[0] = 1;

        for (int i = 1; i < dp.length; i++) {
            dp[i] = 9 * dp[i - 1] + (int) Math.pow(10, i);
        }

        return calculateWithDp(input, dp, false);
    }

    private static int calculateWithDp(final int input, final int[] dp, boolean isSkipCount) {

        if (isSkipCount) return 0;

        if (input < 10) {
            if (input / 7 >= 1) {
                return 1;
            } else {
                return 0;
            }
        }

        int count = 0;
        int firstDigit = 0;
        int tempInput = input;
        while (tempInput > 0) {
            tempInput = tempInput / 10;
            if (tempInput > 0) {
                firstDigit = tempInput;
                count++;
            }
        }

        int temp = (int) Math.pow(10, count);
        int remaining = input - (firstDigit * temp);

        int result = 0;
        if (firstDigit > 7) {
            result = (firstDigit - 1) * dp[count - 1] + temp + calculateWithDp(remaining, dp, isSkipCount);
            return result;
        } else if (firstDigit == 7) {
            result = firstDigit * dp[count - 1] + input - (firstDigit * temp) + 1 + calculateWithDp(remaining, dp, true);
        } else {
            result = firstDigit * dp[count - 1] + calculateWithDp(remaining, dp, isSkipCount);
        }

        return result;
    }

    /**
     * linear time. O(N)
     * @param num
     * @return
     */
    private static int countNumberWithString(int num) {
        int count = 0;
        for (int i = 1; i <= num; i++) {
            if (String.valueOf(i).contains("7")) {
                count++;
            }
        }

        return count;
    }
}