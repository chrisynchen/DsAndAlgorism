package leetcode.heap;

public class Heap {

    public static void main(String[] args) {
        int[] temp = {4, 10, 3, 5, 1};
        for (int i = (temp.length / 2) - 1; i >= 0; i--) {
            minHeapify(temp, temp.length, i);
        }
        for (int element : temp) {
            System.out.println(element);
        }
    }

    private static void maxHeapify(int nums[], int n, int i) {
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
            maxHeapify(nums, n, largest);
        }
    }

    private static void minHeapify(int nums[], int n, int i) {
        int smallest = i; // Initialize smallest as root
        int leftIndex = 2 * i + 1; // left = 2*i + 1
        int rightIndex = 2 * i + 2; // right = 2*i + 2

        // If left child is smaller than root
        if (leftIndex < n && nums[leftIndex] < nums[smallest])
            smallest = leftIndex;

        // If right child is smaller than smallest so far
        if (rightIndex < n && nums[rightIndex] < nums[smallest])
            smallest = rightIndex;

        // If largest is not root
        if (smallest != i) {
            int temp = nums[i];
            nums[i] = nums[smallest];
            nums[smallest] = temp;

            // Recursively heapify the affected sub-tree
            minHeapify(nums, n, smallest);
        }
    }
}
