package leetcode.sort;

public class SelectionSort {
    public static void main(String[] args) {
        int[] temp = {4, 10, 3, 5, 1};
        selectionSort(temp);
        for (int element : temp) {
            System.out.println(element);
        }
    }

    private static void selectionSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                if(nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            swap(nums, i, minIndex);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
