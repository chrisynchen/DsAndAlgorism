package woven;

public class findMoreThanKthLargest {
    public static void main(String[] args) {
        int[][] example = new int[][]{{1426828011, 9},
                {1426828028, 350},
                {1426828037, 25},
                {1426828056, 231},
                {1426828058, 109},
                {1426828066, 111}};

        int[] result = findMoreThanKthLargest(example, 3);
        for(int e: result) {
            System.out.println(e);
        }
    }

    private static int[] findMoreThanKthLargest(int[][] nums, int k) {
        int[] result = new int[k];

        //quick select find kth largest
        int p = findKthLargest(nums, k);

        //pick num which more than p
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i][1] > p) {
                result[index++] = nums[i][0];
            }
        }

        //fill rest of item to fulfill k items
        int i = 0;
        while(index < k) {
            if(nums[i][1] == p) {
                result[index++] = nums[i][0];
            }
            i++;
        }

        return result;
    }

    private static int findKthLargest(int[][] nums, int k) {
        return findKthLargest(nums, 0, nums.length - 1, nums.length - k);
    }

    private static int findKthLargest(int[][] nums, int headIndex, int tailIndex, int k) {
        int pivot = nums[tailIndex][1];
        int lowerIndex = headIndex;

        for (int i = headIndex; i <= tailIndex; i++) {
            if (nums[i][1] < pivot) {
                swap(nums, lowerIndex++, i);
            }
        }
        swap(nums, lowerIndex, tailIndex);

        if (k == lowerIndex) {
            return nums[k][1];
        } else if (lowerIndex > k) {
            return findKthLargest(nums, headIndex, lowerIndex - 1, k);
        } else {
            //lowerIndex < k
            return findKthLargest(nums, lowerIndex + 1, tailIndex, k);
        }
    }

    private static void swap(int[][] nums, int a, int b) {
        int[] temp = new int[]{nums[a][0], nums[a][1]};
        nums[a][0] = nums[b][0];
        nums[a][1] = nums[b][1];

        nums[b][0] = temp[0];
        nums[b][1] = temp[1];
    }
}
