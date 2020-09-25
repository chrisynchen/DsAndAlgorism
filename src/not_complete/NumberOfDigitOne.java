package not_complete;

/**
 * 233. Number of Digit One (Hard)
 *
 * Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.
 *
 * Example:
 *
 * Input: 13
 * Output: 6
 * Explanation: Digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.
 */
public class NumberOfDigitOne {

    public static void main(String[] args) {
        System.out.println(countDigitOne1(100));
        System.out.println(countDigitOne2(100));
//        System.out.println(countDigitOne1(3184191));
//        System.out.println(countDigitOne2(3184191));
//        System.out.println(countDigitOne1(824883294));
//        System.out.println(countDigitOne2(824883294));
    }

    //O(n * log(n)) => too slow will cause Time Limit Exceeded
    private static int countDigitOne1(int n) {
        int result = 0;
        for(int i = 1; i <=n; i++) {
            result += getDigitOneCount(i);
        }

        return result;
    }

    //O(n * log(n)) => too slow will cause Time Limit Exceeded
    public static int countDigitOne2(int n) {
        int result = 0;
        for(int i = 1; i <=n; i++) {
            String s = String.valueOf(i).replaceAll("[0,2,3,4,5,6,7,8,9]","");
            result += s.length();
        }

        return result;
    }

    private static int getDigitOneCount(int digit) {
        int count = 0;
        while(digit > 0) {
            if((digit % 10) == 1){
                count++;
            }
            digit /= 10;
        }

        return count;
    }
}
