package leetcode.sort;

public class InsertSort {
    public static void main(String[] args) {
        int[] temp = {4, 10, 3, 5, 1};
        insertSort(temp);
        for (int element : temp) {
            System.out.println(element);
        }
    }

    private static void insertSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if(nums[i] < nums[j]) {
                    swap(nums, i, j);
                }
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
