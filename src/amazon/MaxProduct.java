package amazon;

public class MaxProduct {
    public static void main(String[] args) {
        System.out.print(maxProduct(new int[]{7,4,5,2,6,5}));
    }

    //{7,4,5,2,6,5}, 12
    private static int maxProduct(int[] nums) {
        int result = 0;
        int n = nums.length;
        for(int i = n - 1; i >= 0; i--) {
            int temp = nums[i];
            int prev = nums[i];
            for(int j = i - 1; j >= 0; j--) {
                if(nums[j] >= prev) {
                    prev = prev - 1;
                    temp += prev;
                } else {
                    temp += nums[j];
                    prev = nums[j];
                }

                if(prev <= 0) break;
            }
            result = Math.max(result, temp);
        }

        return result;
    }
}
