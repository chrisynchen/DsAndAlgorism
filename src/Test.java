/**
 * @author chenchris on 2019/4/28.
 */
public class Test {
    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(prices));
    }

    private static int maxProfit(int[] prices) {
        int min = prices[0];
        int result = 0;

        for (int i = 1; i < prices.length; i++) {

            if (min > prices[i]) {
                System.out.println("min:" + min);
                min = prices[i];
            }

            if (prices[i] - min > result) {
                System.out.println("result:" + result);
                result = prices[i] - min;
            }
        }

        return result;
    }
}
