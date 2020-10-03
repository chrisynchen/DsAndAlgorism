package leetcode.sort;

public class HeapSort {

    public static void main(String[] args) {
        int[] temp = {4, 10, 3, 5, 1};
        heapSort(temp);
        for (int element : temp) {
            System.out.println(element);
        }
    }

    /**
     * https://www.geeksforgeeks.org/heap-sort/
     *
     * @param nums
     */
    private static void heapSort(int nums[]) {

        int numsLength = nums.length;

        // Build heap (rearrange array)
        for (int i = numsLength / 2 - 1; i >= 0; i--)
            heapify(nums, numsLength, i);

        // One by one extract an element from heap
        for (int i = numsLength - 1; i > 0; i--) {
            // Move current root to end
            int temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;

            // call max heapify on the reduced heap
            heapify(nums, i, 0);
        }
    }

    // To heapify a subtree rooted with node i which is
    private static void heapify(int nums[], int n, int i) {
        int largest = i; // Initialize largest as root
        int leftIndex = 2 * i + 1; // left = 2*i + 1
        int rightIndex = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (leftIndex < n && nums[leftIndex] > nums[largest])
            largest = leftIndex;

        // If right child is larger than largest so far
        if (rightIndex < n && nums[rightIndex] > nums[largest])
            largest = rightIndex;

        // If largest is not root
        if (largest != i) {
            int swap = nums[i];
            nums[i] = nums[largest];
            nums[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(nums, n, largest);
        }
    }
}
