package amazon;

public class MaxLengthOfProductOne {
    public static void main(String[] args) {
        System.out.print(maxProductLength(new int[]{1,-1,-1,1,1,-1}));
    }

    //[1,-1,-1,1,1,-1] 5
    //[1,1,-1,1,1]
    private static int maxProductLength(int[] nums) {
        int firstMinusOne = -1;
        int lastMinusOne = -1;
        int n =  nums.length;

        int result = 1;
        for(int i = 0; i < n; i++) {
            if(firstMinusOne == -1 && nums[i] == -1) {
                firstMinusOne = i;
            }

            if(lastMinusOne == -1 && nums[n - i - 1] == -1) {
                lastMinusOne = n - i - 1;
            }

            result *= nums[i];
        }

        if(result == 1) {
            return nums.length;
        } else {
            return Math.max(n - firstMinusOne - 1, lastMinusOne);
        }
    }
}
