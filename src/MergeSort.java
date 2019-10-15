/**
 * @author chenchris on 2019/4/28.
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] temp = {1, 3, 2, 5, 4, 8, 7, 6};
        mergeSort(temp);
        for (int element : temp) {
            System.out.println(element);
        }
    }

    private static void mergeSort(int[] array) {
        if (array.length < 2) return;

        int mid = array.length / 2;
        int[] leftArray = new int[mid];
        int[] rightArray = new int[array.length - mid];

        for (int i = 0; i < array.length; i++) {
            if (i < mid) {
                leftArray[i] = array[i];
            } else {
                rightArray[i - mid] = array[i];
            }
        }

        mergeSort(leftArray);
        mergeSort(rightArray);
        merge(array, leftArray, rightArray);
    }

    private static void merge(int[] array, int[] leftArray, int[] rightArray) {
        int i = 0, j = 0, k = 0;

        //compare two array and pick the small one.
        while (i < leftArray.length && j < rightArray.length) {
            if (leftArray[i] <= rightArray[j]) {
                array[k++] = leftArray[i++];
            } else {
                array[k++] = rightArray[j++];
            }
        }

        //reset fill in the last.
        while (i < leftArray.length) {
            array[k++] = leftArray[i++];
        }
        while (j < rightArray.length) {
            array[k++] = rightArray[j++];
        }
    }
}
